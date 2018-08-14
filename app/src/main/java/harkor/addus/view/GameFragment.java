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
import harkor.addus.interfaces.IFragMenager;
import harkor.addus.viewmodel.GameViewModel;

public class GameFragment extends Fragment {
    @BindView(R.id.text_time)
    TextView textTime;
    GameViewModel gameViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentGameBinding binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_game,
                container,
                false
        );
        View view = binding.getRoot();
        ButterKnife.bind(this,view);
        IFragMenager iFragMenager=(IFragMenager)getActivity();
        gameViewModel= new GameViewModel(iFragMenager);
        gameViewModel.init();
        binding.setGameViewModel(gameViewModel);

        return view;
    }

    @Override
    public void onDestroy() {
        gameViewModel.cancelTimer();
        super.onDestroy();
    }
}
