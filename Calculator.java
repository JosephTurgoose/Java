import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class Calculator
{
    static String display = "";
    static int value = 0;
    static int value2 = 0;
    static Boolean hasSign = false;
    static Boolean done = false;
    public static void main(String[] args)
    {
        JFrame jFrame = new JFrame("Calculator");
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(400,500);

        var label = new JLabel(display);

        // Adding buttons for 0-9
        for(int i = 0; i<=9; i++)
        {
            final int index = i;
            JButton button = new JButton(String.format("%d", i));
            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    if (done == true)
                    {
                        display = "";
                        value = 0;
                        value2 = 0;
                        label.setText(display);
                        done = false;
                    }

                    display += String.format("%d", index);

                    if (hasSign == false)
                    {
                        value = Integer.parseInt(display);
                    }
                    else // if hasSign == true
                    {
                        String sign = display.replaceAll("[0-9]", "");
                        String[] parts = display.split(Pattern.quote(sign)); // This isn't working
                        // for (String part : parts) {
                        //     System.out.println(part);
                        // }
                        var displayBeforeSign = parts[0];
                        var displayAfterSign = parts[1];
                        value = Integer.parseInt(displayBeforeSign);
                        value2 = Integer.parseInt(displayAfterSign);
                    }

                    label.setText(display);
                }
            });
            jFrame.add(button);
        }

        // Adding + button
        JButton plusButton = new JButton("+", null);
        plusButton.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                // If there is not another symbol, add a plus.  If there is, replace it with a plus.
                if (display.charAt(display.length() - 1) == '+' | display.charAt(display.length() - 1) == '-' | display.charAt(display.length() - 1) == '/' | display.charAt(display.length() - 1) == '*')
                {
                    display = display.substring(0, display.length()-1);
                    hasSign = false;
                }
                if (hasSign == false)
                {
                    display += "+";
                    hasSign = true;
                }
                label.setText(display);
            }
        }));
        // Adding - button
        JButton minusButton = new JButton("-", null);
        minusButton.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (display.charAt(display.length() - 1) == '+' | display.charAt(display.length() - 1) == '-' | display.charAt(display.length() - 1) == '/' | display.charAt(display.length() - 1) == '*')
                {
                    display = display.substring(0, display.length()-1);
                    hasSign = false;
                }
                if (hasSign == false)
                {
                    display += "-";
                    hasSign = true;
                }
                label.setText(display);
            }
        }));
        // Adding * button
        JButton timesButton = new JButton("*", null);
        timesButton.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (display.charAt(display.length() - 1) == '+' | display.charAt(display.length() - 1) == '-' | display.charAt(display.length() - 1) == '/' | display.charAt(display.length() - 1) == '*')
                {
                    display = display.substring(0, display.length()-1);
                    hasSign = false;
                }
                if (hasSign == false)
                {
                    display += "*";
                    hasSign = true;
                }
                label.setText(display);
            }
        }));
        // Adding / button
        JButton divideButton = new JButton("/", null);
        divideButton.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (display.charAt(display.length() - 1) == '+' | display.charAt(display.length() - 1) == '-' | display.charAt(display.length() - 1) == '/' | display.charAt(display.length() - 1) == '*')
                {
                    display = display.substring(0, display.length()-1);
                    hasSign = false;
                }
                if (hasSign == false)
                {
                    display += "/";
                    hasSign = true;
                }

                label.setText(display);
            }
        }));
        // Adding = button
        JButton equalButton = new JButton("=", null);
        equalButton.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (hasSign == true && value2 != 0)
                {
                    String sign = display.replaceAll("[0-9]", "");
                    if (sign.equals("+"))
                    {
                        value = (value + value2);
                    }
                    else if (sign.equals("-"))
                    {
                        value = (value - value2);
                    }
                    else if (sign.equals("*"))
                    {
                        value = (value * value2);
                    }
                    else if (sign.equals("/"))
                    {
                        value = (value / value2);
                    }
                    display = String.format("%d", value);
                    label.setText(display);
                    hasSign = false;
                    done = true;
                }
            }
        }));
        // Adding Clear Button
        JButton clearButton = new JButton("Clear", null);
        clearButton.addActionListener((new ActionListener()
        {
            public void actionPerformed (ActionEvent e)
            {
                display = "";
                value = 0;
                value2 = 0;
                label.setText(display);
            }
        }));
        
        // Setup
        jFrame.add(plusButton);
        jFrame.add(minusButton);
        jFrame.add(timesButton);
        jFrame.add(divideButton);
        jFrame.add(equalButton);
        jFrame.add(clearButton);
        jFrame.add(label);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}