package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> filesName = new ArrayList<>();

        Queue<File> queue = new ArrayDeque<>();
        queue.addAll(Arrays.asList(new File(root).listFiles()));

        while (!queue.isEmpty())
        {
            File file = queue.poll();
            if (file.isDirectory())
                queue.addAll(Arrays.asList(file.listFiles()));
            else
                filesName.add(file.getAbsolutePath());
        }

        return filesName;
    }
}
