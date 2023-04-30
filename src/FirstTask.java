import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FirstTask {

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);


        service.scheduleAtFixedRate(
                () -> {
                    System.out.println("Минуло 5 секунд");
                },
                5,
                5,
                TimeUnit.SECONDS
        );
        long start = System.currentTimeMillis();

        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            long time = System.currentTimeMillis();
            System.out.println((time - start)%1000*0.1);
        }
    }

}
