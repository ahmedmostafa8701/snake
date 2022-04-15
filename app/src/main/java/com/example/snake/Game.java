package com.example.snake;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.widget.AbsoluteLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

class Game {
    AbsoluteLayout abs;
    Context context;
    Snake snake;
    Food food;
    int score;
    TextView scoreCell;
    CountDownTimer count;
    static HashMap<DirectionE, Integer> direction;
    static HashMap <OrientationE, Integer>orientation;
    static {
        direction = new HashMap<DirectionE, Integer>();
        orientation = new HashMap<OrientationE, Integer>();
        direction.put(DirectionE.Up, -1);
        direction.put(DirectionE.Right, 1);
        direction.put(DirectionE.Down, 1);
        direction.put(DirectionE.Left, -1);
        orientation.put(OrientationE.Vertical, 1);
        orientation.put(OrientationE.Horizontal, 2);
    }

    public Game(Context context, AbsoluteLayout abs, TextView scoreCell) {
        this.context = context;
        this.abs = abs;
        score = 0;
        this.scoreCell = scoreCell;
        scoreCell.setText(String.valueOf(score));
        snake = new Snake(context, abs, 15, 100, 300, 300, 25, 25, "snake", DirectionE.Right, OrientationE.Horizontal);
        food = new Food(context, 400, 400, 35, 35);
        abs.addView(food, food.width, food.height);
        for (int i = 0; i < snake.size; i++) {
            abs.addView(snake.blocks.get(i), snake.blocks.get(i).width, snake.blocks.get(i).height);
        }
        startCount();
    }
    public  void moveSnake(){
        if(snake.directionE != snake.top.directionE && snake.orientationE == snake.top.orientationE){
            snake.directionE = snake.top.directionE;
            snake.orientationE = snake.top.orientationE;
        }
        Block selected;
        DirectionE previousDirection = snake.directionE, selectedDirectionE;
        OrientationE previousOrientation = snake.orientationE, selectedOrientationE;
        for (int i = 0; i < snake.size; i++) {
            selected = snake.blocks.get(snake.size - i - 1);
            selectedDirectionE = selected.directionE;
            selectedOrientationE = selected.orientationE;
            selected.moveBlock(previousDirection, previousOrientation);
            previousDirection = selectedDirectionE;
            previousOrientation = selectedOrientationE;
        }
        if(isEnd()){
            Toast.makeText(context, "you lose", Toast.LENGTH_LONG).show();
            count.cancel();
        }
        if(snake.top.isPassBy(food)){
            eat();
        }
    }
    public void eat(){
        food.putRandomly();
        while(food.isPassBy(snake)){
            food.putRandomly();
        }
        snake.stretch();
        abs.addView(snake.top, snake.top.width, snake.top.height);
        score += 50;
        scoreCell.setText(String.valueOf(score));
        count.cancel();
        snake.speed *= 0.99;
        startCount();
    }
    public boolean isEnd(){
        return snake.isPassBy(snake);
    }
    public void startCount(){
        count = new CountDownTimer(100000, snake.speed) {
            @Override
            public void onTick(long millisUntilFinished) {
                moveSnake();
            }

            @Override
            public void onFinish() {
                count.start();
            }
        }.start();
    }
}
