package harkor.addus.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import android.view.View;

import harkor.addus.interfaces.IFragMenager;

public class MenuViewModel extends BaseObservable{
static IFragMenager iFragMenager;
public boolean signed;

    public MenuViewModel(IFragMenager iFragMenager){
        this.iFragMenager= iFragMenager;
        signed=iFragMenager.isSignedIn();
    }
    public void onClickGame(View v){
        iFragMenager.goGame(false);
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
