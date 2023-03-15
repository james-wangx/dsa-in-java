package com.codicefun.queue;

public class CircleArrayQueue {
    private final int maxSize;    // 队列的最大容量
    private final int[] arr;      // 模拟队列，存放数据
    private int front;      // 头指针，指向队列头
    private int rear;       // 尾指针，指向队列尾的后一个位置

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据到队列
     *
     * @param n 数据
     */
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据～");
            return;
        }

        arr[rear] = n;
        // 将 rear 后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    /**
     * 获取队列的数据，出队列
     *
     * @return 数据
     */
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取出数据～");
        }

        // 这里需要分析出 front 是指向队列的第一个元素
        // 1、先把 front 对应的值保留到一个临时变量
        int value = arr[front];
        // 2、将 front 后移
        front = (front + 1) % maxSize;

        // 3、将临时保存的变量返回
        return value;
    }

    /**
     * 显示队列的所有数据
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空，无数据～");
            return;
        }

        // 思路：从 front 开始遍历
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 求出当前队列有效数据个数
     *
     * @return 有效数据个数
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 显示队列的头数据
     *
     * @return 头数据
     */
    public int headQueue() {
        return arr[front];
    }
}
