package com.javarush.test.level15.lesson12.home05;

/**
 * Created by Sergey on 06.08.2016.
 */
public class SubSolution extends Solution
{
    public SubSolution()
    {
    }

    public SubSolution(String s)
    {
        super(s);
    }

    public SubSolution(int i)
    {
        super(i);
    }

    private SubSolution(String s, int i)
    {
        super();
    }

    private SubSolution(int i, String s)
    {
        super();
    }

    private SubSolution(int i, String s, Solution n)
    {
        super();
    }

    protected SubSolution(Solution n, String s, int i)
    {
        super(n, s, i);
    }

    protected SubSolution(Solution n, int i)
    {
        super(n, i);
    }

    protected SubSolution(Solution n, String s)
    {
        super(n, s);
    }

    SubSolution(String s, Solution n)
    {
        super(s, n);
    }

    SubSolution(int i, Solution n)
    {
        super(i, n);
    }

    SubSolution(int i, Solution n, String s)
    {
        super(i, n, s);
    }


}
