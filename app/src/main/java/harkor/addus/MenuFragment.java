package harkor.addus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuFragment extends Fragment {
    //private static final int RC_ACHIEVEMENT_UI = 9003;
    //private static final int RC_SIGN_IN = 9898;
    GoogleSignInClient mGoogleSignInClient;
    @BindView(R.id.image_sign_inout)
    ImageView imageSignInOut;
    OnButtonClickListener myCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this,view);
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(),
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).build());
        if(isSignedIn()){//TODO: SET IMAGE LOGED IN OR LOGED OUT!
            //imageSignInOut.setImageDrawable(R.drawable.);
        }else{
         //imageSignInOut.setImageDrawable(R.drawable.);
        }

        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            myCallback = (OnButtonClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnButtonClickListener");
        }
    }

    public interface OnButtonClickListener {
        void onButtonClick(int idButton);
    }
    @OnClick(R.id.image_button1)
    void playGame(){
        GameFragment newFragment = new GameFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, newFragment);transaction.commit();
    }

    @OnClick(R.id.image_sign_inout)
    void click(){
    if(isSignedIn()){ //TODO: LOG OUT
        myCallback.onButtonClick(1);
    }else{//TODO: LOG IN
        myCallback.onButtonClick(2);
    }

    }
    @OnClick(R.id.image_achievements)
    void showAchievements(){
        myCallback.onButtonClick(3);
    }
    private boolean isSignedIn() {
        return GoogleSignIn.getLastSignedInAccount(getContext()) != null;
    }

}
