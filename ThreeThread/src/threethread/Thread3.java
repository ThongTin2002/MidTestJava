/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threethread;

/**
 *
 * @author ADMIN
 */
import java.time.LocalDate;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Thread3 implements Runnable {
    private final BlockingQueue<Student> queue;
    private final BlockingQueue<Integer> outputQueue;
    BlockingQueue<Result> resultQueue = new LinkedBlockingQueue<>();
    public Thread3(BlockingQueue<Student> queue, BlockingQueue<Integer> outputQueue) {
        this.queue = queue;
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Student digitSum = queue.take();
                System.out.println("Thread 3: Received digit sum: " + digitSum);
                Student student = queue.take();
                System.out.println("Thread 3: Processing student " + student.getId());

                // Kiểm tra tổng các chữ số trong ngày tháng năm sinh có phải là số nguyên tố không
                LocalDate dob = student.getDateOfBirth();
                int sum = calculateSumOfDigits(dob);
                boolean isPrime = isPrimeNumber(sum);
                System.out.println("Thread 3: Sum of digits in date of birth: " + sum);
                System.out.println("Thread 3: Is sum of digits prime? " + isPrime);
                ResultWriter resultWriter = new ResultWriter(resultQueue, "kq.xml");
                Thread resultWriterThread = new Thread(resultWriter);
                resultWriterThread.start();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int calculateSumOfDigits(LocalDate dob) {
        int sum = 0;
        int year = dob.getYear();
        int month = dob.getMonthValue();
        int day = dob.getDayOfMonth();

        // Tách từng chữ số của năm sinh và cộng vào sum
        while (year != 0) {
            sum += year % 10;
            year /= 10;
        }

        // Tách từng chữ số của tháng sinh và cộng vào sum
        while (month != 0) {
            sum += month % 10;
            month /= 10;
        }

        // Tách từng chữ số của ngày sinh và cộng vào sum
        while (day != 0) {
            sum += day % 10;
            day /= 10;
        }

        return sum;
    }

    private boolean isPrimeNumber(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}


