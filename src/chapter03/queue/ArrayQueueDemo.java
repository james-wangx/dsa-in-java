package chapter03.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        // 创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key; // 接收用户输入
        Scanner input = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("a(add) : 添加数据");
            System.out.println("g(get) : 取出数据");
            System.out.println("h(head): 看头数据");
            System.out.println("e(exit): 退出程序");
            key = input.next().charAt(0); // 接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.print("请输入一个数: ");
                    int value = input.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.println("取出的数据是: " + res);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.println("队列头数据是: " + res);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    input.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出");
    }
}

// 使用数组模拟队列，编写一个 ArrayQueue 类
class ArrayQueue {
    private final int maxSize;    // 队列的最大容量
    private final int[] arr;      // 模拟队列，存放数据
    private int front;      // 头指针
    private int rear;       // 尾指针

    // 创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1; // 指向队列头的前一个位置
        rear = -1; // 指向队列尾的位置
    }

    /**
     * 判断队列是否满
     *
     * @return true or false
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     *
     * @return true or false
     */
    public boolean isEmpty() {
        return front == rear;
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

        arr[++rear] = n;
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

        return arr[++front];
    }

    /**
     * 显示队列的所有数据
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空，无数据～");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    /**
     * 显示队列的头数据
     *
     * @return 头数据
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，无数据～");
        }

        return arr[front + 1];
    }
}
