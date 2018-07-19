package harkor.addus.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import harkor.addus.R;
import harkor.addus.databinding.FragmentGameBinding;
import harkor.addus.viewmodel.GameViewModel;

public class GameFragment extends Fragment {
    @BindView(R.id.text_time)
    TextView textTime;
    GameViewModel gameViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View view=inflater.inflate(R.layout.fragment_game, container, false);
        FragmentGameBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        //binding.setMarsdata(data);

        //FragmentGameBinding
        //GameFragmentBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ButterKnife.bind(this,view);
        binding.setGameViewModel(gameViewModel);
        gameViewModel= ViewModelProviders.of(getActivity()).get(GameViewModel.class);
        gameViewModel.init();
///<import type="android.support.v4.app.Fragment"/>
        return view;
    }
    public void setTime(int seconds){
        textTime.setText(seconds+"");
    }
    private void initDataBinding(){

    }


}
