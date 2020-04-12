// import java.awt.Container;
// import java.awt.GridBagConstraints;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;


public class caculator extends JFrame {
    
    //Numbers
    JButton btn0;
    JButton btn1;
    JButton btn2;
    JButton btn3;
    JButton btn4;
    JButton btn5;
    JButton btn6;
    JButton btn7;   //bruh
    JButton btn8;
    JButton btn9;

    //Symbols
    JButton btnDot;     // .
    JButton btnEqual;   // =
    JButton btnClear;   // C
    JButton btnPlus;    // +
    JButton btnSub;     // -
    JButton btnMul;     // *
    JButton btnDiv;     // /

    //Store number '0~9', symbol '.' and symbol '=' buttons
    //JPanel numberPad;

    //Show the result
    JTextField txtResult;

    //Store numbers for caculation
    private double number1, number2, result;

    /**
     * To show what number is processing now in the textfield
     * false    :   number1
     * true     :   number2
     */
    //private boolean caculatedNumber = false;

    /**
     * Caculation operator
     * Default is 'n' as there's no need to caculate yet
     */
    private char operator = 'n';

    /**
     * false    :   Only clear current number
     * true     :   All clear
     */
    private boolean allClear = false;

    //Not like the 'allClear' above, this gonna tell the caculator whether if it need to reset textfield after method operatorClicked() has been excuted
    private boolean needtoClearTextfieldAtTheNextTime = false;

    //Add char to textfield
    private void addCharToTextfield(char txt) {
        if (needtoClearTextfieldAtTheNextTime) {
            needtoClearTextfieldAtTheNextTime = false;
            txtResult.setText("0");
        }

        //System.out.println(Double.parseDouble(txtResult.getText()));

        if (Double.parseDouble(txtResult.getText()) == 0 && txt != '.' && txtResult.getText().indexOf(".") == -1)
            txtResult.setText(String.valueOf(txt));
        else
            txtResult.setText(txtResult.getText()+txt);
        
        allClear = false;    //If the btnClear clicked, then it will only clear current number at first time
        btnClear.setText("C");
    }

    //Clear textfield. Only triggered by click btnClear
    private void clearTextField() {
        if (allClear) {     //Clear all data
            number1 = 0; number2 = 0; result = 0;
            //caculatedNumber = false;
            operator = 'n';

            txtResult.setText("0");
        }
        else {
            allClear = true;        //Next time user click this button, I'll clear all data. hehehe...
            btnClear.setText("AC");

            txtResult.setText("0");     //Reset the textfield to 0
        }
    }

    //btnOperator has been clicked
    private void operatorClicked(char operator) {
        if (txtResult.getText().indexOf("ERROR") != -1)     //Prevent ERROR + decimal situation
            return;

        this.operator = operator;

        double number = Double.parseDouble(txtResult.getText());    //Convert number from textField to double
        number1 = number;

        //caculatedNumber = true;     //Tell the caculator we're already have number1

        needtoClearTextfieldAtTheNextTime = true;   //As the name... why am I here?
    }

    //Caculate the result. Only triggered by btnEqual
    private void caculateTheResult() {
        if (txtResult.getText().indexOf("ERROR") != -1)     //Prevent ERROR + ERROR situation
            return;

        number2 = Double.parseDouble(txtResult.getText());

        if (operator == '+')
            result = number1 + number2;
        else if (operator == '-')
            result = number1 - number2;
        else if (operator == '*')
            result = number1 * number2;
        else if (operator == '/') {
            if (number2 == 0) {     //Prevent divided of zero
                txtResult.setText("ERROR");

                needtoClearTextfieldAtTheNextTime = true;

                return;
            }
            else
                result = number1 / number2;
        }

        long integerPart = (long)result;
        //double floatPart = result - integerPart;

        if (result - integerPart == 0)      //If the result is integer, then show the integer part
            txtResult.setText(String.valueOf(integerPart));
        else
            txtResult.setText(String.valueOf(result));
        
        needtoClearTextfieldAtTheNextTime = true;
        //caculatedNumber = false;
    }

    //Initialize all component expect frame
    private void initializeComponents() {
        btn0 = new JButton("0");
        btn1 = new JButton("1");
        btn2 = new JButton("2");
        btn3 = new JButton("3");
        btn4 = new JButton("4");
        btn5 = new JButton("5");
        btn6 = new JButton("6");
        btn7 = new JButton("7");
        btn8 = new JButton("8");
        btn9 = new JButton("9");

        btnDot = new JButton(".");
        btnEqual = new JButton("=");
        btnClear = new JButton("AC");
        btnPlus = new JButton("+");
        btnSub = new JButton("-");
        btnMul = new JButton("*");
        btnDiv = new JButton("/");

        txtResult = new JTextField();
    }

    //Define a GBC methiod just for my convinent
    private GridBagConstraints gbc(int gridx, int gridy, int gridwidth, int gridheight, int fill) {
        /**
         * Here's 'fill' argument
         * Type : int
         * .NONE = 0
         * .BOTH = 1
         * .HORIZONTAL = 2
         * .VERTICAL = 3
         */
        
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = gridx;           gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;   gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.weightx = 1.0;           gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets.left = 1;         gridBagConstraints.insets.top = 1;
        gridBagConstraints.fill = fill;

        return gridBagConstraints;
    }

    //Add all components. Of course still expect frame
    private void addComponents() {
        
        Container cp = getContentPane();
        cp.setLayout(new GridBagLayout());

        //Row 1
        cp.add(btnClear, gbc(0, 0, 1, 1, 1));
        cp.add(txtResult, gbc(1, 0, 3, 1, 1));
        //Row 2
        cp.add(btn7, gbc(0, 1, 1, 1, 1));
        cp.add(btn8, gbc(1, 1, 1, 1, 1));
        cp.add(btn9, gbc(2, 1, 1, 1, 1));
        cp.add(btnPlus, gbc(3, 1, 1, 1, 1));
        //Row3
        cp.add(btn4, gbc(0, 2, 1, 1, 1));
        cp.add(btn5, gbc(1, 2, 1, 1, 1));
        cp.add(btn6, gbc(2, 2, 1, 1, 1));
        cp.add(btnSub, gbc(3, 2, 1, 1, 1));
        //Row 4
        cp.add(btn1, gbc(0, 3, 1, 1, 1));
        cp.add(btn2, gbc(1, 3, 1, 1, 1));
        cp.add(btn3, gbc(2, 3, 1, 1, 1));
        cp.add(btnMul, gbc(3, 3, 1, 1, 1));
        //Row 5
        cp.add(btn0, gbc(0, 4, 1, 1, 1));
        cp.add(btnDot, gbc(1, 4, 1, 1, 1));
        cp.add(btnEqual, gbc(2, 4, 1, 1, 1));
        cp.add(btnDiv, gbc(3, 4, 1, 1, 1));
    }

    //Set components' details and listener
    private void setupComponents() {
        //Details
        txtResult.setEditable(false);   //Let the result text bar be read only
        
        txtResult.setHorizontalAlignment(JTextField.RIGHT); //Text will come from RIGHT now!
        txtResult.setText("0");         //Default 0
        txtResult.setFont(new Font("Arial", Font.PLAIN, 20));   //Set text font

        txtResult.setBorder(new LineBorder(Color.BLACK));       //Let this textfield be more obvious
        txtResult.setBackground(Color.WHITE);       //Same as up

        //Listener
        btn0.addActionListener(e -> addCharToTextfield('0'));
        btn1.addActionListener(e -> addCharToTextfield('1'));
        btn2.addActionListener(e -> addCharToTextfield('2'));
        btn3.addActionListener(e -> addCharToTextfield('3'));
        btn4.addActionListener(e -> addCharToTextfield('4'));
        btn5.addActionListener(e -> addCharToTextfield('5'));
        btn6.addActionListener(e -> addCharToTextfield('6'));
        btn7.addActionListener(e -> addCharToTextfield('7'));
        btn8.addActionListener(e -> addCharToTextfield('8'));
        btn9.addActionListener(e -> addCharToTextfield('9'));
        btnDot.addActionListener(e -> addCharToTextfield('.'));

        btnPlus.addActionListener(e -> operatorClicked('+'));
        btnSub.addActionListener(e -> operatorClicked('-'));
        btnMul.addActionListener(e -> operatorClicked('*'));
        btnDiv.addActionListener(e -> operatorClicked('/'));

        btnClear.addActionListener(e -> clearTextField());

        btnEqual.addActionListener(e -> caculateTheResult());
    }

    //Setup frame
    private void setupFrame() {
        setTitle("Caculator");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 300));
        pack();
        setResizable(false);
    }

    caculator() {
        super();

        initializeComponents();
        addComponents();
        setupComponents();
        setupFrame();

        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
        
            @Override
            public void run() {
                new caculator();
            }
        });

        // new caculator();
    }
}
