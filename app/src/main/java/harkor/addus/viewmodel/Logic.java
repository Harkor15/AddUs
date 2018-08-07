package harkor.addus.viewmodel;

import android.os.CountDownTimer;
import android.util.Log;

import java.util.Random;

import harkor.addus.model.Square;

public class Logic {
    GameViewModel gameViewModel=GameViewModel.getInstance();
    Random random=new Random();
    boolean isClicked=false;
    Square clicked;
    int clickedId;
    CountDownTimer count=new CountDownTimer(60000,1000) {

        @Override
        public void onTick(long l) {
            if(gameViewModel==null){gameViewModel=GameViewModel.getInstance();}
            //gameViewModel.setTime(time);
            Log.i("Tick"," ");
            setTime();
        }

        @Override
        public void onFinish() {
        Log.i("Game Over!","yes");
        gameViewModel.timeout();
        }
    };

    public Logic(){
        Log.i("Logging","Instacne");

        count.start();

    }

    public void click(int id){
        Log.i("Clicked:",id+"");
        Square square=gameViewModel.squares.get(id);
        if(isClicked){
            if(clickedId==id){
                isClicked=false;
                square.clicked=false;
                square.setImage();
                //Log.i("try","hard try");
                gameViewModel.setterSquare(id,square); //?!?!?!?!?!?!?!?!
            }else if(square.canBeACouple(clicked,square)){
                gameViewModel.setterSquare(id,dualX(square));
                gameViewModel.setterSquare(clickedId,resetX(clicked));
                isClicked=false;
            }else{
                Log.i("can","NO!!!!");
                isClicked=false;
                clicked.clicked=false;
                clicked.setImage();
                gameViewModel.setterSquare(clickedId,clicked);
            }

        }else{
            clicked=square;
            isClicked=true;
            clickedId=id;
            square.clicked=true;
            square.setImage();
            gameViewModel.setterSquare(id,square);

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
        Log.i("Points+",value+"");
        gameViewModel.addPoints(value);
    }
    void setTime(){
        gameViewModel.nextSecond();
    }

}
