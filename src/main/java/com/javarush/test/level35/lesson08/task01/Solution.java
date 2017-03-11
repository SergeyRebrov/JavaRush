package com.javarush.test.level35.lesson08.task01;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
/* Wildcards
Перепишите дженерики в методе add импользуя wildcards.
Логику не меняйте.
Не оставляйте закомментированный код.
*/
public class Solution {

    public static <T> void add(List<? super T> destinationList, List<? extends T> sourceList) {
        ListIterator<? super T> destListIterator = destinationList.listIterator();
        ListIterator<? extends T> srcListIterator = sourceList.listIterator();
        for (int i = 0; i < sourceList.size(); i++) {
            destListIterator.add(srcListIterator.next());
        }
    }


    public static void main(String[] args) {
        List<B> destination = new ArrayList<>();
        destination.add(new B());
        List<C> source = new ArrayList<>();
        source.add(new C());
        add(destination, source);
        System.out.println(destination);
        System.out.println(source);
    }

    static class A {
    }

    static class B extends A {
    }

    static class C extends B {
    }
}
