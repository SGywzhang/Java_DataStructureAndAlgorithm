public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        // 测试
        System.out.println("双向链表的测试");
        // 先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.show();
        // 修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.show();
        // 删除
        doubleLinkedList.delete(3);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.show();

    }
}


class DoubleLinkedList{
    private  HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead(){
        return head;
    }
    public void show(){
        if(head.next==null){
            System.out.println("empty list");
            return;
        }
        HeroNode2 temp = head.next;
        while(temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void add(HeroNode2 herNode){

        HeroNode2 temp = head;

        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = herNode;
        herNode.pre = temp;
    }

    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        HeroNode2 temp = head.next;
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

        if(head.next == null){
            System.out.println("this is an empty double linked list");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false;
        while(temp != null){
            if(temp.no == n){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.pre.next = temp.next;
            if(temp.next!=null){
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.printf("要删除的 %d 节点不存在\n", n);
        }
    }
}



class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname){
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



