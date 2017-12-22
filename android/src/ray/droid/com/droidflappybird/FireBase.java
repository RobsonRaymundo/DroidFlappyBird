package ray.droid.com.droidflappybird;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * Created by Robson on 19/06/2017.
 */

public class FireBase {

    private static DatabaseReference databaseReferencia;
    private static DatabaseReference jogadorReferencia;

    public static DatabaseReference getDatabaseReferencia()
    {
        if (databaseReferencia == null)
        {
            databaseReferencia = FirebaseDatabase.getInstance().getReference();

        }
        return databaseReferencia;
    }

    public static DatabaseReference jogadorReferencia()
    {
        if (jogadorReferencia == null)
        {
            jogadorReferencia = getDatabaseReferencia().child("jogadores");
        }
        return jogadorReferencia;
    }


}
