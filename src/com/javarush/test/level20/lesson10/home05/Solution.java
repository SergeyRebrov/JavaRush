package com.javarush.test.level20.lesson10.home05;

import java.io.*;
import java.util.logging.Logger;

/* Сериализуйте Person
Сериализуйте класс Person стандартным способом. При необходимости поставьте полям модификатор transient.
*/
public class Solution {

    public static class Person implements Serializable {
        String firstName;
        String lastName;
        transient String fullName;
        transient final String greetingString = "Hello, ";
        String country;
        Sex sex;
        transient PrintStream outputStream;
        transient Logger logger;

        Person(String firstName, String lastName, String country, Sex sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.country = country;
            this.sex = sex;
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }

        private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException
        {
            s.defaultReadObject();
            fullName = String.format("%s, %s", lastName, firstName);
            outputStream = System.out;
            logger = Logger.getLogger(String.valueOf(Person.class));
        }

        private void writeObject(ObjectOutputStream s) throws IOException
        {
            s.defaultWriteObject();
        }

    }

    enum Sex {
        MALE,
        FEMALE
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        Person person = new Person("Ivan", "Ivanov", "Russia", Sex.MALE);
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:/3.txt"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:/3.txt"));

        objectOutputStream.writeObject(person);

        Person newPerson = (Person) objectInputStream.readObject();

        System.out.println(person.logger.toString());
        System.out.println(newPerson.logger.toString());


        objectInputStream.close();
        objectOutputStream.close();

    }
}
