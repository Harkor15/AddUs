package harkor.addus.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import harkor.addus.R;
import harkor.addus.databinding.FragmentGameBinding;
import harkor.addus.interfaces.IFragMenager;
import harkor.addus.interfaces.ISoundsPlay;
import harkor.addus.viewmodel.GameViewModel;

public class GameFragment extends Fragment implements ISoundsPlay{
    @BindView(R.id.text_time)
    TextView textTime;
    GameViewModel gameViewModel;
    SoundsPlayer soundsPlayer;
    IFragMenager iFragMenager;
    boolean created=true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentGameBinding binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_game,
                container,
                false
        );
        created=true;
        View view = binding.getRoot();
        ButterKnife.bind(this,view);
        soundsPlayer=new SoundsPlayer(getContext());
        iFragMenager=(IFragMenager)getActivity();
        ISoundsPlay iSoundsPlay=this;
        gameViewModel= new GameViewModel(iFragMenager,iSoundsPlay);
        gameViewModel.init();
        binding.setGameViewModel(gameViewModel);


        return view;
    }
/*
    @Override
    public void onDestroy() {
        gameViewModel.cancelTimer();
        super.onDestroy();
    }
*/
    @Override
    public void onPause() {
        gameViewModel.cancelTimer();
        super.onPause();
        Log.i("AddUs","onPause");
    }

    @Override
    public void onResume() {
        if(created==false){
            iFragMenager.goResult(gameViewModel.points);
        }else{
        created=false;
            Log.i("AddUs","onResume");
    }

        super.onResume();
    }

    @Override
    public void playGood() {
        soundsPlayer.playGood();
    }

    @Override
    public void playBad() {
        soundsPlayer.playBad();
    }
}
