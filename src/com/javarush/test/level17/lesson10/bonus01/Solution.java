package com.javarush.test.level17.lesson10.bonus01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution
{
    public static List<Person> allPeople = new ArrayList<Person>();

    static
    {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException
    {
        String[] param = args;

        if (param[0].equals("-c"))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date bd = sdf.parse(param[3]);
            if (param[2].equals("м"))
            {
                allPeople.add(Person.createMale(param[1], bd));
            } else
            {
                allPeople.add(Person.createFemale(param[1], bd));
            }
            System.out.println(allPeople.size() - 1);
        } else
        {
            if (param[0].equals("-u"))
            {
                Person person = allPeople.get(Integer.parseInt(param[1]));
                person.setName(param[2]);
                person.setSex(param[3].equals("м") ? Sex.MALE : Sex.FEMALE);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date bd = sdf.parse(param[4]);
                person.setBirthDay(bd);
            } else
            {
                if (param[0].equals("-d"))
                {
                    Person person = allPeople.get(Integer.parseInt(param[1]));
                    person.setBirthDay(null);
                    person.setSex(null);
                    person.setName(null);
                } else
                {
                    if (param[0].equals("-i"))
                    {
                        Person person = allPeople.get(Integer.parseInt(param[1]));
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                        System.out.println(person.getName() + " " + (person.getSex() == Sex.MALE ? "м" : "ж") + " " + sdf.format(person.getBirthDay()));
                    }
                }
            }
        }
    }
}
