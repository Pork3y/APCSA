package GraphicsTest;
public class Stopwatch{

    private long elapsedTime;
    private long startTime;
    private boolean isRunning;

    public Stopwatch(){
        reset();
    }

    public void start(){
        if(!isRunning){
            isRunning = true;
            startTime = System.currentTimeMillis();
        }
    }

    public void stop(){
        if(isRunning){
            isRunning = false;
            elapsedTime += System.currentTimeMillis() - startTime;
        }
    }

    public void reset(){
        isRunning = false;
        elapsedTime = 0;
    }

    public long getElapsedTime(){
        if(isRunning){
            return elapsedTime + System.currentTimeMillis() - startTime;
        } else{
            return elapsedTime;
        }
    }
}