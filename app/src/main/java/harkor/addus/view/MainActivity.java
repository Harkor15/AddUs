package harkor.addus.view;

import android.content.Intent;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import butterknife.ButterKnife;
import harkor.addus.R;
import harkor.addus.SharedP;
import harkor.addus.interfaces.IFragMenager;



public class MainActivity extends AppCompatActivity implements IFragMenager{
    boolean doubleBackToExitPressedOnce = false;
    final int RC_SIGN_IN=9876;
    private static final int RC_ACHIEVEMENT_UI = 9003;
    private static final int RC_LEADERBOARD_UI = 9004;
    private GoogleSignInClient mGoogleSignInClient;
    GoogleSignInAccount signedInAccount;
    MenuFragment menuFragment;
    Boolean played=false;


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
        if(!isSignedIn()){
            startSignInIntent();
        }
        goMenu(false);

    }
    private void leaderboardAndAchevemenents(){
        SharedP sharedP=new SharedP(getApplicationContext());
        int best=sharedP.getBest();
        Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .submitScore(getString(R.string.leaderboard_standard_mode), best);
        if(played){
            played=false;
            Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                    .unlock(getString(R.string.achievement_first_play));
            Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                    .increment(getString(R.string.achievement_10_games),1);
            Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                    .increment(getString(R.string.achievement_50_games),1);
            Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                    .increment(getString(R.string.achievement_100_games),1);
        }
        if(best>=400){
            Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                    .unlock(getString(R.string.achievement_400_points));
            if(best>=500){
                Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                        .unlock(getString(R.string.achievement_500_points));
                if(best>=600){
                    Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                            .unlock(getString(R.string.achievement_600_points));
                    if(best>=700){
                        Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                                .unlock(getString(R.string.achievement_700_points));
                    }
                }
            }
        }
    }

    @Override
    public boolean isSignedIn() {
        return GoogleSignIn.getLastSignedInAccount(this) != null;
    }

    @Override
    public void logButtonClick() {
        if(isSignedIn()){
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

    private void signInSilently() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        signInClient.silentSignIn().addOnCompleteListener(this,
                new OnCompleteListener<GoogleSignInAccount>() {
                    @Override
                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                        if (task.isSuccessful()) {
                            signedInAccount = task.getResult();
                            GamesClient gamesClient = Games.getGamesClient(MainActivity.this,
                                    GoogleSignIn.getLastSignedInAccount(getApplicationContext()));
                            gamesClient.setViewForPopups(findViewById(R.id.gps_popup));
                            logedIn();
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
            GamesClient gamesClient = Games.getGamesClient(MainActivity.this,
                    GoogleSignIn.getLastSignedInAccount(getApplicationContext()));
            gamesClient.setViewForPopups(findViewById(R.id.gps_popup));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                signedInAccount = result.getSignInAccount();
                GamesClient gamesClient = Games.getGamesClient(MainActivity.this,
                        GoogleSignIn.getLastSignedInAccount(getApplicationContext()));
                gamesClient.setViewForPopups(findViewById(R.id.gps_popup));
                logedIn();
            } else {
                String message = result.getStatus().getStatusMessage();
                if (message == null || message.isEmpty()) {
                    message = getString(R.string.signin_other_error);
                }
                new AlertDialog.Builder(this).setMessage(message)
                        .setNeutralButton(android.R.string.ok, null).show();
            }
        }
    }
    void logedIn(){
        menuFragment.setForIn();
    }
    void logedOut(){
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
    public void goMenu(Boolean play) {
        menuFragment = new MenuFragment ();
        played=play;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, menuFragment);
        transaction.commit();
        if(isSignedIn()){
            leaderboardAndAchevemenents();
        }
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
    public void goGame(Boolean play) {
        played=play;
        if(isSignedIn()&&play){
            leaderboardAndAchevemenents();
        }
        GameFragment newFragment = new GameFragment ();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, newFragment);
        transaction.commit();
    }

    @Override
    public void showResult() {
        SharedP sharedP=new SharedP(getApplicationContext());
        int res=sharedP.getBest();
        menuFragment.showbestResult(res);
    }

    @Override
    public void showAchievements() {
        if (isSignedIn()){
            Games.getAchievementsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                    .getAchievementsIntent()
                    .addOnSuccessListener(new OnSuccessListener<Intent>() {
                        @Override
                        public void onSuccess(Intent intent) {
                            startActivityForResult(intent, RC_ACHIEVEMENT_UI);
                        }
                    });
        }
    }

    @Override
    public void showRanking() {
        if(isSignedIn()){
            showLeaderboard();
        }
    }
    private void showLeaderboard() {
        Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .getLeaderboardIntent(getString(R.string.leaderboard_standard_mode))
                .addOnSuccessListener(new OnSuccessListener<Intent>() {
                    @Override
                    public void onSuccess(Intent intent) {
                        startActivityForResult(intent, RC_LEADERBOARD_UI);
                    }
                });
    }

}
