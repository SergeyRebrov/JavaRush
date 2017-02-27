package com.javarush.test.level33.lesson05.home03;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

/* Десериализация JSON объекта
НЕОБХОДИМО: подключенные библиотеки Jackson Core, Bind и Annotation версии 2.6.1

В метод convertFromJsonToNormal первым параметром приходит имя файла, который содержит один ДЖЕЙСОН объект.
Вторым параметром приходит имя класса, объект которого находится в файле.
Метод convertFromJsonToNormal должен вычитать объект из файла, преобразовать его из JSON и вернуть его.
*/
public class Solution {

    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        /*FileInputStream inputStream = new FileInputStream(fileName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while (inputStream.available() > 0)
        {
            int len = inputStream.read(buffer);
            baos.write(buffer, 0, len);
        }
        StringReader stringReader = new StringReader(baos.toString());*/
        ObjectMapper mapper = new ObjectMapper();

        /*Object t = mapper.readValue(stringReader, clazz);*/

        T t = mapper.readValue(new File(fileName), clazz);
        return t;
    }

    public static void main(String[] args) throws IOException
    {
        Cat cat = new Cat();
        cat.name = "Murka";
        cat.age = 5;
        cat.weight = 4;

        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, cat);
        FileWriter fileWriter = new FileWriter("D:/3.txt");
        fileWriter.write(writer.toString());
        fileWriter.close();

        System.out.println(convertFromJsonToNormal("D:/3.txt", Cat.class));
    }

    @JsonAutoDetect
    static class Cat
    {
        public String name;
        public int age;
        public int weight;

        Cat()
        {
        }

        @Override
        public String toString()
        {
            return "Cat{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", weight=" + weight +
                    '}';
        }
    }
}
