package ray.droid.com.droidflappybird;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Timestamp;

import ray.droid.com.droidflappybird.FlappyBird;


public class AndroidLauncher extends AndroidApplication  {
    private ApplicationListener applicationListener;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getBaseContext();
        Dados.LerPontuacao(getBaseContext());
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        applicationListener = new FlappyBird() {

            @Override
            public void pause() {
                if (FlappyBird.VerPlacar) {
                    FlappyBird.VerPlacar = false;
                    Dados.GravarPontuacao(context, FlappyBird.Pontuacao());
                    Dados.ExibePontuacao(context);
                }
            }

            @Override
            public void resume() {

            }

        };



        initialize(applicationListener, config);


    }

    @Override
    protected void onDestroy() {
   //     Dados.GravarPontuacao(getBaseContext(), FlappyBird.Pontuacao());
        super.onDestroy();
    }

    private void timeSleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }


}
