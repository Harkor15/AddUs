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
import harkor.addus.databinding.FragmentResultBinding;
import harkor.addus.interfaces.IFragMenager;
import harkor.addus.viewmodel.ResultViewModel;


public class ResultFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arguments= getArguments();
        int points=arguments.getInt("points");
        FragmentResultBinding binding= DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_result,
                container,
                false
        );
        View view=binding.getRoot();
        IFragMenager iFragMenager=(IFragMenager)getActivity();
        ResultViewModel resultViewModel=new ResultViewModel(points,iFragMenager);
        binding.setResultViewModel(resultViewModel);
        //ButterKnife.bind(this,view);
        return view;
    }


}
