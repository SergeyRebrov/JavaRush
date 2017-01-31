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

        linesAdd(stringsOne, stringsTwo);

        reader.close();
        scannerOne.close();
        scannerTwo.close();
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

    public static void linesAdd(List<String> one, List<String> two)
    {
        int numberOne = 0;
        int numberTwo = 0;
        while (true)
        {
            try
            {
                if (one.get(numberOne).equals(two.get(numberTwo)))
                {
                    lines.add(new LineItem(Type.SAME, one.get(numberOne)));
                    numberOne++;
                    numberTwo++;
                } else if (one.get(++numberOne).equals(two.get(numberTwo)))
                    lines.add(new LineItem(Type.REMOVED, one.get(numberOne - 1)));
                else
                {
                    lines.add(new LineItem(Type.ADDED, two.get(numberTwo)));
                    numberOne--;
                    numberTwo++;
                }
            }
            catch (IndexOutOfBoundsException e)
            {
                if (numberOne < one.size())
                    lines.add(new LineItem(Type.REMOVED, one.get(numberOne)));
                else if (numberTwo < two.size())
                    lines.add(new LineItem(Type.ADDED, two.get(numberTwo)));
                else
                    break;
                numberOne++;
                numberTwo++;
            }
        }
    }
}
