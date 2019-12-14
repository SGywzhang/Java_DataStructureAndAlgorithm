import java.util.Scanner;

public class ArrayQueue {

    public static void main(String[] args) {

        Queue queue = new Queue(3);
        char key = ' ';
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show): show queue");
            System.out.println("e(exit): exit program");
            System.out.println("a(add): enqueue");
            System.out.println("g(get): dequeue");
            System.out.println("h(head): peak");
            key = sc.next().charAt(0);

            switch (key){
                case  's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("input a number");
                    queue.enqueue(sc.nextInt());
                    break;
                case 'g':
                    try{
                        int res = queue.dequeue();
                        System.out.printf("get number:%d\n",res);
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int res = queue.peak();
                        System.out.printf("get peak :%d\n",res);

                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    sc.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("exit program");
    }
}

class Queue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public Queue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    public boolean isFull(){
        return rear == maxSize - 1;
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public void enqueue(int n){
        if(isFull()){
            System.out.println("Queue is full");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    public int dequeue(){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        front++;
        return arr[front];
    }

    public void showQueue(){
        if(isEmpty()){
            System.out.println("Queue is empty");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n",i,arr[i]);
        }
    }

    public int peak(){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        return arr[front + 1];
    }
}
