package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Sergey on 03.01.2017.
 */
class DepositCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en", Locale.getDefault());

    @Override
    public void execute() throws InterruptOperationException
    {
        try
        {
            ConsoleHelper.writeMessage(res.getString("before"));
            String currencyCode = ConsoleHelper.askCurrencyCode();
            String[] digits = ConsoleHelper.getValidTwoDigits(currencyCode);
            if (Integer.parseInt(digits[0]) < 0 || Integer.parseInt(digits[1]) < 0)
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            else
            {
                CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
                currencyManipulator.addAmount(Integer.parseInt(digits[0]), Integer.parseInt(digits[1]));
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), currencyManipulator.getTotalAmount(), currencyCode));
            }
        }
        catch (NumberFormatException e)
        {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
    }
}
