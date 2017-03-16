package com.javarush.test.level36.lesson06.task01;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/* Найти класс по описанию
1. Реализует интерфейс List
2. Является приватным статическим классом внутри популярного утилитного класса
3. Доступ по индексу запрещен - кидается исключение IndexOutOfBoundsException
4. Используйте рефлекшн, чтобы добраться до искомого класса
*/
public class Solution
{
    public static void main(String[] args)
    {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass()
    {
        for (Class clazz : Collections.class.getDeclaredClasses())
        {
            if (Modifier.isPrivate(clazz.getModifiers()) && Modifier.isStatic(clazz.getModifiers()) && List.class.isAssignableFrom(clazz))
            {
                try
                {
                    Constructor constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    List list = (List) constructor.newInstance();

                    try{
                        list.get(0);
                    } catch (IndexOutOfBoundsException e)
                    {
                        return clazz;
                    }
                }
                catch (NoSuchMethodException e)
                {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
                catch (InstantiationException e)
                {
                    e.printStackTrace();
                }
                catch (InvocationTargetException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
