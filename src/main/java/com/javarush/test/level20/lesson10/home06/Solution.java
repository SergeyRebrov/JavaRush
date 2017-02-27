package com.javarush.test.level20.lesson10.home06;

import java.io.*;

/* Запрет сериализации
Запретите сериализацию класса SubSolution используя NotSerializableException.
Сигнатуры классов менять нельзя
*/
public class Solution implements Serializable {
    public static class SubSolution extends Solution {
        private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException
        {
            throw new NotSerializableException();
        }

        private void writeObject(ObjectOutputStream s) throws IOException
        {
            throw new NotSerializableException();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        SubSolution subSolution = new SubSolution();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:/3.txt"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:/3.txt"));

        objectOutputStream.writeObject(subSolution);

        SubSolution newSub = (SubSolution) objectInputStream.readObject();

        System.out.println(subSolution.toString());
        System.out.println(newSub.toString());

        objectInputStream.close();
        objectOutputStream.close();
    }
}
