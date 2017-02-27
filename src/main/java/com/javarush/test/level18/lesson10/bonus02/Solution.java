package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        File file = new File(reader.readLine());
        reader.close();
        FileReader fileReader = new FileReader(file);
        reader = new BufferedReader(fileReader);

        StringBuilder string = null;
        int maxId = 0;
        while (reader.ready())
        {
            string = new StringBuilder(reader.readLine());
            string.setLength(8);
            try
            {
                int id = Integer.parseInt(string.toString().replaceAll("[^0-9]", ""));
                if (maxId < id)
                    maxId = id;
            } catch (NumberFormatException ignore)
            {}

        }


        int id = maxId;
        if (string != null)
            id = maxId + 1;

        StringBuilder productName = new StringBuilder();
        for (int i = 1; i < args.length - 2; i++)
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

        FileWriter fileWriter = new FileWriter(file, true);

        if (string != null)
            fileWriter.append('\r').append('\n');

        fileWriter.append(line);

        fileWriter.close();
        reader.close();
        fileReader.close();

    }
}
