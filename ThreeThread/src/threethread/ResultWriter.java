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
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class ResultWriter implements Runnable {
    private final BlockingQueue<Result> queue;
    private final String fileName;

    public ResultWriter(BlockingQueue<Result> queue, String fileName) {
        this.queue = queue;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<Results>\n");
            
            while (true) {
                Result result = queue.take(); // Lấy kết quả từ hàng đợi
                if (result == null) {
                    break; // Kết thúc khi không còn kết quả nào
                }
                
                // Ghi dữ liệu vào file kq.xml
                writer.write("<Student>\n");
                writer.write("    <Id>" + result.getId() + "</Id>\n");
                writer.write("    <Name>" + result.getName() + "</Name>\n");
                writer.write("    <Age>" + result.getAge() + "</Age>\n");
                writer.write("    <DigitSum>" + result.getDigitSum() + "</DigitSum>\n");
                writer.write("    <IsPrime>" + result.isPrime() + "</IsPrime>\n");
                writer.write("</Student>\n");
            }
            
            writer.write("</Results>");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

