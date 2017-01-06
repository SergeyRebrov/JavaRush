package com.javarush.test.level19.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
оригинальный   редактированный    общий
file1:         file2:             результат:(lines)

строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка4                           REMOVED строка4
строка5        строка5            SAME строка5
строка0                           ADDED строка0
строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка5                           ADDED строка5
строка4        строка4            SAME строка4
строка5                           REMOVED строка5
*/

public class Solution
{
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Scanner scannerOne = new Scanner(new File(reader.readLine()));
        Scanner scannerTwo = new Scanner(new File(reader.readLine()));

        List<String> stringsOne = new ArrayList<String>();
        List<String> stringsTwo = new ArrayList<String>();

        while (scannerOne.hasNextLine())
            stringsOne.add(scannerOne.nextLine());

        while (scannerTwo.hasNextLine())
            stringsTwo.add(scannerTwo.nextLine());

        one:
        for (int i = 0; i < stringsOne.size(); i++)
        {
            String string1 = stringsOne.get(i);
            String string2 = null;

            for (int j = 0; j < stringsTwo.size(); j++)
            {
                string2 = stringsTwo.get(j);
                if (string1.equals(string2))
                {
                    lines.add(new LineItem(Type.SAME, string1));
                    continue one;
                }

                for (int y = 0; y < stringsOne.size(); y++)
                {
                    if (stringsOne.get(y).equals(string2))
                    {
                        continue;
                    }
                }
                lines.add(new LineItem(Type.ADDED, string2));
            }
            lines.add(new LineItem(Type.REMOVED, string1));
        }

        reader.close();
        scannerOne.close();
        scannerTwo.close();

        for (LineItem line : lines)
            System.out.println(line.type.toString() + " " + line.line);


    }


    public static enum Type
    {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem
    {
        public Type type;
        public String line;

        public LineItem(Type type, String line)
        {
            this.type = type;
            this.line = line;
        }
    }
}
