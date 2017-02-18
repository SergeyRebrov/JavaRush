package com.javarush.test.level31.lesson06.home01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution
{
    public static void main(String[] args) throws IOException
    {
        Path file = Paths.get(args[0]);
        Path zipFile = Paths.get(args[1]);

        Path tmp = Files.createTempFile(zipFile.getParent(), "tmp", "_abrakadabra");
        ZipOutputStream tmpZip = new ZipOutputStream(new FileOutputStream(tmp.toFile()));

        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile.toFile()));
        ZipEntry entry;
        while ((entry = zipInputStream.getNextEntry()) != null)
        {
            String name = entry.getName();
            if (entry.getName().contains("/"))
                name = name.substring(name.lastIndexOf("/") + 1, name.length());

            if (name.equals(file.getFileName().toString()))
            {
                tmpZip.putNextEntry(new ZipEntry("new/" + file.toFile().getName()));
                Files.copy(file, tmpZip);
                continue;
            }
            tmpZip.putNextEntry(new ZipEntry(entry.getName()));
            byte[] buffer = new byte[1 * 1024 * 1024];
            int len = 0;
            while ((len = zipInputStream.read(buffer)) > 0)
            {
                tmpZip.write(buffer, 0, len);
            }
            tmpZip.closeEntry();
        }

        zipInputStream.close();
        tmpZip.close();

        Files.delete(zipFile);
        Files.move(tmp, zipFile);
    }
}
