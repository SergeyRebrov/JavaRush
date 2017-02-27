package com.javarush.test.level07.lesson12.home06;

/* Семья
Создай класс Human с полями имя(String), пол(boolean),возраст(int), отец(Human), мать(Human). Создай объекты и заполни их так, чтобы получилось: Два дедушки, две бабушки, отец, мать, трое детей.
Вывести объекты на экран.
Примечание:
Если написать свой метод String toString() в классе Human, то именно он будет использоваться при выводе объекта на экран.
Пример вывода:
Имя: Аня, пол: женский, возраст: 21, отец: Павел, мать: Катя
Имя: Катя, пол: женский, возраст: 55
Имя: Игорь, пол: мужской, возраст: 2, отец: Михаил, мать: Аня
…
*/

public class Solution
{
    public static void main(String[] args)
    {
        Human gf1 = new Human("Игорь", true, 50);
        Human gm1 = new Human("Ольга", false, 48);
        Human f = new Human("Олег", true, 28, gf1, gm1);
        Human gf2 = new Human("Алексей", true, 54);
        Human gm2 = new Human("Светлана", false, 55);
        Human m = new Human("Марина", true, 25, gf2, gm2);
        Human c1 = new Human("Степан", true, 10, f, m);
        Human c2 = new Human("Кристина", false, 4, f, m);
        Human c3 = new Human("Максим", true, 1, f, m);


        System.out.println(gf1.toString());
        System.out.println(gm1.toString());
        System.out.println(f.toString());
        System.out.println(gf2.toString());
        System.out.println(gm2.toString());
        System.out.println(m.toString());
        System.out.println(c1.toString());
        System.out.println(c2.toString());
        System.out.println(c3.toString());

    }

    public static class Human
    {
        String name;
        boolean sex;
        int age;
        Human father;
        Human mother;

        Human(String name, boolean sex, int age)
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        Human(String name, boolean sex, int age, Human gf, Human gm)
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = gf;
            this.mother = gm;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }

}
