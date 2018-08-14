package harkor.addus.viewmodel;

import android.os.CountDownTimer;
import android.util.Log;

import java.util.Random;

import harkor.addus.interfaces.IGame;
import harkor.addus.interfaces.ISoundsPlay;
import harkor.addus.model.Square;

public class Logic{
    IGame iGame;
    ISoundsPlay iSoundsPlay;
    Random random=new Random();
    boolean isClicked=false;
    Square clicked;
    int clickedId;
    CountDownTimer count=new CountDownTimer(60000,1000) {

        @Override
        public void onTick(long l) {
            //if(gameViewModel==null){gameViewModel=GameViewModel.getInstance();}
            Log.i("Tick"," ");
            setTime();
        }

        @Override
        public void onFinish() {
        Log.i("Game Over!","yes");
        iGame.timeout();
        }
    };

    public Logic(IGame iGame,ISoundsPlay iSoundsPlay){
        this.iGame=iGame;
        this.iSoundsPlay=iSoundsPlay;
        count.start();
        if(iSoundsPlay==null){
            Log.i("AddUs","nullSoundsPlayInterface");
        }else{
            Log.i("AddUs","ISOUNDS OK!");
        }

    }

    public void click(int id){
        Log.i("Clicked:",id+"");
        Square square=iGame.getSquare(id);
        if(isClicked){
            if(clickedId==id){
                isClicked=false;
                square.clicked=false;
                square.setImage();
                iGame.setterSquare(id,square);
            }else if(square.canBeACouple(clicked,square)){
                iGame.setterSquare(id,dualX(square));
                iGame.setterSquare(clickedId,resetX(clicked));
                isClicked=false;
                iSoundsPlay.playGood();
            }else{
                isClicked=false;
                clicked.clicked=false;
                clicked.setImage();
                iGame.setterSquare(clickedId,clicked);
                iSoundsPlay.playBad();
            }

        }else{
            clicked=square;
            isClicked=true;
            clickedId=id;
            square.clicked=true;
            square.setImage();
            iGame.setterSquare(id,square);

        }
    }
    private Square dualX(Square square){
        square.clicked=false;
        square.value*=2;
        square.setImage();
        addPoints(square.value);
        return square;
    }
    private Square resetX(Square square){
        square.clicked=false;
        square.value=1;
        square.color=random.nextInt(2)+1;
        square.setImage();
        return square;
    }
    void addPoints(int value){
        iGame.addPoints(value);
    }

    void setTime(){
        iGame.nextSecond();
    }


}
