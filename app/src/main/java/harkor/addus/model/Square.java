package harkor.addus.model;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.Random;

import harkor.addus.R;

public class Square {
    public int color;
    public int value;
    public int drawable;
    public boolean clicked;
    private Random random=new Random();

    public Square(){
        color=random.nextInt(2)+1;
        value=1;
        clicked=false;
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
     public void setImage(){
        switch(value){
            case 1: if(color==1){
                if(clicked){
                    drawable= R.drawable.square11c;
                }else{
                    drawable= R.drawable.square11;
                }
            }else{
                if(clicked){
                    drawable= R.drawable.square12c;
                }else{
                    drawable= R.drawable.square12;
                }
            }break;

            case 2: if(color==1){
                if(clicked){
                    drawable= R.drawable.square21c;
                }else{
                    drawable= R.drawable.square21;
                }
            }else{
                if(clicked){
                    drawable= R.drawable.square22c;
                }else{
                    drawable= R.drawable.square22;
                }
            }break;
            case 4: if(color==1){
                if(clicked){
                    drawable= R.drawable.square41c;
                }else{
                    drawable= R.drawable.square41;
                }
            }else{
                if(clicked){
                    drawable= R.drawable.square42c;
                }else{
                    drawable= R.drawable.square42;
                }
            }break;
            case 8: if(color==1){
                if(clicked){
                    drawable= R.drawable.square81c;
                }else{
                    drawable= R.drawable.square81;
                }
            }else{
                if(clicked){
                    drawable= R.drawable.square82c;
                }else{
                    drawable= R.drawable.square82;
                }
            }break;
            case 16: if(color==1){
                if(clicked){
                    drawable= R.drawable.square161c;
                }else{
                    drawable= R.drawable.square161;
                }
            }else{
                if(clicked){
                    drawable= R.drawable.square162c;
                }else{
                    drawable= R.drawable.square162;
                }
            }break;
            case 32: if(color==1){
                if(clicked){
                    drawable= R.drawable.square321c;
                }else{
                    drawable= R.drawable.square321;
                }
            }else{
                if(clicked){
                    drawable= R.drawable.square322c;
                }else{
                    drawable= R.drawable.square322;
                }
            }break;
            case 64: if(color==1){
                if(clicked){
                    drawable= R.drawable.square641c;
                }else{
                    drawable= R.drawable.square641;
                }
            }else{
                if(clicked){
                    drawable= R.drawable.square642c;
                }else{
                    drawable= R.drawable.square642;
                }
            }break;
        }
    }
}
