package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Sergey on 03.01.2017.
 */
class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en", Locale.getDefault());

    @Override
    public void execute()
    {
        ConsoleHelper.writeMessage(res.getString("before"));

        if (CurrencyManipulatorFactory.getAllCurrencyManipulators().isEmpty())
            ConsoleHelper.writeMessage(res.getString("no.money"));
        for (CurrencyManipulator currencyManipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators())
        {
            if (currencyManipulator.hasMoney())
                ConsoleHelper.writeMessage(res.getString("no.money"));
            else
                ConsoleHelper.writeMessage(currencyManipulator.getCurrencyCode() + " - " + currencyManipulator.getTotalAmount());
        }
    }
}
