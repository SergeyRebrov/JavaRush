package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.HHStrategy;
import com.javarush.test.level28.lesson15.big01.model.Model;
import com.javarush.test.level28.lesson15.big01.model.MoikrugStrategy;
import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.view.HtmlView;

/**
 * Created by Sergey on 26.01.2017.
 */
public class Aggregator
{
    public static void main(String[] args)
    {
        Provider provider = new Provider(new HHStrategy());
        Provider provider1 = new Provider(new MoikrugStrategy());
        HtmlView view = new HtmlView();
        Model model = new Model(view, provider, provider1);
        Controller controller = new Controller(model);

        view.setController(controller);
        view.userCitySelectEmulationMethod();
    }
}
