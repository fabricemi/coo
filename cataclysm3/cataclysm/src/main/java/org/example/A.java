package org.example;

public class A {
    int num;

    public A(int num) {
        this.num = num;
    }

    public A() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "A{" +
                "num=" + num +
                '}';
    }
}
