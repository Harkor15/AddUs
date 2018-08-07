package harkor.addus.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import harkor.addus.R;
import harkor.addus.databinding.FragmentGameBinding;
import harkor.addus.databinding.FragmentGameOverBinding;
import harkor.addus.viewmodel.GameOverViewModel;
import harkor.addus.viewmodel.GameViewModel;

public class GameOverFragment extends Fragment {
    GameOverViewModel gameOverViewModel;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentGameOverBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game_over,container,false);
        View view = binding.getRoot();
        gameOverViewModel=GameOverViewModel.getInstance();
        binding.setGameOverViewModel(gameOverViewModel);
        ButterKnife.bind(this,view);

        return view;
    }
}
