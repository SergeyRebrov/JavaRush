package com.javarush.test.level37.lesson04.task01;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/* Круговой итератор
Класс Solution наследуется от ArrayList.
Напишите свой класс RoundIterator внутри Solution, который будет итератором для списка Solution.
Итератор должен ходить по кругу по всем элементам.
В остальном поведение должно быть идентичным текущему итератору.
*/
public class Solution<T> extends ArrayList<T>
{
    public static void main(String[] args)
    {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list)
        {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10)
            {
                break;
            }
        }

    }

    @Override
    public Iterator<T> iterator()
    {
        return new RoundIterator();
    }

/*    public class RoundIterator implements Iterator<T>
    {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such

        @Override
        public boolean hasNext()
        {
            return Solution.this.size() > 0;
        }

        @Override
        public T next()
        {
            try
            {
                int i = cursor;
                if (i >= Solution.this.size())
                    i = 0;

                Field field = ArrayList.class.getDeclaredField("elementData");
                field.setAccessible(true);

                Object[] elementData = (Object[]) field.get(Solution.this);
                if (i >= elementData.length)
                    throw new ConcurrentModificationException();
                cursor = i + 1;
                return (T) elementData[lastRet = i];
            }
            catch (NoSuchFieldException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void remove()
        {
            try {
                Solution.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

    }*/

    public class RoundIterator implements Iterator<T>
    {
        private Iterator<T> iterator;

        public RoundIterator()
        {
            this.iterator = Solution.super.iterator();
        }

        @Override
        public boolean hasNext()
        {
            return Solution.this.size() > 0;
        }

        @Override
        public T next()
        {
            if (!iterator.hasNext())
                iterator = Solution.super.iterator();

            return iterator.next();
        }

        @Override
        public void remove()
        {
            iterator.remove();
        }
    }
}
