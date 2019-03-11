package com.algo.mine;

import javax.swing.*;
import java.awt.*;

public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        this.setContentPane(canvas);
        this.pack();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public void setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public void setCanvasHeight(int canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    // TODO:
    private MineData mineData;
    public void render(MineData mineData) {
        this.mineData = mineData;
        this.repaint();
    }

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            super(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );
            g2d.setRenderingHints(hints);

            // TODO:
            int width = AlgoFrame.this.canvasWidth / AlgoFrame.this.mineData.getCols();
            int height = AlgoFrame.this.canvasHeight / AlgoFrame.this.mineData.getRows();

            for (int i = 0; i < AlgoFrame.this.mineData.getRows(); i++) {
                for (int j = 0; j < AlgoFrame.this.mineData.getCols(); j++) {

                    if (AlgoFrame.this.mineData.open[i][j]) {
                        // (i,j)被打开了
                        if (AlgoFrame.this.mineData.isMine(i, j)) {
                            // 显示雷
                            AlgoVisHelper.putImage(
                                    g2d,
                                    j * width,
                                    i * height,
                                    MineData.mineImageUrl
                            );
                        } else {
                            // 显示数字
                            AlgoVisHelper.putImage(
                                    g2d,
                                    j * width,
                                    i * height,
                                    MineData.numberImageUrl(AlgoFrame.this.mineData.getNumber(i, j))
                            );
                        }
                    } else {
                        // 没有打开
                        if (AlgoFrame.this.mineData.flags[i][j]) {
                            // 被标记为旗子
                            AlgoVisHelper.putImage(
                                    g2d,
                                    j * width,
                                    i * height,
                                    MineData.flagImageUrl
                            );
                        } else {
                            AlgoVisHelper.putImage(
                                    g2d,
                                    j * width,
                                    i * height,
                                    MineData.blockImageUrl
                            );
                        }
                    }
/*
                    if (AlgoFrame.this.mineData.isMine(i, j)) {
                        // 显示雷
                        AlgoVisHelper.putImage(
                                g2d,
                                j * width,
                                i * height,
                                MineData.mineImageUrl
                        );
                    } else {
                        // 显示数字
                        AlgoVisHelper.putImage(
                                g2d,
                                j * width,
                                i * height,
                                MineData.numberImageUrl(AlgoFrame.this.mineData.getNumber(i, j))
                        );
                    }
*/
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(
                    AlgoFrame.this.getCanvasWidth(),
                    AlgoFrame.this.getCanvasHeight()
            );
        }
    }
}
