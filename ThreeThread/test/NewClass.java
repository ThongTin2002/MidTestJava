/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class NewClass {
    private static BlockingQueue<Student> thread2Queue = new LinkedBlockingQueue<>();
    private static BlockingQueue<Student> thread3Queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            List<Student> students = readStudentsFromXml();
            calculateAgeAndEncode(students);
            try {
                for (Student student : students) {
                    thread2Queue.put(student);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    Student student = thread2Queue.take();
                    String encodedAge = encodeDigits(student.getAge());
                    student.setEncodedAge(encodedAge);
                    thread3Queue.put(student);
                    if (thread2Queue.isEmpty()) {
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            while (true) {
                try {
                    Student student = thread3Queue.take();
                    boolean isPrimeSum = checkSumIsPrime(student.getDateOfBirth());
                    student.setPrimeSum(isPrimeSum);
                    if (thread3Queue.isEmpty()) {
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Student> students = new ArrayList<>(thread3Queue);
        saveResultToXml(students);
    }

    private static List<Student> readStudentsFromXml() {
        // TODO: Đọc file student.xml và trả về danh sách các sinh viên
        return null;
    }

    private static void calculateAgeAndEncode(List<Student> students) {
        for (Student student : students) {
            String dateOfBirth = student.getDateOfBirth();
            // Tính toán tuổi
            // Lưu ý: Đây chỉ là một ví dụ đơn giản, cách tính tuổi cụ thể có thể khác nhau trong thực tế
            int age = 2024 - Integer.parseInt(dateOfBirth.substring(dateOfBirth.length() - 4));
            student.setAge(age);

            // Mã hoá chữ số đô
            String encodedAge = encodeDigits(age);
            student.setEncodedAge(encodedAge);
        }
    }

    private static String encodeDigits(int number) {
        StringBuilder encoded = new StringBuilder();
        String numberStr = String.valueOf(number);
        for (int i = 0; i < numberStr.length(); i++) {
            encoded.append((int) numberStr.charAt(i));
        }
        return encoded.toString();
    }

    private static boolean checkSumIsPrime(String dateOfBirth) {
        // TODO: Kiểm tra tổng các chữ số trong ngày tháng năm sinh có phải là số nguyên tố không
        return false;
    }

    private static void saveResultToXml(List<Student> students) {
        // TODO: Lưu kết quả vào file kq.xml
    }
}

class Student {
    private String id;
    private String name;
    private String address;
    private String dateOfBirth;
    private int age;
    private String encodedAge;
    private boolean primeSum;
	public int getAge() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setAge(int age2) {
		// TODO Auto-generated method stub
		
	}
	public void setPrimeSum(boolean isPrimeSum) {
		// TODO Auto-generated method stub
		
	}
	public String getDateOfBirth() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setEncodedAge(String encodedAge2) {
		// TODO Auto-generated method stub
		
	}

    // Constructors, getters, and setters
}
