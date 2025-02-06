package net.bodkasoft.billiards.ball;

import net.bodkasoft.billiards.Canvas;

public class BallThread extends Thread {

    private final Ball ball;
    private final Canvas canvas;

    public BallThread(Ball ball, Canvas canvas){
        this.ball = ball;
        this.canvas = canvas;
    }

    @Override
    public void run(){
        try{
            for(int i = 1; i < 100000; i++){
                if (ball.isActive()) {
                    ball.move(canvas.getPocket());
                    System.out.println("Thread name = " + Thread.currentThread().getName());
                    Thread.sleep(5);
                }
            }
        } catch(InterruptedException ex){

        }
    }
}
