package com.javarush.test.level36.lesson10.bonus01;


import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* Осваиваем ClassLoader и Reflection
Аргументом для класса Solution является абсолютный путь к пакету,
например, "C:\JavaRushHomeWork\src\com\javarush\test\level36\lesson10\bonus01\data\second".
Имя пакета может содержать File.separator.
В этом пакете находятся только скомпилированные классы.
Известно, что каждый класс имеет конструктор без параметров и реализует интерфейс HiddenClass.
Считайте все классы с файловой системы, создайте фабрику - реализуйте метод getHiddenClassObjectByKey.
Известно, что есть только один класс, простое имя которого начинается с String key без учета регистра.
*/
public class Solution
{
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName)
    {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException
    {
        //Solution solution = new Solution("C:\\JavaRushHomeWork\\src\\com\\javarush\\test\\level36\\lesson10\\bonus01\\data\\second");
        Solution solution = new Solution("D:\\test");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException
    {
        if (!packageName.endsWith("/"))
            packageName = packageName + "/";

        final String pathToBin = packageName;
        ClassLoader classLoader = new ClassLoader()
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

        for (String path : new File(packageName).list())
        {
            String moduleName = path.split(".class")[0];
            Class clazz = classLoader.loadClass(moduleName);
            hiddenClasses.add(clazz);
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key)
    {
        try
        {
            for (Class clazz : hiddenClasses)
            {
                if (clazz.getSimpleName().toLowerCase().contains(key.toLowerCase()))
                {
                    Constructor constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return (HiddenClass) constructor.newInstance();
                }
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

        return null;
    }

    private static byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException
    {
        InputStream is = Files.newInputStream(Paths.get(path));

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
