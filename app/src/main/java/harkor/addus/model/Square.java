package harkor.addus.model;

import android.graphics.drawable.Drawable;

import java.util.Random;

import harkor.addus.R;

public class Square {
    public int color;
    public int value;
    public int drawable;
    public int drawableClicked;
    private Random random=new Random();

    public Square(){
        color=random.nextInt(2)+1;
        value=1;
        setImage();
    }

    public Boolean canBeACouple(Square she,Square he){
        if(she.color==he.color&&she.value==he.value)
            return true;
        return false;
    }
    public void addIs(Square she,Square he){
        she.color=random.nextInt(2)+1;
        she.value=1;
        he.value*=2;
        she.setImage();
        he.setImage();
    }
    private void setImage(){
        switch(value){
            case 1: if(color==1){
                drawable= R.drawable.square11;
                //drawableClicked= R.drawable.square_clicked11;
            }else{
                drawable= R.drawable.square12;
                //drawableClicked= R.drawable.square_clicked12;
            }break;

            case 2: if(color==1){
                //drawable= R.drawable.square21;
                //drawableClicked= R.drawable.square_clicked21;
            }else{
                //drawable= R.drawable.square22;
                //drawableClicked= R.drawable.square_clicked22;
            }break;

            case 4: if(color==1){
                //drawable= R.drawable.square41;
                //drawableClicked= R.drawable.square_clicked41;
            }else{
                //drawable= R.drawable.square42;
                //drawableClicked= R.drawable.square_clicked42;
            }break;
        }
    }
}
