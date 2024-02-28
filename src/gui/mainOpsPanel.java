package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainOpsPanel extends JPanel {

    public mainOpsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalGlue());

        JButton addButton3 = new JButton("The Ward Operations");
        addButton3.setMaximumSize(addButton3.getPreferredSize());
        add(addButton3);
        addButton3.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                WardFrame bedPanel = new WardFrame();
                bedPanel.setLocation(300, 300);
                bedPanel.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                bedPanel.setVisible(true);
            }
        });
        add(Box.createVerticalGlue());
        JButton addButton2 = new JButton("The Doctor Operations");
        addButton2.setMaximumSize(addButton2.getPreferredSize());
        add(addButton2);
        addButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                DoctorOpsFrame frame2 = new DoctorOpsFrame();
                frame2.setLocation(300, 300);
                frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame2.setVisible(true);
            }
        });
        add(Box.createVerticalGlue());

        JButton addButton1 = new JButton("The Patient Operations");
        addButton1.setMaximumSize(addButton1.getPreferredSize());
        add(addButton1);
        addButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                PatientOpsFrame frame1 = new PatientOpsFrame();
                frame1.setLocation(400, 400);
                frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame1.setVisible(true);
            }
        });
        add(Box.createVerticalGlue());

        final JButton exitButton = new JButton("Exit");
        exitButton.setMaximumSize(exitButton.getPreferredSize());
        add(exitButton);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
    }

    public static final long serialVersionUID = 1;
}