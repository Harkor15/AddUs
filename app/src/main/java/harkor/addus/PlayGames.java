package harkor.addus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.games.Games;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class PlayGames {
    private GoogleSignInClient mGoogleSignInClient;
    GoogleSignInAccount signedInAccount;
    private Context context;
    Activity activity;
    private static final int RC_ACHIEVEMENT_UI = 9003;

    public PlayGames(Context context,Activity activity){
        this.context=context;
        this.activity=activity;
    }

  //  mGoogleSignInClient = GoogleSignIn.getClient(this,
    //        new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).build());


    private boolean isSignedIn() {
        return GoogleSignIn.getLastSignedInAccount(context) != null;
    }
    void signOut() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(context,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        signInClient.signOut().addOnCompleteListener(activity, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(context,"Signed out!",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
