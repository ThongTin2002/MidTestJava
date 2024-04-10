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

public class Result {
    private String id;
    private String name;
    private int age;
    private int digitSum;
    private boolean isPrime;

    public Result(String id, String name, int age, int digitSum, boolean isPrime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.digitSum = digitSum;
        this.isPrime = isPrime;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getDigitSum() {
        return digitSum;
    }

    public boolean isPrime() {
        return isPrime;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", digitSum=" + digitSum +
                ", isPrime=" + isPrime +
                '}';
    }
}
