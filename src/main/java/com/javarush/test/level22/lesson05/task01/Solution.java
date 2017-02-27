package com.javarush.test.level22.lesson05.task01;

/* Найти подстроку
Метод getPartOfString должен возвращать подстроку начиная с символа после 1-го пробела и до конца слова,
которое следует после 4-го пробела.
Пример: "JavaRush - лучший сервис обучения Java."
Результат: "- лучший сервис обучения"
На некорректные данные бросить исключение TooShortStringException (сделать исключением).
Сигнатуру метода getPartOfString не менять.
Метод main не участвует в тестировании.
*/
public class Solution
{
    public static String getPartOfString(String string)
    {
        if (string == null)
            throw new TooShortStringException();
        char[] symbols = string.toCharArray();
        int count = 0;
        int lastIndex = 0;
        for (int i = 0; i < symbols.length; i++)
        {
            if (symbols[i] == ' ')
            {
                count++;
                if (count == 5)
                    lastIndex = i;
                if (count == 4 && i == symbols.length - 1)
                    throw new TooShortStringException();
            }
            if (count == 4)
                lastIndex = i + 1;
        }
        if (count < 4)
            throw new TooShortStringException();

        int firstIndex = string.indexOf(' ');

        return string.substring(++firstIndex, lastIndex);
    }

    public static class TooShortStringException extends RuntimeException
    {
    }

    public static void main(String[] args)
    {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }
}
