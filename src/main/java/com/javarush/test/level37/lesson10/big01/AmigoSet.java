package com.javarush.test.level37.lesson10.big01;

import java.io.*;
import java.util.*;

/**
 * Created by Sergey on 20.03.2017.
 */
public class AmigoSet<E> extends AbstractSet implements Serializable, Cloneable
{
    private final static Object PRESENT = new Object();

    private transient HashMap<E, Object> map;

    public AmigoSet()
    {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection)
    {
        int capacity = (int) Math.max(16, collection.size() / .75f);
        map = new HashMap<>(capacity);
        this.addAll(collection);
    }

    @Override
    public Iterator<E> iterator()
    {
        return map.keySet().iterator();
    }

    @Override
    public int size()
    {
        return map.size();
    }

    @Override
    public boolean add(Object o)
    {
        return map.put((E) o, PRESENT) == null;
    }

    @Override
    public boolean isEmpty()
    {
        return map.keySet().isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return map.keySet().contains((E) o);
    }

    @Override
    public void clear()
    {
        map.keySet().clear();
    }

    @Override
    public boolean remove(Object o)
    {
        return map.keySet().remove((E) o);
    }

    @Override
    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new InternalError();
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException
    {
        //out.defaultWriteObject();
        out.writeInt(map.size());
        for (E e : map.keySet())
            out.writeObject(e);
        out.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        out.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        //in.defaultReadObject();
        int size = in.readInt();
        Set<E> eSet = new HashSet<>();
        for (int i = 0; i < size; i++)
            eSet.add((E) in.readObject());
        int capacity = (int) in.readObject();
        float loadFactor = (float) in.readObject();

        map = new HashMap<>(capacity, loadFactor);
        this.addAll(eSet);
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        AmigoSet<Integer> set1 = new AmigoSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(stream);
        set1.writeObject(outputStream);

        ByteArrayInputStream is = new ByteArrayInputStream(stream.toByteArray());
        ObjectInputStream inputStream = new ObjectInputStream(is);
        AmigoSet<Integer> set2 = new AmigoSet<>();
        set2.readObject(inputStream);
        for (Object i : set2)
            System.out.println(i);
    }
}
