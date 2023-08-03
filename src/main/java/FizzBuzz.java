import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FizzBuzz {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введiть n ");
        int n = scanner.nextInt();

        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        FizzBuzzClass fizzBuzz = new FizzBuzzClass(n, queue);

        Thread fizzThread = new Thread(() -> fizzBuzz.fizz());
        Thread outputThread = new Thread(() -> fizzBuzz.outputResults());
        Thread number = new Thread(() -> fizzBuzz.number());
        Thread buzz = new Thread(() -> fizzBuzz.buzz());
        Thread fizzbuzz = new Thread(() -> fizzBuzz.fizzBuzz());

        fizzThread.start();
        outputThread.start();
        number.start();
        buzz.start();
        fizzbuzz.start();

        while (!queue.isEmpty()) {
            String element = queue.poll();
            System.out.println(element);
        }

        try {
            outputThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    }

class FizzBuzzClass {
    private int n;
    private Queue queue;
    private int currenNumber = 1;

    public FizzBuzzClass(int n, Queue queue) {
        this.n = n;
        this.queue = queue;
    }

    public void fizz () {
        synchronized (this) {
            while (currenNumber <= n) {
                if (currenNumber % 3 == 0 && currenNumber % 5 != 0) {
                    queue.add("Fizz");
                    currenNumber++;
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void number () {
        synchronized (this) {
            while (currenNumber <= n) {
                if (currenNumber % 3 != 0 && currenNumber % 5 != 0) {
                    queue.add(currenNumber);
                    currenNumber++;
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void buzz () {
        synchronized (this) {
            while (currenNumber <= n) {
                if (currenNumber % 3 != 0 && currenNumber % 5 == 0) {
                    queue.add("Buzz");
                    currenNumber++;
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void fizzBuzz () {
        synchronized (this) {
            while (currenNumber <= n) {
                if (currenNumber % 3 == 0 && currenNumber % 5 == 0) {
                    queue.add("FizzBuzz");
                    currenNumber++;
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void outputResults() {
        while (currenNumber <= n) {
            while (!queue.isEmpty()) {
                Object element = queue.poll();
                System.out.println(element);
            }

        }
    }
}
