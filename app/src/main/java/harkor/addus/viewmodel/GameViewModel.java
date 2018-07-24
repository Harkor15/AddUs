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
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.games.Game;

import harkor.addus.BR;
import harkor.addus.R;
import harkor.addus.model.Square;

public class GameViewModel extends BaseObservable {
    public static ObservableArrayList<Square> squares;
    public int points;
    public String sPoints;



    private GameViewModel(){}
    public static GameViewModel getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void setPoints(int points) {
        this.points = points;
        setsPoints(""+points);
    }
    @Bindable
    public String getsPoints() {
        return sPoints;
    }
    public void setsPoints(String sPoints) {
        this.sPoints = sPoints;
        Log.i("Info sPoints",sPoints);
        notifyPropertyChanged(BR.sPoints);
    }

    @Bindable
    public ObservableArrayList<Square> getSquares() {
        return squares;
    }

    public void setSquares(int doubled,int generated) {
        GameViewModel.squares.get(doubled).addIs(GameViewModel.squares.get(doubled),GameViewModel.squares.get(generated));
        notifyPropertyChanged(BR.squares);
    }

    public Square setterSquares(int id){
        return squares.get(id);
    }


    public void init(){
        squares=new ObservableArrayList<>();
        for(int i=1;i<=25;i++){
            squares.add(new Square());
        }
        setPoints(0);
    }

    public void onClickItem(View v,int id){
        Log.i("Info clicked","id: "+id);

        //setSquares(row,col);
    }


    @BindingAdapter("load_image")
    public static void loadImage(ImageView view,Square square) {
            view.setImageResource(square.drawable);
    }

    private static class SingletonHolder {
        private static final GameViewModel INSTANCE = new GameViewModel();
    }
}
