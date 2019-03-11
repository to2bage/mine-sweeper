package com.algo.mine;

import javax.swing.*;
import java.awt.*;

public class AlgoVisHelper {

    private AlgoVisHelper() {}

    public static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void putImage(Graphics2D g2d, int x, int y, String imgUrl) {
        ImageIcon icon = new ImageIcon(imgUrl);
        Image image = icon.getImage();

        g2d.drawImage(image, x, y, null);
    }
}
