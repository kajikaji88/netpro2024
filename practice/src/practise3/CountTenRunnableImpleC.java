package practise3;

public class CountTenRunnableImpleC implements Runnable{
    private int interval;
    private int threadId;

    public CountTenRunnableImpleC(int threadId, int interval){
        this.threadId = threadId;
        this.interval = interval;
    }

    public void run(){
        for(int i=1; i<=10 ;i++){
            try{
            Thread.sleep(interval * 1000);
            }catch(InterruptedException e){
                System.err.println("Thread " + threadId + " was interrupted.");
            }
            System.out.println("Thread ID: " + threadId + ", Counter: " + i);
        }
    }

}
