import java.util.Scanner;

public class CircularArrayQueue {

    public static void main(String[] args) {

        CircularQueue queue = new CircularQueue(4);
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

class CircularQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CircularQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    public boolean isFull(){
        return front == (rear+1)%maxSize;
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public void enqueue(int n){
        if(isFull()){
            System.out.println("Queue is full");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public int dequeue(){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        int pos = front;
        front = (front + 1) % maxSize;
        return arr[pos];
    }

    public void showQueue(){
        if(isEmpty()){
            System.out.println("Queue is empty");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    public int size(){
        return (rear+maxSize-front) % maxSize;
    }

    public int peak(){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        return arr[front];
    }
}
