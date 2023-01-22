package thread;

/**
 * Hello world!
 *
 */
public class MainApplication
{
    public static void main( String[] args )
    {
        Thread thread = new Thread(() -> {
           try {
               Thread.sleep(3_000);
               System.out.println("Run Thread");
           }catch (InterruptedException e){
               e.printStackTrace();
           }
        });

        thread.setDaemon(true);
        thread.start();
    }
}