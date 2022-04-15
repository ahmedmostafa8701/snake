package com.example.snake;

import android.content.Context;
import android.graphics.Color;
import android.widget.AbsoluteLayout;

class Block extends MovementItem{
    public Block(Context context, int x, int y, int width, int height, DirectionE directionE, OrientationE orientationE) {
        super(context, x, y, width, height, directionE, orientationE);
        setBackgroundColor(Color.GREEN);
    }
}
