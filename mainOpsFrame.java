package gui;

import javax.swing.*;

public class mainOpsFrame extends JFrame {


    public static final int DEFAULT_WIDTH = 450;

    public static final int DEFAULT_HEIGHT = 300;

    public mainOpsFrame() {
        setTitle("Operations");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        mainOpsPanel panel = new mainOpsPanel();
        add(panel);
    }

    public static final long serialVersionUID = 1;
}

