package harkor.addus.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import harkor.addus.BR;
import harkor.addus.interfaces.IFragMenager;
import harkor.addus.interfaces.IGame;
import harkor.addus.interfaces.ISoundsPlay;
import harkor.addus.model.Square;

public class GameViewModel extends BaseObservable implements IGame{
    IFragMenager iFragMenager;
    public static ObservableArrayList<Square> squares;
    public int points;
    public String pointext;
    public int time;
    public String timer;
    boolean backClickTwice=false;
    ISoundsPlay iSoundsPlay;
    private Logic logic;


    public GameViewModel(IFragMenager iFragMenager,ISoundsPlay iSoundsPlay){
        this.iFragMenager=iFragMenager;
        this.iSoundsPlay=iSoundsPlay;
        logic=new Logic(this,iSoundsPlay);
    }

    public void setPoints(int points) {
        this.points = points;
        setPointext(""+points);
    }

    @Bindable
    public String getPointext() {
        return pointext;
    }
    public void setPointext(String sPoints) {
        pointext = sPoints;
        notifyPropertyChanged(BR.pointext);
    }

    @Bindable
    public ObservableArrayList<Square> getSquares() {
        return squares;
    }

    @Bindable
    public String getTimer(){
        return timer;
    }
    public void setTimer(String s){
        timer=s;
        notifyPropertyChanged(BR.timer);
    }
    public void setTime(int t){
        time=t;
        setTimer(time+"");
    }


    public void init(){
        squares=new ObservableArrayList<>();
        for(int i=1;i<=25;i++){
            squares.add(new Square());
        }
        setPoints(0);
        setTime(60);
    }

    public void onClickItem(View v,int id){
        logic.click(id);
    }

    public void onBackClick(View v){
        if (backClickTwice) {
            logic.count.cancel();
            iFragMenager.goMenu(false);
        }
        this.backClickTwice = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backClickTwice=false;
            }
        }, 2000);
    }

    @BindingAdapter("load_image")
    public static void loadImage(ImageView view,Square square) {
            view.setImageResource(square.drawable);
    }

    public void cancelTimer(){
        logic.count.cancel();
    }

    @Override
    public void setterSquare(int id,Square square){
        squares.set(id,square);
    }

    @Override
    public void nextSecond(){
        setTime(--time);
    }

    @Override
    public Square getSquare(int id) {
        return squares.get(id);
    }

    @Override
    public void addPoints(int value){
        if(value<0){
            if(points>=1){
                setPoints(points+value);
            }
        }else{
            setPoints(points+value);
        }

    }
    @Override
    public void timeout(){
        Log.i("Timeout","TRUE");
        iFragMenager.goResult(points);
    }



}
