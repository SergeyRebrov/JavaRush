package com.javarush.test.level36.lesson04.home01;

/**
 * Created by Sergey on 14.03.2017.
 */
public class View
{
    private Controller controller = new Controller();

    public void fireEventShowData() {
        System.out.println(controller.onDataListShow());
    }
}
