package harkor.addus.viewmodel;

import android.databinding.BaseObservable;
import android.util.Log;
import android.view.View;

import harkor.addus.interfaces.IFragMenager;

public class MenuViewModel extends BaseObservable{
IFragMenager iFragMenager;

    public MenuViewModel(IFragMenager iFragMenager){
        this.iFragMenager= iFragMenager;
    }
    public void onClickGame(View view){
        Log.i("ML","GameClick");
        iFragMenager.goGame();
        //MainViewModel.iFragMenager.goGame();
    }
    /*
     public static MenuViewModel getInstance(){
         return SingletonHolder.INSTANCE;

    }


    private static class SingletonHolder {
        private static final MenuViewModel INSTANCE = new MenuViewModel();
    }

    */
}
