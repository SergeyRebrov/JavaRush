package com.javarush.test.level31.lesson02.home01;

import java.io.*;
import java.util.*;

/* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*/
public class Solution
{    public static void main(String[] args) throws IOException
    {
        File path = new File(/*args[0]*/"D:/test");
        File resultFileAbsolutePath = new File(/*args[1]*/"D:/3.txt");

        List<File> fileList = new ArrayList<>();

        Queue<File> queue = new ArrayDeque<>();
        queue.addAll(Arrays.asList(path.listFiles()));

        while (!queue.isEmpty()) {
            File file = queue.poll();
            if (file.isDirectory())
                queue.addAll(Arrays.asList(file.listFiles()));
            else
            {
                if (file.length() > 50)
                    file.delete();
                else
                    fileList.add(file);
            }
        }

        queue.addAll(Arrays.asList(path.listFiles()));

        while (!queue.isEmpty()) {
            File file = queue.poll();
            if (file.isDirectory() && file.listFiles().length == 0)
                file.delete();
        }

        Collections.sort(fileList, new Comparator<File>()
        {
            @Override
            public int compare(File o1, File o2)
            {
                return o1.getName().compareTo(o2.getName());
            }
        });


        File newFile = new File(resultFileAbsolutePath.getParent() + "allFilesContent.txt");
        resultFileAbsolutePath.renameTo(newFile);


        FileOutputStream fileOutputStream = new FileOutputStream(resultFileAbsolutePath);

        for (int i = 0; i < fileList.size(); i++) {
            if (i > 0)
                fileOutputStream.write('\n');

            FileInputStream fileInputStream = new FileInputStream(fileList.get(i));
            byte[] buffer = new byte[50];
            while (fileInputStream.available() > 0)
            {
                int length = fileInputStream.read(buffer);
                fileOutputStream.write(buffer, 0, length);
            }
            fileInputStream.close();
        }

        fileOutputStream.close();

    }



}
