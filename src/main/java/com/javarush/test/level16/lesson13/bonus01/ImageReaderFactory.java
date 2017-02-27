package com.javarush.test.level16.lesson13.bonus01;

import com.javarush.test.level16.lesson13.bonus01.common.*;

/**
 * Created by Sergey on 06.09.2016.
 */
public class ImageReaderFactory
{
    public static ImageReader getReader(ImageTypes imageType)
    {
        if (imageType == ImageTypes.JPG)
            return new JpgReader();
        else if (imageType == ImageTypes.PNG)
            return new PngReader();
        else if (imageType == ImageTypes.BMP)
            return new BmpReader();
        else
            throw new IllegalArgumentException("Неизвестный тип картинки");
    }
}
