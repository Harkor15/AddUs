package harkor.addus.view;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import butterknife.ButterKnife;
import harkor.addus.R;
import harkor.addus.SharedP;
import harkor.addus.interfaces.IFragMenager;



public class MainActivity extends AppCompatActivity implements IFragMenager{
    boolean doubleBackToExitPressedOnce = false;
    final int RC_SIGN_IN=9876;
    private GoogleSignInClient mGoogleSignInClient;
    GoogleSignInAccount signedInAccount;
    MenuFragment menuFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mGoogleSignInClient = GoogleSignIn.getClient(this,
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).build());

        goMenu();
        Log.i("Addus logedin?",isSignedIn()+"");
    }
    @Override
    public boolean isSignedIn() {
        return GoogleSignIn.getLastSignedInAccount(this) != null;
    }

    @Override
    public void logButtonClick() {
        //TODO: LOG IN OR LOG OUT
        if(isSignedIn()){//LOG OUT
            GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
                    GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
            signInClient.signOut().addOnCompleteListener(this,
                    new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            logedOut();
                        }
                    });
        }else{//LOG IN
            startSignInIntent();
        }


    }

    @Override
    public void showRanking() {
        //TODO: Ranking show
    }

    private void signInSilently() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        signInClient.silentSignIn().addOnCompleteListener(this,
                new OnCompleteListener<GoogleSignInAccount>() {
                    @Override
                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                        if (task.isSuccessful()) {
                            // The signed in account is stored in the task's result.
                            signedInAccount = task.getResult();
                            GamesClient gamesClient = Games.getGamesClient(MainActivity.this, GoogleSignIn.getLastSignedInAccount(getApplicationContext()));
                            gamesClient.setViewForPopups(findViewById(R.id.gps_popup));
                            logedIn();
                        } else {

                        }
                    }
                });
    }
    @Override
    protected void onResume() {
        super.onResume();
        signInSilently();
    }
    private void startSignInIntent() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        Intent intent = signInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
        if(isSignedIn()){
            GamesClient gamesClient = Games.getGamesClient(MainActivity.this, GoogleSignIn.getLastSignedInAccount(getApplicationContext()));
            gamesClient.setViewForPopups(findViewById(R.id.gps_popup));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // The signed in account is stored in the result.
                signedInAccount = result.getSignInAccount();
                GamesClient gamesClient = Games.getGamesClient(MainActivity.this,
                        GoogleSignIn.getLastSignedInAccount(getApplicationContext()));
                gamesClient.setViewForPopups(findViewById(R.id.gps_popup));
                logedIn();
            } else {
                String message = result.getStatus().getStatusMessage();
                if (message == null || message.isEmpty()) {
                    message = getString(R.string.signin_other_error);
                    //Log.i("addus",result+"");
                }
                new AlertDialog.Builder(this).setMessage(message)
                        .setNeutralButton(android.R.string.ok, null).show();
            }
        }
    }
    void logedIn(){
        //imageView.setImageResource(R.drawable.log_out);
        menuFragment.setForIn();
    }
    void logedOut(){
        //imageView.setImageResource(R.drawable.log_in);
        menuFragment.setForOut();
    }



    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.two_tap_to_exit, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public void goMenu() {
        menuFragment = new MenuFragment ();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, menuFragment);
        transaction.commit();
    }

    @Override
    public void goResult(int points) {
        ResultFragment newFragment = new ResultFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("points", points);
        newFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, newFragment);
        transaction.commit();
    }

    @Override
    public void goGame() {
        GameFragment newFragment = new GameFragment ();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, newFragment);
        transaction.commit();
    }

    @Override
    public void showResult() {
        SharedP sharedP=new SharedP(getApplicationContext());
        int res=sharedP.getBest();
        //Toast.makeText(getApplicationContext(),res+"",Toast.LENGTH_SHORT);
        menuFragment.showbestResult(res);
    }
}
