package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<Human> ch = new ArrayList<Human>();

        Human ch1 = new Human("Александр", true, 7);
        Human ch2 = new Human("Мария", false, 10);
        Human ch3 = new Human("Иван", true, 3);

        Human f = new Human("Андрей", true, 35, ch);
        Human m = new Human("Анна", false, 33, ch);

        Human gf1 = new Human("Алексей", true, 58, ch);
        Human gm1 = new Human("Елена", false, 54, ch);

        Human gf2 = new Human("Афанасий", true, 52, ch);
        Human gm2 = new Human("Ефросинья", false, 49, ch);

        System.out.println(ch1.toString());
        System.out.println(ch2.toString());
        System.out.println(ch3.toString());
        ch.add(ch1);
        ch.add(ch2);
        ch.add(ch3);
        System.out.println(f.toString());
        System.out.println(m.toString());
        ch.clear();
        ch.add(f);
        System.out.println(gf1.toString());
        System.out.println(gm1.toString());
        ch.clear();
        ch.add(m);
        System.out.println(gf2.toString());
        System.out.println(gm2.toString());
    }

    public static class Human
    {
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children = new ArrayList<Human>();

        Human(String name, boolean sex, int age)
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        Human(String name, boolean sex, int age, ArrayList<Human> ch)
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = ch;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }

            return text;
        }
    }

}
