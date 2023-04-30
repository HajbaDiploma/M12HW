import java.util.ArrayList;
import java.util.List;

public class HWTest {
    public static void main(String[] args) {
        NumberProcessorThread fizzProcessor = new NumberProcessorThread((n) -> {
            if (n % 3 == 0) {
                System.out.println("fizz");
            }
        });
        NumberProcessorThread buzzProcessor = new NumberProcessorThread((n) -> {
            if (n % 5 == 0) {
                System.out.println("buzz");
            }
        });
        NumberProcessorThread fizzBuzzProcessor = new NumberProcessorThread((n) -> {
            if (n % 5 == 0 && n % 3 == 0) {
                System.out.println("fizzbuzz");
            }
        });
        NumberProcessorThread defaultProcessor = new NumberProcessorThread((n) -> {
            if (n % 5 != 0 && n % 3 != 0) {
                System.out.println(n);
            }
        });

        List<NumberProcessorThread> threads = new ArrayList<>();
        threads.add(fizzBuzzProcessor);
        threads.add(fizzProcessor);
        threads.add(buzzProcessor);
        threads.add(defaultProcessor);

        for (NumberProcessorThread thread : threads) {
            thread.start();
        }

        for (int i = 1; i < 101; i++) {
            for (NumberProcessorThread thread : threads) {
                thread.process(i);
            }

            while (true) {
                int processedCount = 0;
                for (NumberProcessorThread thread : threads) {
                    if(thread.getIsProcessed().get()) {
                        processedCount++;
                    }
                }
                if (processedCount == threads.size()) {
                    break;
                }
            }
        }
    }
}
