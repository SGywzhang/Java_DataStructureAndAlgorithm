import java.util.Scanner;

public class HashTableDemo {

    public static void main(String[] args) {


        HashTable ht = new HashTable(7);


        String key = "";
        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = sc.next();


            switch (key){
                case "add":
                    System.out.println("输入 id");
                    int id = sc.nextInt();
                    System.out.println("输入名字");
                    String name = sc.next();
                    Emp emp = new Emp(id, name);
                    ht.add(emp);
                    break;
                case "list":
                    ht.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的 id");
                    id = sc.nextInt();
                    ht.findEmpById(id);
                    break;
                case "exit":
                    sc.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}


class HashTable {

    private  EmpLinkedList[] empLinkedListArray;

    private int size;

    public  HashTable(int size){
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];

        for (int i = 0; i < size ; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp){

        int empLinkedListNo = hashFun(emp.id);

        empLinkedListArray[empLinkedListNo].add(emp);
    }

    public int hashFun(int id ){
        return id% size;
    }

    public void list(){

        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    public void findEmpById(int id) {
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {//找到
            System.out.printf("在第%d 条链表中找到 雇员 id = %d\n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }
}




class EmpLinkedList{
    private  Emp head;

    public void add (Emp emp){

        if(head == null){
            head = emp;
            return;
        }

        Emp cur = head;

        while(true){

            if(cur.next == null){
                break;
            }
            cur = cur.next;
        }

        cur.next = emp;
    }

    public void list(int no) {
        if (head == null) {
            return;
        }
        System.out.print("第 " + (no + 1) + " 链表的信息为");
        Emp curEmp = head; //辅助指针
        while (true) {
            System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {//说明 curEmp 已经是最后结点
                break;
            }
            curEmp = curEmp.next; //后移，遍历
        }
        System.out.println();
    }

    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }

        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }

            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}