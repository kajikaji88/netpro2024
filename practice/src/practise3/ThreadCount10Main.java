package practise3;

public class ThreadCount10Main {
    public static void main(String[] args){
        for(int i=1; i<=10; i++){
            Thread thread = new Thread(new CountTenRunnableImpleC(i , i));
            thread.start();
        }
    }
}
