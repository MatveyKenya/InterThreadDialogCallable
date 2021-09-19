package ru.matveykenya;

import java.util.concurrent.Callable;

public class MyWork implements Callable<Integer> {

    private final int sleepTime;
    private final int countIteration;
    private int counter;
    private final String name;

    public MyWork(String name, int sleepTime, int countIteration) {
        this.name = name;
        this.sleepTime = sleepTime;
        this.countIteration = countIteration;
    }

    @Override
    public Integer call() throws Exception {
        try {
            for (counter = 0; counter < countIteration; counter++){
                Thread.sleep(sleepTime);
                System.out.println("Я " + name + " в потоке " + Thread.currentThread().getId() + " - делаю итерацию № " + counter);
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
        } finally {
            System.out.printf("%s завершен\n", name);

        }
        return counter;
    }
}
