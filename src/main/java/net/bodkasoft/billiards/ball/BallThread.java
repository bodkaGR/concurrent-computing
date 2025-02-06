package net.bodkasoft.billiards.ball;

public class BallThread extends Thread {

    private Ball ball;

    public BallThread(Ball ball){
        this.ball = ball;
    }

    @Override
    public void run(){
        try{
            for(int i = 1; i < 100000; i++){
                if (ball.isActive()) {
                    ball.move();
                    System.out.println("Thread name = " + Thread.currentThread().getName());
                    Thread.sleep(5);
                }
            }
        } catch(InterruptedException ex){

        }
    }
}
