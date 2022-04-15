package com.example.snake;

import android.content.Context;
import android.graphics.Color;
import android.widget.AbsoluteLayout;

import androidx.annotation.Nullable;

import com.google.android.material.slider.RangeSlider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class Snake extends Thing{
    boolean isStretch;
    Snake(Context context, AbsoluteLayout abs, int size, int speed, int x, int y, int width, int height, String type, DirectionE directionE, OrientationE orientationE){
        super(context, abs, size, speed, x, y, width, height, type, directionE, orientationE);
        isStretch = false;
    }
    public void stretch(){
        Block block = new Block(top.getContext(), top.position.x, top.position.y, top.width, top.height, top.directionE, top.orientationE);
        block.setBackgroundColor(Color.GREEN);
        blocks.add(block);
        size++;
        top.setBackgroundColor(Color.GREEN);
        top = block;
        top.setBackgroundColor(Color.RED);
        block.moveBlock(top.directionE, top.orientationE);
    }
}
