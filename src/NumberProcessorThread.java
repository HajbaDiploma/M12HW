import java.util.concurrent.atomic.AtomicBoolean;

public class NumberProcessorThread extends Thread {
    private int number;

    private AtomicBoolean isProcessed = new AtomicBoolean(true);

    private NumberProcessor processor;

    public NumberProcessorThread(NumberProcessor processor) {
        this.processor = processor;
    }

    public void process(int a) {
        this.number = a;
        isProcessed.set(false);
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (isProcessed.get()) {
                continue;
            }

            processor.process(number);
            isProcessed.set(true);
        }
    }

    public AtomicBoolean getIsProcessed() {
        return isProcessed;
    }
}
