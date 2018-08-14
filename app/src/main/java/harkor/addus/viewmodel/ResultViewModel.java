package harkor.addus.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import harkor.addus.interfaces.IFragMenager;


public class ResultViewModel extends BaseObservable {
    int pointsInt;
    String points;
    IFragMenager iFragMenager;
    public ResultViewModel(int points,IFragMenager iFragMenager){
        this.pointsInt=points;
        this.points=points+"";
        this.iFragMenager=iFragMenager;
    }

    @Bindable
    public String getPoints() {
        return points;
    }


    public void toMenu(View v){
        iFragMenager.goMenu();
    }
    public void toGame(View v){
        iFragMenager.goGame();
    }





}
