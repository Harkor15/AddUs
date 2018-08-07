package harkor.addus.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import harkor.addus.BR;
import harkor.addus.view.MainActivity;

public class GameOverViewModel extends BaseObservable{

    private GameOverViewModel(){}
    int points;


    public static GameOverViewModel getInstance(){
        return GameOverViewModel.SingletonHolder.INSTANCE;
    }
    @Bindable
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
        notifyPropertyChanged(BR.points);
    }


    private static class SingletonHolder {
        private static final GameOverViewModel INSTANCE = new GameOverViewModel();
    }
}
