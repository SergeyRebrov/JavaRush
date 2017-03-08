package com.javarush.test.level34.lesson08.bonus01;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();

    public V getByKey(K key, Class<V> clazz) throws Exception {
        if (!cache.containsKey(key))
        {
            put(clazz.getDeclaredConstructor(key.getClass()).newInstance(key));
        }
        return (V)cache.get(key);
    }

    public boolean put(V obj) {
        try
        {
            Method method = obj.getClass().getDeclaredMethod("getKey");
            if (!Modifier.isPublic(method.getModifiers()))
            {
                method.setAccessible(true);
            }
            Object o = method.invoke(obj);
            cache.put((K) o, obj);
            return true;
        }
        catch (Exception ignore)
        {

        }
        return false;
    }

    public int size() {
        return cache.size();
    }
}
