package harkor.addus.view;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;

import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import harkor.addus.R;
import harkor.addus.databinding.FragmentMenuBinding;
import harkor.addus.interfaces.IFragMenager;
import harkor.addus.viewmodel.MenuViewModel;

public class MenuFragment extends Fragment{
    MenuViewModel menuViewModel;
    ImageView buttonInOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMenuBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_menu,container,false);
        View view = binding.getRoot();
        buttonInOut =(ImageView) view.findViewById(R.id.image_sign_inout);
        view.findViewById(R.id.privacy_policy).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showPrivacyPolicy();
            }
        });
        IFragMenager iFragMenager=(IFragMenager)getActivity();
        menuViewModel=new MenuViewModel(iFragMenager);

        binding.setMenuViewModel(menuViewModel);
        return view;
    }

    public void setForIn(){
        buttonInOut.setImageResource(R.drawable.log_out);
    }

    public void setForOut(){
        buttonInOut.setImageResource(R.drawable.log_in);
    }

    public void showbestResult(int result){
        Toast.makeText(getContext(),result+"",Toast.LENGTH_SHORT).show();
    }

    void showPrivacyPolicy(){
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.freeprivacypolicy.com/privacy/view/c4fbc20540a7365b768d06ea3e8ba805"));
        startActivity(intent);
    }
}
