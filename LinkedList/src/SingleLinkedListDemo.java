import java.util.Queue;
import java.util.Stack;

public class SingleLinkedListDemo {

    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        singleLinkedList.show();

        reverserList(singleLinkedList.getHead());
        System.out.println();
        System.out.println("***************");
        System.out.println();

        singleLinkedList.show();

        System.out.println();
        System.out.println("***************");
        System.out.println();

        reversePrint(singleLinkedList.getHead());

//        singleLinkedList.delete(1);
//        System.out.println("***************");
//        singleLinkedList.show();
//
//        System.out.println(getLength(singleLinkedList.getHead()));
//
//        HeroNode res = findLastIndexNode(singleLinkedList.getHead(),2);
//        System.out.println(res);

    }

    /**
     *
     * @param head of the single linked list
     * @return the number of the valid nodes
     */
    public static int getLength(HeroNode head){
        if(head.next == null) return 0;

        int length =0;

        HeroNode current = head.next;
        while(current != null){
            length ++;
            current = current.next;
        }
        return length;
    }

    //查找单链表中的倒数第 k 个结点 【新浪面试题】
    public static  HeroNode findLastIndexNode (HeroNode head, int index){
        if(head.next == null) return null;

        int size = getLength(head);

        if(index <= 0 || index > size) return null;

        HeroNode cur = head.next;

        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //单链表的反转【腾讯面试题】
    public static void reverserList(HeroNode head){
        if(head.next == null || head.next.next == null){
            return;
        }

        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0,"","");

        while(cur!=null){
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    //从尾到头打印单链表【百度，要求方式1:反向遍历。方式2:Stack栈】

    public static void reversePrint(HeroNode head){
        if(head.next == null) return;

        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while(cur!=null){
            stack.push(cur);
            cur = cur.next;
        }

        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}

class SingleLinkedList{

    private  HeroNode head = new HeroNode(0,"","");

    public void add(HeroNode herNode){

        HeroNode temp = head;

        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = herNode;
    }

    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;

        while(temp.next != null){
            if(temp.next.no > heroNode.no){
                break;
            }else if (temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            System.out.printf("Hero number: %d already exists\n",heroNode.no);
        }else{
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                    flag = true;
                    break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    public void delete(int n){

        HeroNode temp = head;
        boolean flag = false;
        while(temp.next != null){
            if(temp.next.no == n){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }else{
            System.out.printf("要删除的 %d 节点不存在\n", n);
        }
    }


    public void show(){
        if(head.next==null){
            System.out.println("empty list");
            return;
        }
        HeroNode temp = head.next;
        while(temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public HeroNode getHead(){
        return head;
    }
}

class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +
                '}';
    }
}