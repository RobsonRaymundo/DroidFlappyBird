package ray.droid.com.droidflappybird;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Robson on 20/12/2017.
 */



public class Dados  {

    public static int PontuacaoAtual;

    private static String getIDDevice(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    private static String getAccount(Context context) {
        String account = "padrao";
        try {
            String email = getEmail(context);
            String[] accounts = email.split("@");
            account = accounts[0];
        }catch (Exception ex)
        {
        }
        return account;
    }

    private static String getEmail(Context context) {
        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(context).getAccounts();

        String possibleEmail = "";
        for (Account account : accounts) {
            if (account.type.equalsIgnoreCase("com.google") && emailPattern.matcher(account.name).matches()) {
                possibleEmail = account.name;
                break;
            }
        }

        if (possibleEmail.isEmpty()) {
            for (Account account : accounts) {
                if (emailPattern.matcher(account.name).matches()) {
                    possibleEmail = account.name;
                    break;
                }
            }
        }
        return possibleEmail;
    }


    public static void GravarPontuacao(Context context, int pontos)
    {
        if (FlappyBird.Pontuacao() > Dados.PontuacaoAtual) {
            DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
            DatabaseReference jogadorReferencia = databaseReferencia.child("jogadores");

            Jogador jogador = new Jogador();
            jogador.setNome(getAccount(context));
            jogador.setPontos(pontos);

            jogadorReferencia.child(getIDDevice(context)).setValue(jogador);
        }
    }

    public static void LerPontuacao(Context context)
    {
        DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
        DatabaseReference jogadorReferencia = databaseReferencia.child("jogadores").child(getIDDevice(context)).child("pontos");

        jogadorReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    PontuacaoAtual = Integer.parseInt(dataSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public static void ExibePontuacao(Context context)
    {
        DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
        DatabaseReference jogadorReferencia = databaseReferencia.child("jogadores");
        jogadorReferencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FlappyBird.jogadores.clear();
                for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
                //    for (DataSnapshot ds2 : ds1.getChildren()) {
                    FlappyBird.jogadores.add(ds1.getValue(Jogador.class));
                  //  }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
