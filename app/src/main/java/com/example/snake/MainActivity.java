package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static int screenWidth;
    static int screenHeight;
    ImageButton up, down, right, left, restart;
    TextView scoreCell;
    Game game;
    boolean firstTime = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = 1400;
        AbsoluteLayout abs = (AbsoluteLayout) findViewById(R.id.Abs);
        scoreCell = (TextView) findViewById(R.id.score);
        game = new Game(this, abs, scoreCell);
        up = findViewById(R.id.up);
        down = findViewById(R.id.down);
        right = findViewById(R.id.right);
        left = findViewById(R.id.left);
        restart = findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent re = new Intent(MainActivity.this, MainActivity.class);
                finish();
                startActivity(re);
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.snake.changeDirection(DirectionE.Up, OrientationE.Vertical);
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.snake.changeDirection(DirectionE.Down, OrientationE.Vertical);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.snake.changeDirection(DirectionE.Right, OrientationE.Horizontal);
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.snake.changeDirection(DirectionE.Left, OrientationE.Horizontal);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        game.count.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!firstTime) {
            game.startCount();
        }
        firstTime = false;
    }
}