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
            while (!canvas.isPocketed(ball)) {
                ball.move(canvas.getWidth(), canvas.getHeight());
                canvas.repaint();
                System.out.println("Thread name = " + Thread.currentThread().getName());
                Thread.sleep(5);
            }
        } catch(InterruptedException ex){
            System.out.println("Thread interrupted: " + ex.getMessage());
        }
    }
}
