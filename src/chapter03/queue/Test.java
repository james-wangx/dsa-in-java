package chapter03.queue;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        // ArrayQueue queue = new ArrayQueue(3);
        CircleArrayQueue queue = new CircleArrayQueue(6);
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
