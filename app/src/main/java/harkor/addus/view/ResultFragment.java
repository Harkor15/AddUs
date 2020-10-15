package harkor.addus.view;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import harkor.addus.R;
import harkor.addus.SharedP;
import harkor.addus.databinding.FragmentResultBinding;
import harkor.addus.interfaces.IFragMenager;
import harkor.addus.interfaces.IResult;
import harkor.addus.viewmodel.ResultViewModel;


public class ResultFragment extends Fragment implements IResult{
    @BindView(R.id.text_record)
    TextView record;
    SharedP sharedP;
    int points;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arguments= getArguments();
        points=arguments.getInt("points");
        FragmentResultBinding binding= DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_result,
                container,
                false
        );
        View view=binding.getRoot();
        IFragMenager iFragMenager=(IFragMenager)getActivity();
        ResultViewModel resultViewModel=new ResultViewModel(points,iFragMenager,this);
        binding.setResultViewModel(resultViewModel);
        ButterKnife.bind(this,view);
        sharedP=new SharedP(getContext());
        resultViewModel.goCheck();
        return view;
    }
   private void showR(){
        record.setVisibility(View.VISIBLE);
   }
    @Override
    public void showRecord() {
        showR();
    }

    @Override
    public boolean checkBest() {
        return sharedP.trySetBest(points);
    }
}
