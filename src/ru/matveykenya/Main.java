package ru.matveykenya;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("Главный поток Старт!");
        final ExecutorService threadPool = Executors.newFixedThreadPool(4);

        MyWork myWork1 = new MyWork( "Игорек", 1500, 14);
        MyWork myWork2 = new MyWork( "Петька", 3000, 6);
        MyWork myWork3 = new MyWork( "Наташка", 2000, 5);
        MyWork myWork4 = new MyWork( "Зинка", 1000, 20);

        // Отправляем задачи на выполнение в пул потоков - получаем обратно объекты Future
        final List<Future<Integer>> tasks = threadPool.invokeAll(Arrays.asList(myWork1, myWork2, myWork3, myWork4));

        // Получаем все результаты
        System.out.println("Печатаем все счетчики задач:");
        tasks.forEach(task -> {
            try {
                System.out.println(task.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        System.out.println("находим самую быструю задачу");
        int result = threadPool.invokeAny(Arrays.asList(myWork1, myWork2, myWork3, myWork4));
        System.out.println("Результат выполнения самой быстрой задачи - " + result);

        //Завершаем работу пула потоков
        threadPool.shutdown();

    }
}
