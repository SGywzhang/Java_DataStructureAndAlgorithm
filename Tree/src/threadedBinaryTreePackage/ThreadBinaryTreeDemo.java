package threadedBinaryTreePackage;

public class ThreadBinaryTreeDemo {

    public static void main(String[] args){

        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadBinaryTree threadedBinaryTree = new ThreadBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10 号结点的前驱结点是 =" + leftNode);
        System.out.println("10 号结点的后继结点是=" + rightNode);


}
}


class ThreadBinaryTree{

    private HeroNode root;

    public void setRoot(HeroNode root){
        this.root = root;
    }

    private HeroNode pre = null;

    public  void threadedNodes(){
        this.threadedNodes(root);
    }

    public void threadList(){
        HeroNode node = root;
        while(node !=null){

            while(node.getLeftType() == 0){
                node = node.getLeft();
            }
            System.out.println(node);

            while(node.getRightType() ==1){
                node = node.getRight();
                System.out.println(node);
            }

            node = node.getRight();
        }
    }

    public  void threadedNodes(HeroNode node){

        if(node == null){
            return;
        }

        threadedNodes(node.getLeft());

        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }

        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }

        pre = node;

        threadedNodes(node.getRight());
    }

    public void preOrder(){
        if(root != null){
            this.root.preOrder();
        }
        else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void inOrder(){
        if(root != null){
            this.root.inOrder();
        }
        else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void postOrder(){
        if(root != null){
            this.root.postOrder();
        }
        else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public HeroNode preOrderSearch(int no){
        if(root != null){
            return this.root.preOrderSearch(no);
        }
        else{
            return null;
        }
    }

    public HeroNode inOrderSearch(int no){
        if(root != null){
            return this.root.inOrderSearch(no);
        }
        else{
            return null;
        }
    }

    public HeroNode postOrderSearch(int no){
        if(root != null){
            return this.root.postOrderSearch(no);
        }
        else{
            return null;
        }
    }

    public void delNode(int no){
        if(root != null){
            if(root.getNo() == no){
                root = null;
            }
            else{
                root.delNode(no);
            }
        }
        else{
            System.out.println("空树，不能删除~");
        }
    }

}

class HeroNode{

    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //1. 如果 leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
    // 2. 如果 rightType == 0 表示指向是右子树, 如果 1 表示指向后继结点
    private int leftType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    private int rightType;


    public HeroNode(int no, String name){
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public void delNode(int no){

        if(this.left != null && this.left.no == no){
            this.left = null;
            return;
        }

        if(this.right != null && this.right.no == no){
            this.right = null;
            return;
        }

        if(this.left != null){
            this.left.delNode(no);
        }

        if(this.right != null){
            this.right.delNode(no);
        }
    }

    public void preOrder(){
        System.out.println(this);

        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

    public void inOrder(){
        if(this.left != null){
            this.left.inOrder();
        }
        System.out.println(this);

        if(this.right != null){
            this.right.inOrder();
        }
    }

    public void postOrder(){
        if(this.left != null){
            this.left.postOrder();
        }
        if(this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    public HeroNode preOrderSearch(int no){

        if(this.no == no) return this;

        HeroNode res = null;
        if(this.left != null){
            res = this.left.preOrderSearch(no);
        }
        if(res != null){
            return res;
        }

        if(this.right != null){
            res = this.right.preOrderSearch(no);
        }
        return res;
    }

    public HeroNode inOrderSearch(int no){

        HeroNode res = null;
        if(this.left != null){
            res = this.left.inOrderSearch(no);
        }
        if(res != null){
            return res;
        }
        if(this.no == no) return this;

        if(this.right != null){
            res = this.right.inOrderSearch(no);
        }
        return res;
    }

    public HeroNode postOrderSearch(int no){

        HeroNode res = null;
        if(this.left != null){
            res = this.left.postOrderSearch(no);
        }
        if(res != null){
            return res;
        }

        if(this.right != null){
            res = this.right.postOrderSearch(no);
        }
        if(res != null){
            return res;
        }

        if(this.no == no) return this;

        return res;
    }
}