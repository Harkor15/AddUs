package harkor.addus;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.games.Games;
import com.google.android.gms.tasks.OnSuccessListener;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuFragment extends Fragment {
    private static final int RC_ACHIEVEMENT_UI = 9003;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    @OnClick(R.id.image_button1)
    void playGame(){
        GameFragment newFragment = new GameFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, newFragment);transaction.commit();


    }
    private boolean isSignedIn() {
        return GoogleSignIn.getLastSignedInAccount(getContext()) != null;
    }
    void showAchievements() {
        if (isSignedIn()){
            Games.getAchievementsClient(getActivity(), GoogleSignIn.getLastSignedInAccount(getContext()))
                    .getAchievementsIntent()
                    .addOnSuccessListener(new OnSuccessListener<Intent>() {
                        @Override
                        public void onSuccess(Intent intent) {
                            startActivityForResult(intent,RC_ACHIEVEMENT_UI);
                        }
                    });
        }
    }
}
