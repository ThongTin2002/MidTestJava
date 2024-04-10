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
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.concurrent.BlockingQueue;

public class Thread1 implements Runnable {
    private final BlockingQueue<Student> queue;

    public Thread1(BlockingQueue<Student> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            // Đọc file student.xml
            File file = new File("student.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            // Lấy danh sách các sinh viên từ file XML và đưa vào BlockingQueue cho Thread 2 xử lý
            NodeList nodeList = doc.getElementsByTagName("Student");
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getAttribute("Id");
                    String name = element.getElementsByTagName("Name").item(0).getTextContent();
                    String address = element.getElementsByTagName("Address").item(0).getTextContent();
                    String dobString = element.getElementsByTagName("DateOfBirth").item(0).getTextContent();
                    LocalDate dob = LocalDate.parse(dobString);
                    Student student = new Student(id, name, address, dob);
                    queue.put(student); // Thêm sinh viên vào BlockingQueue
                }
            }
            System.out.println("Thread 1: All students have been read and added to the queue.");
        } catch (ParserConfigurationException | SAXException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
