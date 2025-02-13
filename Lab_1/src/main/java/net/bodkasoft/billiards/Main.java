package net.bodkasoft.billiards;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        BounceFrame bounceFrame = new BounceFrame();
        bounceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bounceFrame.setVisible(true);
    }
}