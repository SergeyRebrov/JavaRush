package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Sergey on 03.01.2017.
 */
class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en", Locale.getDefault());

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());
        while (true)
        {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            try
            {
                int amount = Integer.parseInt(ConsoleHelper.readString());
                if (manipulator.isAmountAvailable(amount))
                {
                    try
                    {
                        Map<Integer, Integer> withdraw = manipulator.withdrawAmount(amount);
                        for (Map.Entry<Integer, Integer> entry : withdraw.entrySet())
                        {
                            ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
                        }
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount, manipulator.getCurrencyCode()));
                        break;
                    } catch (NotEnoughMoneyException e)
                    {
                        ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                    }
                }
            } catch (NumberFormatException ignore)
            {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            }
        }
    }
}