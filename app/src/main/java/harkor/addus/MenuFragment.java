package harkor.addus;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import harkor.addus.view.GameFragment;

public class MenuFragment extends Fragment{
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
    if(isSignedIn()){
        myCallback.onButtonClick(1);
    }else{
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

    ///////////////////////////////////////////////FRAGMENTS IMPLEMENTATION



}
