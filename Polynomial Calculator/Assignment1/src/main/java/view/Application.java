package view;

import controller.Controller;
import model.Polynomial;
import model.Operations;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Application {

    private JTextField textP1, textP2, textP3;
    private JButton plus, minus, inmult, impart;
    private JButton deriv,integr;
    private JLabel labelP1, labelP2, labelP3;
    //Operations operations = new Operations();
    private Controller controller;
    public void createGUI()
    {
        JFrame frame = new JFrame("Calculator de polinoane");
        Operations op = new Operations();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 280);

        Color albastru = new Color(197, 248, 250);
        Color roz = new Color(249, 197, 250);

        frame.getContentPane().setBackground(albastru);

        controller = new Controller(this);

        Dimension prefDimensionText = new Dimension(200, 50);

        textP1 = new JTextField();
        textP1.setPreferredSize(prefDimensionText);
        textP1.setBackground(roz);
        textP2 = new JTextField();
        textP2.setPreferredSize(prefDimensionText);
        textP2.setBackground(roz);
        textP3 = new JTextField();
        textP3.setPreferredSize(prefDimensionText);
        textP3.setBackground(roz);
        textP3.setEditable(false);

        labelP1 = new JLabel("Polinom 1:");
        labelP1.setFont(new Font("Serif", Font.ITALIC, 25));
        labelP2 = new JLabel("Polinom 2:");
        labelP2.setFont(new Font("Serif", Font.ITALIC, 25));
        labelP3 = new JLabel("Rezultat:");
        labelP3.setFont(new Font("Serif", Font.ITALIC, 25));

        plus = new JButton(); // adunare
        plus.setPreferredSize(new Dimension(100, 50));
        plus.setBackground(roz);
        plus.setText("+");
        plus.setFont(new Font("Arial", Font.PLAIN, 25));
        plus.addActionListener(controller.plusListener());

        minus = new JButton(); // scadere
        minus.setPreferredSize(new Dimension(100, 50));
        minus.setBackground(roz);
        minus.setText("-");
        minus.setFont(new Font("Arial", Font.PLAIN, 25));
        minus.addActionListener(controller.minusListener());

        inmult = new JButton(); // inmultire
        inmult.setPreferredSize(new Dimension(100, 50));
        inmult.setBackground(roz);
        inmult.setText("*");
        inmult.setFont(new Font("Arial", Font.PLAIN, 25));
        inmult.addActionListener(controller.inmultListener());

        impart = new JButton(); // impartire
        impart.setPreferredSize(new Dimension(100, 50));
        impart.setBackground(roz);
        impart.setText("/");
        impart.setFont(new Font("Arial", Font.PLAIN, 25));
        impart.addActionListener(controller.impartListener());

        deriv = new JButton(); // derivare
        deriv.setPreferredSize(new Dimension(100, 50));
        deriv.setBackground(roz);
        deriv.setText("DER");
        deriv.addActionListener(controller.derivListener());

        integr = new JButton(); // integrare
        integr.setPreferredSize(new Dimension(100, 50));
        integr.setBackground(roz);
        integr.setText("INT");
        integr.addActionListener(controller.integrListener());

        JPanel poliPanel = new JPanel(new GridLayout(3,2));
        poliPanel.setBackground(albastru);
        poliPanel.add(labelP1);
        poliPanel.add(textP1);
        poliPanel.add(labelP2);
        poliPanel.add(textP2);
        poliPanel.add(labelP3);
        poliPanel.add(textP3);

        JPanel butPanel = new JPanel(new FlowLayout());
        butPanel.setBackground(albastru);
        butPanel.add(plus);
        butPanel.add(minus);
        butPanel.add(inmult);
        butPanel.add(impart);
        butPanel.add(deriv);
        butPanel.add(integr);

        frame.setLayout(new BorderLayout());
        frame.add(poliPanel, BorderLayout.NORTH);
        frame.add(butPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public JTextField getTextP1()
    {
        return textP1;
    }

    public JTextField getTextP2()
    {
        return textP2;
    }

    public JTextField getTextP3()
    {
        return textP3;
    }

}
