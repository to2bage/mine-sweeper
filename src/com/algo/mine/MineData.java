package com.algo.mine;

public class MineData {

    public static final String blockImageUrl = "resources/block.png";
    public static final String flagImageUrl = "resources/flag.png";
    public static final String mineImageUrl = "resources/mine.png";

    public static String numberImageUrl(int num) {
        if (num < 0 || num >= 8) {
            throw new IllegalArgumentException("num is out of range");
        }

        return "resources/" + num + ".png";
    }

    private int rows;
    private int cols;
    private int mineNumber;
    private boolean[][] mines;
    private int[][] numbers;
    public boolean[][] open;
    public boolean[][] flags;

    public MineData(int rows, int cols, int mineNumber) {

        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("rows cols mineNumbe is wrong number");
        }
        if (mineNumber < 0 || mineNumber > rows * cols) {
            throw new IllegalArgumentException("mineNumber is wrong number");
        }

        this.rows = rows;
        this.cols = cols;
        this.mineNumber = mineNumber;
        this.mines = new boolean[rows][cols];
        this.numbers = new int[rows][cols];
        this.open = new boolean[rows][cols];
        this.flags = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.mines[i][j] = false;
                this.open[i][j] = false;
                this.flags[i][j] = false;
                this.numbers[i][j] = 0;
            }
        }

        this.generateMines(mineNumber);             // 随机的生成雷
        this.calculateNumbers();                    // 计算当前点周围8个方向的雷的个数
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getMineNumber() {
        return mineNumber;
    }

    public void setMineNumber(int mineNumber) {
        this.mineNumber = mineNumber;
    }

    public int getNumber(int row, int col) {
        if (!this.isArea(row, col)) {
            throw new IllegalArgumentException("row or col is out of range");
        }
        return this.numbers[row][col];
    }

    public boolean isArea(int row, int col) {
        return row >= 0 && row < this.getRows() && col >= 0 && col < this.getCols();
    }

    public boolean isMine(int row, int col) {
        if (!isArea(row, col)) {
            throw new IllegalArgumentException("row or col is out of range");
        }
        return this.mines[row][col];
    }

    private void generateMines(int mineNumber) {
        // 先将mineNumber个雷, 顺次的放入this.mines中
        for (int i = 0; i < mineNumber; i++) {
            this.mines[i / this.getCols()][i % this.getCols()] = true;
        }

        for (int i = this.rows * this.cols - 1; i >= 0; i--) {
            int ix = i / this.cols;
            int iy = i % this.cols;

            int randNumber = (int)(Math.random() * (i + 1));
            int randx = randNumber / this.cols;
            int randy = randNumber % this.cols;

            this.swap(ix, iy, randx, randy);
        }
    }

    private void swap(int x1, int y1, int x2, int y2) {
        boolean tmp = this.mines[x1][y1];
        this.mines[x1][y1] = this.mines[x2][y2];
        this.mines[x2][y2] = tmp;
    }

    private void calculateNumbers() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {

                if (this.mines[i][j]) {
                    this.numbers[i][j] = -1;            // mine
                }

                this.numbers[i][j] = 0;
                for (int ii = i - 1; ii <= i + 1; ii++) {
                    for (int jj = j - 1; jj <= j + 1; jj++) {
                        if (this.isArea(ii, jj) && this.mines[ii][jj]) {
                            this.numbers[i][j]++;
                        }
                    }
                }
            }
        }
    }


}
