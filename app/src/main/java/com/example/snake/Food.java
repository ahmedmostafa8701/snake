package com.example.snake;

import android.content.Context;
import android.graphics.Color;
import android.widget.AbsoluteLayout;

import java.util.Random;

class Food extends Item{
    Food(Context context, int x, int y, int width, int height){
        super(context, x, y, width, height);
        setBackgroundColor(Color.YELLOW);
    }
}
