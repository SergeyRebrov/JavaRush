package com.javarush.test.level20.lesson10.home03;

import java.io.*;

/* Найти ошибки
Почему-то при сериализации/десериализации объекта класса B возникают ошибки.
Найдите проблему и исправьте ее.
Класс A не должен реализовывать интерфейсы Serializable и Externalizable.
Сигнатура класса В не содержит ошибку :)
Метод main не участвует в тестировании.
*/
public class Solution implements Serializable {
    public static class A {
        protected String name = "A";

        protected A() {}

        public A(String name) {
            this.name += name;
        }
    }

    public class B extends A implements Serializable {

        public B(String name) {
            super(name);
            this.name += name;
        }

        private void writeObject(ObjectOutputStream s) throws IOException
        {
            s.defaultWriteObject();
            s.writeUTF(name);
        }

        private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
            s.defaultReadObject();
            this.name = s.readUTF();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        B b = new Solution(). new B("B");
        System.out.println(b.name);

        ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream("D:/3.txt"));
        objectOutput.writeObject(b);

        ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream("D:/3.txt"));

        B bb = (B)objectInput.readObject();

        System.out.println(bb.name);

        objectInput.close();
        objectOutput.close();

    }
}
