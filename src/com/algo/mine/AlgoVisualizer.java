package com.algo.mine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    private MineData mineData;
    private AlgoFrame frame;

    public AlgoVisualizer(int ROWS, int COLS, int COUNT, String title) {

        this.mineData = new MineData(ROWS, COLS, COUNT);

        int blockSide = 32;
        int sceneWidth = this.mineData.getCols() * blockSide;
        int sceneHeight = this.mineData.getRows() * blockSide;

        EventQueue.invokeLater(() -> {
            this.frame = new AlgoFrame(title, sceneWidth, sceneHeight);
            this.frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                this.run();
            }).start();
        });
    }

    private void run() {
        this.renderData(false, -1, -1);
    }

    private void renderData(boolean isLeftClick, int row, int col) {
        if (this.mineData.isArea(row, col)) {
            if (isLeftClick) {
                this.mineData.open[row][col] = true;
            } else {
                this.mineData.flags[row][col] = !this.mineData.flags[row][col];
            }
        }

        this.frame.render(this.mineData);
        AlgoVisHelper.pause(10);
    }

    private class AlgoMouseListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent event) {
            super.mouseReleased(event);

            event.translatePoint(
                    0,
                    -(AlgoVisualizer.this.frame.getBounds().height - AlgoVisualizer.this.frame.getCanvasHeight())
            );

            Point pos = event.getPoint();

            int width = AlgoVisualizer.this.frame.getCanvasWidth() / AlgoVisualizer.this.mineData.getCols();
            int height = AlgoVisualizer.this.frame.getCanvasHeight() / AlgoVisualizer.this.mineData.getRows();

            int row = pos.y / height;
            int col = pos.x / width;

            if (SwingUtilities.isLeftMouseButton(event)) {
                // 鼠标左键
                AlgoVisualizer.this.renderData(true, row, col);
            } else if (SwingUtilities.isRightMouseButton(event)) {
                // 鼠标右键
                AlgoVisualizer.this.renderData(false, row, col);
            }
        }
    }
}
