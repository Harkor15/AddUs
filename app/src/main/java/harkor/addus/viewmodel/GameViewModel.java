package harkor.addus.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.games.Game;

import harkor.addus.BR;
import harkor.addus.MenuFragment;
import harkor.addus.R;
import harkor.addus.model.Square;

public class GameViewModel extends BaseObservable {
    public static ObservableArrayList<Square> squares;
    public int points;
    public String pointext;
    private Logic logic=new Logic();
    public int time;
    public String timer;


    private GameViewModel(){}
    public static GameViewModel getInstance(){
        return SingletonHolder.INSTANCE;
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
        Log.i("Info pointext",pointext);
        notifyPropertyChanged(BR.pointext);
    }
    public void addPoints(int value){
        setPoints(points+value);
    }

    @Bindable
    public ObservableArrayList<Square> getSquares() {
        return squares;
    }

    public void setSquares(int doubled,int generated) {
        GameViewModel.squares.get(doubled).addIs(GameViewModel.squares.get(doubled),GameViewModel.squares.get(generated));
        notifyPropertyChanged(BR.squares);
    }
    public void setterSquare(int id,Square square){
        squares.set(id,square);
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
        setTime(66);
    }

    public void onClickItem(View v,int id){
        Log.i("Info clicked","id: "+id);
        logic.click(id);
        //setSquares(row,col);
    }
    public void onBackClick(View v){
        Log.i("Back clicked","True");
        /* TODO:back button!
        MenuFragment newFragment = new MenuFragment ();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, newFragment);
        transaction.commit();
        */
    }


    @BindingAdapter("load_image")
    public static void loadImage(ImageView view,Square square) {
            view.setImageResource(square.drawable);
    }

    private static class SingletonHolder {
        private static final GameViewModel INSTANCE = new GameViewModel();
    }
}
