package com.javarush.test.level36.lesson08.bonus01;

import javax.swing.*;
import java.awt.*;

/* Разбираемся в красно-черном дереве
Дана реализация красно-черного дерева.
Некоторые методы сломаны. Разберитесь в коде и исправьте ошибки.
Метод main не участвует в тестировании.
Все модификатры правильные.
Имена переменных и методов не изменяйте.
*/
public class Solution extends JFrame
{

    private JPanel field = new JPanel()
    {
        private void paintArrow(Graphics g, int x1, int y1, int x2, int y2)
        {
            final float k = 0.25f;
            g.setColor(Color.blue);

            int xBeg = Math.round(x1 + k * (x2 - x1));
            int yBeg = Math.round(y1 + k * (y2 - y1));
            int xEnd = Math.round(x2 - k * (x2 - x1));
            int yEnd = Math.round(y2 - k * (y2 - y1));

            g.drawLine(xBeg, yBeg, xEnd, yEnd);
        }

        private void paintNode(RedBlackTree.Node node, Graphics g, int x, int y, int length) throws Exception
        {
            Color color = "black".equals(NodeHelperTestSolution.getNodeColor(node).toString().toLowerCase()) ?
                    Color.black : Color.red;

            g.setColor(color);
            g.fillRect(x - 5, y - 5, 10, 10);
            g.drawString(Integer.valueOf(NodeHelperTestSolution.getNodeIntValue(node)).toString(), x, y - 8);

            RedBlackTree.Node leftNode = NodeHelperTestSolution.getNodeValue("left", node);
            if (leftNode != NodeHelperTestSolution.getEmptyNode())
            {
                paintArrow(g, x, y, x - length, y + 30);
                paintNode(leftNode, g, x - length, y + 30, length / 2);
            }

            RedBlackTree.Node rightNode = NodeHelperTestSolution.getNodeValue("right", node);
            if (rightNode != NodeHelperTestSolution.getEmptyNode())
            {
                paintArrow(g, x, y, x + length, y + 30);
                paintNode(rightNode, g, x + length, y + 30, length / 2);
            }

        }

        @Override
        public void paint(Graphics g)
        {
            g.setColor(Color.white);
            g.fillRect(0, 0, getWidth(), getHeight());

            try
            {
                RedBlackTree.Node node = NodeHelperTestSolution.getFromTreeNodeByName("header", tree);
                paintNode(node, g, 20, 20, 400);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    };

    private Solution()
    {
        add(field);
        setSize(1000, 500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        tree = new RedBlackTree();
    }

    private RedBlackTree tree;

    private static void insertValue(Solution solution, int value) throws InterruptedException
    {
        JOptionPane.showMessageDialog(solution, value);
        solution.tree.insert(value);
        solution.field.repaint();
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();

        int[] ints = {10, 1, 15, 9, 18, 7, 14, 25, 10, 16, 28, 20, 8, 2, 17, 24, 25, 13, 6};

        for (int i : ints)
            insertValue(solution, i);

        solution.field.repaint();
    }
}