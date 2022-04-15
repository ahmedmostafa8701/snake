package com.example.snake;

import android.content.Context;
import android.widget.AbsoluteLayout;
import android.widget.TextView;

import java.util.Random;

class Item extends androidx.appcompat.widget.AppCompatTextView {
    Position position;
    int width;
    int height;
    Item(Context context, int x, int y, int width, int height) {
        super(context);
        position = new Position(x, y);
        this.setX(x);
        this.setY(y);
        this.width = width;
        this.height = height;
    }
    void setPosition(int x, int y){
        position.x = x;
        position.y = y;
        this.setX(x);
        this.setY(y);
    }
    public void putRandomly(){
        Random random = new Random();
        this.setPosition(random.nextInt(MainActivity.screenWidth / width) * width, random.nextInt(MainActivity.screenHeight / height) * height);
    }
    boolean isPointInside(int x, int y){
        if(x > position.x && x < position.x + width && y > position.y && y < position.y + height){
            return true;
        }
/*        if(x > position.x && x < position.x + width && y > position.y && y < position.y + height){
            return true;
        }*/
        return false;
    }
    boolean isCornersInsideMe(Item item){
        return (
                isPointInside(item.position.x, item.position.y) ||
                        isPointInside(item.position.x + item.width, item.position.y) ||
                        isPointInside(item.position.x, item.position.y + item.height) ||
                        isPointInside(item.position.x + item.width, item.position.y + item.height));
    }
    boolean isPassBy(Item item){
        return isCornersInsideMe(item) || item.isCornersInsideMe(this) || isIdentity(item) || isMoveOn(item) || item.isMoveOn(this);
    }
    boolean isPassBy(Thing thing){
        int i = 0;
        while (i < thing.size) {
            if(thing.blocks.get(thing.size - i - 1) != this && isPassBy(thing.blocks.get(thing.size - i - 1))){
                return true;
            }
            i++;
        }
        return false;
    }
    boolean isMoveOn(Item item){
        return (item.position.x == position.x && item.position.x + item.width == position.x + width &&
                item.position.y > position.y && item.position.y < position.y + height) ||
                (item.position.y == position.y && item.position.y + item.height == position.y + height &&
                item.position.x > position.x && item.position.x < position.x + width);
    }
    boolean isIdentity(Item item){
        return width == item.width && height == item.height && position.isEqual(item.position);
    }
}
