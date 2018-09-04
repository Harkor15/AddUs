package harkor.addus.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import harkor.addus.R;
import harkor.addus.interfaces.IFragMenager;

public class MenuViewModel extends BaseObservable{
static IFragMenager iFragMenager;
public boolean signed;

    public MenuViewModel(IFragMenager iFragMenager){
        this.iFragMenager= iFragMenager;
        signed=iFragMenager.isSignedIn();
    }
    public void onClickGame(View v){
        iFragMenager.goGame();
    }
    public void onClickLogin(View v){
        iFragMenager.logButtonClick();
    }
    public void onClickRanking(View v){
        iFragMenager.showRanking();
    }
    public void onClickAchievements(View v){
        iFragMenager.showAchievements();
    }
    public void onClickResult(View v){
        iFragMenager.showResult();
    }

}
