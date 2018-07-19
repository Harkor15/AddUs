package harkor.addus.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.res.Resources;
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


import harkor.addus.R;
import harkor.addus.model.Square;

public class GameViewModel extends ViewModel {
    public static ObservableArrayList<Square> squares;
    private static Context context;

    public void init(){
        squares=new ObservableArrayList<>();
        for(int i=1;i<=25;i++){
            squares.add(new Square());
        }
    }
    public static int getDrawable(int id){
        return squares.get(id).drawable;
    }



    public void onClickItem(View v,int row, int col){
        Log.d("clicked","row: "+row+" col: "+col);
        Toast.makeText(context,"Good!",Toast.LENGTH_SHORT).show();
    }
    @BindingAdapter("load_image")
    public static void loadImage(ImageView view,int imageId) {
        view.setImageResource(getDrawable(imageId));
    }

}
