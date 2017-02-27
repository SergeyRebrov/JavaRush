package com.javarush.test.level32.lesson08.home01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Sergey on 23.02.2017.
 */
public class CustomInvocationHandler implements InvocationHandler
{
    private SomeInterfaceWithMethods methods;

    public CustomInvocationHandler(SomeInterfaceWithMethods methods)
    {
        this.methods = methods;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        System.out.println(method.getName() + " in");
        method.invoke(methods, args);
        System.out.println(method.getName() + " out");
        return null;
    }
}
