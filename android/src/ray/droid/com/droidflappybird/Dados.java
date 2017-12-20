package ray.droid.com.droidflappybird;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Patterns;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

/**
 * Created by Robson on 20/12/2017.
 */

public class Dados  {



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
        DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
        DatabaseReference jogadorReferencia = databaseReferencia.child("jogadores");

        Jogador jogador = new Jogador();
        jogador.setNome(getAccount(context));
        jogador.setPontos(pontos);

        jogadorReferencia.child(getIDDevice(context)).setValue(jogador);
    }
}
