package harkor.addus.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
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
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import harkor.addus.R;
import harkor.addus.databinding.FragmentMenuBinding;

import harkor.addus.interfaces.IFragMenager;
import harkor.addus.view.GameFragment;
import harkor.addus.viewmodel.MainViewModel;
import harkor.addus.viewmodel.MenuViewModel;

public class MenuFragment extends Fragment{
    IFragMenager iFragMenager;
    MenuViewModel menuViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMenuBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_menu,container,false);
        View view = binding.getRoot();
        ButterKnife.bind(this,view);
        IFragMenager iFragMenager=(IFragMenager)getActivity();
        menuViewModel=new MenuViewModel(iFragMenager);
        binding.setMenuViewModel(menuViewModel);

        return view;
    }

}
