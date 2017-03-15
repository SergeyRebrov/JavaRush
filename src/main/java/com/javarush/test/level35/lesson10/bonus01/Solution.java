package com.javarush.test.level35.lesson10.bonus01;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/* ClassLoader - что это такое?
Реализуйте логику метода getAllAnimals.
Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
Путь не обязательно содержит / в конце.
НЕ все классы наследуются от интерфейса Animal.
НЕ все классы имеют публичный конструктор без параметров.
Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров, - создать по одному объекту.
Добавить созданные объекты в результирующий сет и вернуть.
Метод main не участвует в тестировании.
*/
public class Solution
{
    public static void main(String[] args)
    {
        Set<? extends Animal> allAnimals = getAllAnimals("D:\\test");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals)
    {

        if (!pathToAnimals.endsWith("/"))
            pathToAnimals = pathToAnimals + "/";

        Set<Animal> animals = new HashSet<>();

        final String pathToBin = pathToAnimals;
        ClassLoader loader = new ClassLoader()
        {
            @Override
            protected Class<?> findClass(String className) throws ClassNotFoundException
            {
                try
                {
                    byte b[] = fetchClassFromFS(pathToBin + className + ".class");
                    return defineClass(null, b, 0, b.length);
                }
                catch (FileNotFoundException ex)
                {
                    return super.findClass(className);
                }
                catch (IOException ex)
                {
                    return super.findClass(className);
                }
            }
        };

        for (String path : new File(pathToAnimals).list())
        {
            try
            {
                String moduleName = path.split(".class")[0];
                Class clazz = loader.loadClass(moduleName);

                boolean isInterface = false;
                for (Class c : clazz.getInterfaces())
                    if (c.equals(Animal.class))
                        isInterface = true;

                boolean isConstructor = false;
                for (Constructor constructor : clazz.getConstructors())
                    if (Modifier.isPublic(constructor.getModifiers()) && constructor.getParameterTypes().length == 0)
                        isConstructor = true;

                if (isInterface && isConstructor)
                    animals.add((Animal) clazz.newInstance());

            }
            catch (ClassNotFoundException e)
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
        }

        return animals;
    }

    private static byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException
    {
        InputStream is = new FileInputStream(new File(path));

        // Get the size of the file
        long length = new File(path).length();

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)
        {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length)
        {
            throw new IOException("Could not completely read file " + path);
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;

    }
}
