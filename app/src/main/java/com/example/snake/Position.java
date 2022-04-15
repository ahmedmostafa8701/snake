package com.example.snake;

class Position {
    int x;
    int y;
    Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    boolean isEqual(Position position){
        return position.x == x && position.y == y;
    }
}