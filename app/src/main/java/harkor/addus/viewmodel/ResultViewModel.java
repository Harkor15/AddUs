package harkor.addus.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import android.view.View;

import harkor.addus.interfaces.IFragMenager;
import harkor.addus.interfaces.IResult;


public class ResultViewModel extends BaseObservable {
    int pointsInt;
    String points;
    IFragMenager iFragMenager;
    IResult iResult;
    long start;
    public ResultViewModel(int points, IFragMenager iFragMenager, IResult iResult){
        this.pointsInt=points;
        this.points=points+"";
        this.iFragMenager=iFragMenager;
        this.iResult=iResult;
        start=System.currentTimeMillis();

    }
    @Bindable
    public String getPoints() {
        return points;
    }

    public void toMenu(View v){
        if(System.currentTimeMillis()-start>750){
            iFragMenager.goMenu(true);
        }

    }
    public void toGame(View v){
        if(System.currentTimeMillis()-start>750) {
            iFragMenager.goGame(true);
        }
    }

    public void goCheck(){
        if(iResult.checkBest()){
            iResult.showRecord();
        }
    }



}
