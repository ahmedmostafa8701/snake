package com.example.snake;

import android.content.Context;
import android.media.Image;
import android.text.style.AbsoluteSizeSpan;
import android.util.DisplayMetrics;
import android.widget.AbsoluteLayout;

class MovementItem extends Item{
    DirectionE directionE;
    OrientationE orientationE;
    MovementItem(Context context, int x, int y, int width, int height, DirectionE directionE, OrientationE orientationE) {
        super(context,x, y, width, height);
        this.directionE = directionE;
        this.orientationE = orientationE;
    }
    public void moveBlock(DirectionE directionE, OrientationE orientationE){
        int d = Game.direction.get(directionE);
        int o = Game.orientation.get(orientationE);
        setPosition((position.x + width * ((o + 1) % 2) * d) % MainActivity.screenWidth, (position.y + height * (o % 2) * d) % MainActivity.screenHeight);
        this.directionE = directionE;
        this.orientationE = orientationE;
        if(position.x < 0){
            position.x = MainActivity.screenWidth - width;
        }
        else if(position.y < 0){
            position.y = MainActivity.screenHeight - height;
        }
        setPosition(position.x, position.y);
    }
}
