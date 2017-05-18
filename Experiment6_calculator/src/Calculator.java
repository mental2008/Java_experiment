import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

public class Calculator extends JFrame {

    private JButton buttonDot, buttonEqual, buttonClear, buttonBack, buttonSpace;
    private JButton[] buttonNumber = new JButton[10];
    private JButton buttonAdd, buttonSubstract, buttonMultiply, buttonDivide;
    private JTextField textField;
    private String displaystring;
    private boolean contentError;

    private double DealEquation(String equation) {
        int len = equation.length();
        Stack num = new Stack();
        Stack oper = new Stack();
        int sign = 1;
        int opercount = 0, numcount = 0;
        for(int i = 0; i < len; i++) {
            //System.out.println(equation.charAt(i));
            if((i == 0 || equation.charAt(i - 1) == '×' || equation.charAt(i - 1) == '÷') && equation.charAt(i) == '-') {
                sign = -1;
                continue;
            }
            if(equation.charAt(i) == '+' || equation.charAt(i) == '-' || equation.charAt(i) == '×' || equation.charAt(i) == '÷') {
                oper.push(equation.charAt(i));
                opercount++;
            }
            else if((equation.charAt(i) >= '0' && equation.charAt(i) <= '9') || equation.charAt(i) == '.') {
                String temp = "";
                int j;
                for(j = i; j < len; j++) {
                    //System.out.println(equation.charAt(j));
                    if ((equation.charAt(j) >= '0' && equation.charAt(j) <= '9') || equation.charAt(j) == '.') {
                        temp += equation.charAt(j);
                    } else break;
                }
                double tempnum = Double.valueOf(temp);
                tempnum *= sign;
                sign = 1;
                //System.out.println(temp);
                num.push(tempnum);
                numcount++;
                i = j - 1;
                if(!oper.empty()) {
                    if((char)oper.peek() == '×') {
                        double tempb = (double)num.peek();
                        num.pop();
                        double tempa = (double)num.peek();
                        num.pop();
                        double sum = tempa * tempb;
                        num.push(sum);
                        oper.pop();
                    }
                    else if((char)oper.peek() == '÷') {
                        double tempb = (double)num.peek();
                        num.pop();
                        double tempa = (double)num.peek();
                        num.pop();
                        if(Math.abs(tempb) < 0.0000001) {
                            contentError = true;
                            return 0;
                        }
                        double sum = tempa / tempb;
                        num.push(sum);
                        oper.pop();
                    }
                }
            }
        }
        if(numcount - opercount != 1) {
            contentError = true;
            return 0;
        }
        Stack tempnum = new Stack();
        Stack tempoper = new Stack();
        while(!num.empty()) {
            double temp = (double)num.peek();
            num.pop();
            tempnum.push(temp);
        }
        while(!oper.empty()) {
            char temp = (char)oper.peek();
            oper.pop();
            tempoper.push(temp);
        }
        while(!tempoper.empty()) {
            double tempa = (double)tempnum.peek();
            tempnum.pop();
            double tempb = (double)tempnum.peek();
            tempnum.pop();
            double sum;
            if((char)tempoper.peek() == '+') {
                sum = tempa + tempb;
            }
            else {
                sum = tempa - tempb;
            }
            tempnum.push(sum);
            tempoper.pop();
        }
        return (double)tempnum.peek();
    }

    void initContentError() {
        if(contentError) {
            displaystring = "";
            textField.setText(displaystring);
            contentError = false;
        }
    }

    private void addButtonNumberMouse() {
        for(int i = 0; i < 10; i++) {
            buttonNumber[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    initContentError();
                    JButton temp = (JButton)e.getComponent();
                    String i = temp.getActionCommand();
                    displaystring = displaystring + i;
                    textField.setText(displaystring);
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }
    }

    public Calculator() {
        setLayout(null);
        setTitle("MyCalculator");
        displaystring = "";
        contentError = false;

        textField = new JTextField();
        textField.setBounds(0, 0, 315, 120);
        textField.setEditable(false);
        textField.setText(displaystring);
        textField.setFont(new Font("Arial", 1, 30));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        add(textField);

        buttonDot = new JButton(".");
        buttonDot.setFont(new Font("Arial", 1, 20));
        buttonDot.setBackground(Color.white);
        buttonDot.setBounds(160, 320, 80, 50);
        buttonDot.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                initContentError();
                int len = displaystring.length();
                boolean is_dot = false;
                for(int i = len - 1; i >= 0; i--) {
                    if(displaystring.charAt(i) == '+' || displaystring.charAt(i) == '-' || displaystring.charAt(i) == '×' || displaystring.charAt(i) == '÷') {
                        break;
                    }
                    if(displaystring.charAt(i) == '.') {
                        is_dot = true;
                    }
                }
                if(!is_dot) {
                    displaystring = displaystring + '.';
                    textField.setText(displaystring);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonDot);

        for(int i = 0; i < 10; i++) {
            buttonNumber[i] = new JButton(Integer.toString(i));
            buttonNumber[i].setActionCommand(Integer.toString(i));
        }

        buttonNumber[0].setBackground(Color.white);
        buttonNumber[0].setFont(new Font("Arial", 1, 20));
        buttonNumber[0].setBounds(80, 320, 80, 50);
        add(buttonNumber[0]);

        int tempx = 0, tempy = 270;
        for(int i = 1; i < 10; i++) {
            buttonNumber[i].setBounds(tempx, tempy, 80, 50);
            buttonNumber[i].setFont(new Font("Arial", 1, 20));
            buttonNumber[i].setBackground(Color.white);
            tempx += 80;
            add(buttonNumber[i]);
            if(i % 3 == 0) {
                tempx = 0;
                tempy -= 50;
            }
        }
        addButtonNumberMouse();

        buttonEqual = new JButton("=");
        buttonEqual.setFont(new Font("Arial", 1, 20));
        buttonEqual.setBackground(new Color(193, 210, 240));
        buttonEqual.setBounds(240, 270, 80, 100);
        buttonEqual.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                double ans = DealEquation(displaystring);
                if(contentError) {
                    textField.setText("Content errors");
                }
                else {
                    if((int)ans == ans) {
                        displaystring = Integer.toString((int)ans);
                    }
                    else {
                        displaystring = Double.toString(ans);
                    }
                    textField.setText(displaystring);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonEqual);

        buttonClear = new JButton("C");
        buttonClear.setBounds(0, 120, 80, 50);
        buttonClear.setFont(new Font("Arial", 0, 20));
        buttonClear.setBackground(Color.lightGray);
        buttonClear.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                initContentError();
                displaystring = "";
                textField.setText(displaystring);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonClear);

        buttonBack = new JButton("<-");
        buttonBack.setFont(new Font("Arial", 1, 20));
        buttonBack.setBackground(Color.lightGray);
        buttonBack.setBounds(240, 120, 80, 50);
        buttonBack.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                initContentError();
                if(displaystring.length() != 0) {
                    displaystring = displaystring.substring(0, displaystring.length() - 1);
                    //System.out.println(displaystring);
                    textField.setText(displaystring);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonBack);

        buttonSpace = new JButton();
        //buttonSpace.setEnabled(false);
        buttonSpace.setBackground(Color.white);
        buttonSpace.setBounds(0, 320, 80, 50);
        add(buttonSpace);

        buttonDivide = new JButton("÷");
        buttonDivide.setFont(new Font("Arial", 1, 20));
        buttonDivide.setBackground(Color.lightGray);
        buttonDivide.setBounds(80, 120, 80, 50);
        buttonDivide.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                initContentError();
                int len = displaystring.length();
                if(len != 0) {
                    if (displaystring.charAt(len - 1) == '+' || displaystring.charAt(len - 1) == '-' || displaystring.charAt(len - 1) == '×' || displaystring.charAt(len - 1) == '÷') {
                        displaystring = displaystring.substring(0, len - 1) + '÷';
                    } else {
                        displaystring = displaystring + '÷';
                    }
                    textField.setText(displaystring);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonDivide);

        buttonMultiply = new JButton("×");
        buttonMultiply.setFont(new Font("Arial", 1, 20));
        buttonMultiply.setBackground(Color.lightGray);
        buttonMultiply.setBounds(160, 120, 80, 50);
        buttonMultiply.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                initContentError();
                int len = displaystring.length();
                if(len != 0) {
                    if (displaystring.charAt(len - 1) == '+' || displaystring.charAt(len - 1) == '-' || displaystring.charAt(len - 1) == '×' || displaystring.charAt(len - 1) == '÷') {
                        displaystring = displaystring.substring(0, len - 1) + '×';
                    } else {
                        displaystring = displaystring + '×';
                    }
                    textField.setText(displaystring);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonMultiply);

        buttonAdd = new JButton("+");
        buttonAdd.setFont(new Font("Arial", 1, 20));
        buttonAdd.setBackground(Color.lightGray);
        buttonAdd.setBounds(240, 220, 80, 50);
        buttonAdd.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                initContentError();
                int len = displaystring.length();
                if(len != 0) {
                    if (displaystring.charAt(len - 1) == '+' || displaystring.charAt(len - 1) == '-' || displaystring.charAt(len - 1) == '×' || displaystring.charAt(len - 1) == '÷') {
                        displaystring = displaystring.substring(0, len - 1) + '+';
                    } else {
                        displaystring = displaystring + '+';
                    }
                    textField.setText(displaystring);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonAdd);

        buttonSubstract = new JButton("-");
        buttonSubstract.setFont(new Font("Arial", 1, 20));
        buttonSubstract.setBackground(Color.lightGray);
        buttonSubstract.setBounds(240, 170, 80, 50);
        buttonSubstract.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                initContentError();
                int len = displaystring.length();
                if(len != -0 && (displaystring.charAt(len - 1) == '+' || displaystring.charAt(len - 1) == '-')) {
                    displaystring = displaystring.substring(0, len - 1) + '-';
                }
                else {
                    displaystring = displaystring + '-';
                }
                textField.setText(displaystring);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonSubstract);

        setBounds(0, 0, 320, 405);
        setResizable(false);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setVisible(true);
    }

}
