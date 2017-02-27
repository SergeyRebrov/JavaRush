package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        File file = new File(reader.readLine());
        reader.close();
        FileReader fileReader = new FileReader(file);
        reader = new BufferedReader(fileReader);

        ArrayList<String> strings = new ArrayList<>();

        while (reader.ready())
        {
            String str = reader.readLine();
            StringBuilder string = new StringBuilder(str);
            string.setLength(8);
            try
            {
                int id = Integer.parseInt(string.toString().replaceAll("[^0-9]", ""));
                if ("-d".equals(args[0]))
                {
                    if (id == Integer.parseInt(args[1]))
                        continue;
                }

                if ("-u".equals(args[0]))
                {
                    if (id == Integer.parseInt(args[1]))
                    {
                        StringBuilder productName = new StringBuilder();
                        for (int i = 2; i < args.length - 2; i++)
                            productName.append(args[i]).append(" ");
                        while (productName.length() < 30)
                            productName.append(" ");
                        productName.setLength(30);

                        StringBuilder price = new StringBuilder(args[args.length - 2]);
                        while (price.length() < 8)
                            price.append(" ");
                        price.setLength(8);

                        StringBuilder quantity = new StringBuilder(args[args.length - 1]);
                        while (quantity.length() < 4)
                            quantity.append(" ");
                        quantity.setLength(4);

                        StringBuilder line = new StringBuilder();
                        line.append(id);
                        while (line.length() < 8)
                            line.append(" ");
                        line.append(productName).append(price).append(quantity);
                        str = line.toString();
                    }
                }
                strings.add(str);
            }
            catch (NumberFormatException ignore)
            {
            }

        }


        FileWriter fileWriter = new FileWriter(file);

        for (int i = 0; i < strings.size(); i++)
        {
            if (i != 0)
                fileWriter.write("\r\n");
            fileWriter.write(strings.get(i));
        }

        fileWriter.close();
        reader.close();
        fileReader.close();
    }
}
