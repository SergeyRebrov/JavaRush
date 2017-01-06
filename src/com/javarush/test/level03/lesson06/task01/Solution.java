package com.javarush.test.level03.lesson06.task01;

/* Мама мыла раму
Вывести на экран все возможные комбинации слов «Мама», «Мыла», «Раму».
Подсказка: их 6 штук. Каждую комбинацию вывести с новой строки. Слова не разделять. Пример:
МылаРамуМама
РамуМамаМыла
...
*/

public class Solution
{
    public static void main(String[] args)
    {
        String a = "Мама";
        String b = "Мыла";
        String c = "Раму";

        String x = a + b + c;
        System.out.println(x);
        x = a + c + b;
        System.out.println(x);
        x = b + a + c;
        System.out.println(x);
        x = b + c + a;
        System.out.println(x);
        x = c + b + a;
        System.out.println(x);
        x = c + a + b;
        System.out.println(x);

    }
}