package com.javarush.test.level32.lesson15.big01.actions;

import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

/**
 * Created by Sergey on 24.02.2017.
 */
public class SubscriptAction extends StyledEditorKit.StyledTextAction
{
    public SubscriptAction()
    {
        super(StyleConstants.Subscript.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
