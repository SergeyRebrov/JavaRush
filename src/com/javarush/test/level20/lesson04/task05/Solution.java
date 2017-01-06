package com.javarush.test.level20.lesson04.task05;

import java.io.*;

/* Как сериализовать что-то свое?
Сделайте так, чтобы сериализация класса Object была возможной
*/
public class Solution
{
    public static class Object implements Serializable
    {
        public String string1;
        public String string2;
    }

    public static int countStrings;

    public static class String implements Serializable
    {
        private final int number;

        public String()
        {
            number = ++countStrings;
        }

        public void print()
        {
            System.out.println("string #" + number);
        }
    }

    public static void main(java.lang.String[] args) throws IOException, ClassNotFoundException
    {
        FileOutputStream fileOutputStream = new FileOutputStream("D:/3.txt");
        FileInputStream fileInputStream = new FileInputStream("D:/3.txt");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        Object object = new Object();
        object.string1 = new String();
        object.string2 = new String();
        object.string1.print();
        object.string2.print();

        objectOutputStream.writeObject(object);


        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object1 = (Object)objectInputStream.readObject();
        object1.string1.print();
        object1.string2.print();
    }
}
