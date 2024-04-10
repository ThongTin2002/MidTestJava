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
import java.time.Period;
import java.util.concurrent.BlockingQueue;

public class Thread2 implements Runnable {
    private final BlockingQueue<Student> inputQueue;
    private final BlockingQueue<Integer> outputQueue; // BlockingQueue để gửi kết quả đến Thread3

    public Thread2(BlockingQueue<Student> inputQueue, BlockingQueue<Integer> outputQueue) {
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }


    @Override
    public void run() {
        try {
            while (true) {
                Student student = inputQueue.take();
                System.out.println("Thread 2: Processing student " + student.getId());

                // Tính tuổi của học sinh
                LocalDate today = LocalDate.now();
                LocalDate dob = student.getDateOfBirth();
                Period period = Period.between(dob, today);
                int years = period.getYears();
                int months = period.getMonths();
                int days = period.getDays();
                System.out.println("Thread 2: Student's age: " + years + " years, " + months + " months, " + days + " days");

                // Mã hoá tuổi của học sinh thành chữ số đơn vị
                int sum = years + months + days;
                int digitSum = sum % 10; // Chỉ lấy chữ số hàng đơn vị
                System.out.println("Thread 2: Encrypted digit: " + digitSum);

                // Gửi kết quả mã hoá chữ số đơn vị đến Thread 3
                outputQueue.put(digitSum);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

