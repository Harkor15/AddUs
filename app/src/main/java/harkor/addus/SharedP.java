package harkor.addus;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedP {
    //private Context context;
    private SharedPreferences sharedPreferences;

    public SharedP(Context context){
        //this.context=context;
        sharedPreferences=context.getSharedPreferences("harkor.addus",Context.MODE_PRIVATE);
    }
    public int getBest(){
        return sharedPreferences.getInt("score",0);
    }
    public boolean trySetBest(int result){
        int best=sharedPreferences.getInt("score",0);
        if (best<result){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("score",result);
            editor.commit();
            return  true;
        }
        return false;
    }



}
