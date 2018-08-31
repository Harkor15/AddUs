package harkor.addus.view;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;


import harkor.addus.R;

public class SoundsPlayer {
    MediaPlayer goodSound;
    MediaPlayer badSound;
    public SoundsPlayer(Context context){
        goodSound=MediaPlayer.create(context, R.raw.sound_good);
        badSound=MediaPlayer.create(context, R.raw.sound_bad);
    }
    public void playGood(){
        goodSound.start();
    }
    public void playBad(){
        badSound.start();
    }
}
