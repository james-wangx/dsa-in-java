package com.codicefun.dsa.sparsearray;

public class SparseArray {
    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0：没有棋子
        // 1：黑子
        // 2：白子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        // 输出原始的二维数组
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 将二维数组 转 稀疏数组
        // 1、先遍历二维数组，得到非 0 数据的个数
        int sum = 0;
        for (int[] row : chessArr1) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }

        // 2、创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 3、遍历二维数组，将非 0 值存放到稀疏数组
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                int data = chessArr1[i][j];
                if (data != 0) {
                    sparseArr[++count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = data;
                }
            }
        }

        // 输出稀疏数组
        System.out.println("\n得到的稀疏数组为");
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 将稀疏数组恢复陈原始数组
        // 1、先读取稀疏数组的第一行，得到原始数组的大小，并创建
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        // 2、读取稀疏数组的后几行的数据，并付给原始数组
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }
    }
}
