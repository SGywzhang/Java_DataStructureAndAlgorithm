public class Josepfu {


    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);// 加入 5 个小孩节点
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1, 2, 5); // 2->4->1->5->3

    }


}


class CircleSingleLinkedList{

    private Boy first = null;

    public void addBoy(int nums){
        if(nums < 1){
            System.out.println("nums invalid");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);

            if(i==1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            }
            else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    public void showBoy(){
        if(first==null){
            System.out.println("list is empty");
            return;
        }
        Boy curBoy = first;
        while(true){
            System.out.printf("boy number :%d \n",curBoy.getNo());
            if(curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }
    // 根据用户的输入，计算出小孩出圈的顺序

    /**
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums    表示最初有多少小孩在圈中
     */

    public void countBoy (int startNo, int countNum, int nums){

        if(first == null || startNo <1 || startNo > nums){
            System.out.println("invalid input");
            return;
        }
        Boy helper = first;

        while (true){
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }

        for (int i = 0; i < startNo-1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while(true){
            if(helper == first){
                break;
            }
            for (int i = 0; i < countNum-1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩%d 出圈\n", first.getNo());

            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());
    }
}


class Boy{
    private int no;
    private Boy next;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy(int no){
        this.no = no;
    }
}
