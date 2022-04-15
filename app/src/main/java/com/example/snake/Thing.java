package com.example.snake;

import android.content.Context;
import android.graphics.Color;
import android.widget.AbsoluteLayout;

import java.util.ArrayList;

class Thing {
    int size;
    int speed;
    DirectionE directionE;
    OrientationE orientationE;
    ArrayList<Block> blocks;
    Block top;
    String type;
    Thing(Context context, AbsoluteLayout abs, int size, int speed, int x, int y, int width, int height, String type, DirectionE directionE, OrientationE orientationE){
        this.directionE = directionE;
        this.orientationE = orientationE;
        this.type = type;
        if(size < 2 * MainActivity.screenWidth / width + 2 * MainActivity.screenHeight / height - 4){
            this.size = size;
        }
        else{
            this.size = 2 * MainActivity.screenWidth / width + 2 * MainActivity.screenHeight / height - 5;
            size = this.size;
        }
        blocks = new ArrayList<Block>();
        this.speed = speed;
        // if sequence = 0 the remain blocks will be put left of the first block else they will be uptoward it
        for (int i = 0; i < size; i++) {
            blocks.add(new Block(context,x + width * i, y , width, height, directionE, orientationE));
        }
        top = blocks.get(size - 1);
        top.setBackgroundColor(Color.RED);
    }
    boolean changeDirection(DirectionE directionE, OrientationE orientationE){
        if(orientationE != this.orientationE){
            this.directionE = directionE;
            this.orientationE = orientationE;
            return true;
        }
        else{
            return  false;
        }
    }
    boolean isPassBy(Thing thing){
        return top.isPassBy(thing);
    }
}
