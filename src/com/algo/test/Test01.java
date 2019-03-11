package com.algo.test;

import com.algo.mine.AlgoVisualizer;

public class Test01 {
    public static void main(String[] args) {

        int rows = 20;
        int cols = 20;
        int mineNumber = 50;
        String title = "扫雷";

        AlgoVisualizer visualizer =
                new AlgoVisualizer(rows, cols, mineNumber, title);
    }
}
