package harkor.addus.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import harkor.addus.interfaces.IFragMenager;
import harkor.addus.interfaces.IResult;


public class ResultViewModel extends BaseObservable {
    int pointsInt;
    String points;
    IFragMenager iFragMenager;
    IResult iResult;
    public ResultViewModel(int points, IFragMenager iFragMenager, IResult iResult){
        this.pointsInt=points;
        this.points=points+"";
        this.iFragMenager=iFragMenager;
        this.iResult=iResult;

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

    public void goCheck(){
        if(iResult.checkBest()){
            iResult.showRecord();
        }
    }



}
