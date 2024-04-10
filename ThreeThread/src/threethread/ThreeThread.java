/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threethread;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreeThread {

    public static void main(String[] args) {
        // Khởi tạo BlockingQueue để chia sẻ dữ liệu giữa các luồng
        BlockingQueue<Student> queue = new LinkedBlockingQueue<>();
        BlockingQueue<Integer> outputQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Result> resultQueue = new LinkedBlockingQueue<>();
        // Khởi tạo ExecutorService để quản lý các luồng
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // Thêm Thread1 vào ExecutorService
        executor.submit(new Thread1(queue));
        // Chờ Thread1 kết thúc
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Thêm Thread2 vào ExecutorService
        executor = Executors.newFixedThreadPool(3);
        executor.submit(new Thread2(queue, outputQueue));
        // Chờ Thread2 kết thúc
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Thêm Thread3 vào ExecutorService
        executor = Executors.newFixedThreadPool(3);
        executor.submit(new Thread3(queue,outputQueue));
        // Dừng ExecutorService sau khi tất cả các luồng đã hoàn thành
        executor.shutdown();
    }

}
