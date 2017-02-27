package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by Sergey on 04.01.2017.
 */
class LoginCommand implements Command
{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en", Locale.getDefault());

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String number = null;
        String pin = null;
        do {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            number = ConsoleHelper.readString();

            String code = null;
            try
            {
                code = validCreditCards.getString(number);
            } catch (MissingResourceException e)
            {
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            }

            pin = ConsoleHelper.readString();
            if (!pin.matches("\\d{4}") && !number.matches("\\d{12}"))
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));

            if (code.equals(pin))
            {
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), number));
                break;
            } else
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), number));
        } while (true);


    }
}
