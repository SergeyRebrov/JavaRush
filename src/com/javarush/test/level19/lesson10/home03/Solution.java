package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution
{
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException
    {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));

        String line;
        while ((line = reader.readLine()) != null)
        {
            String[] symbols = line.split(" ");
            String name = "";
            for (int i = 0; i < symbols.length - 3; i++)
            {
                if (!name.equals(""))
                    name += " ";
                name += symbols[i];
            }
            String bd = symbols[symbols.length - 3] + "." + symbols[symbols.length - 2] + "." + symbols[symbols.length - 1];

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date birthday = dateFormat.parse(bd);
            PEOPLE.add(new Person(name, birthday));
        }

        reader.close();

    }

}
