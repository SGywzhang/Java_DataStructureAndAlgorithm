public class BinaryTreeDemo {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        binaryTree.delNode(5);
//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//        System.out.println("中序遍历");
//        binaryTree.inOrder();
//        System.out.println("后序遍历");
//        binaryTree.postOrder();

//        System.out.println("前序遍历方式~~~");
//        HeroNode resNode = binaryTree.preOrderSearch(5);
//        if (resNode != null) {
//            System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
//        } else {
//            System.out.printf("没有找到 no = %d 的英雄", 5);
//        }
//
//
//        System.out.println("中序遍历方式~~~");
//        resNode = binaryTree.inOrderSearch(5);
//        if (resNode != null) {
//            System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
//        } else {
//            System.out.printf("没有找到 no = %d 的英雄", 5);
//        }
//
//        System.out.println("后序遍历方式~~~");
//        resNode = binaryTree.postOrderSearch(5);
//        if (resNode != null) {
//            System.out.printf("找到了，信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
//        } else {
//            System.out.printf("没有找到 no = %d 的英雄", 5);
//        }
    }
}

class BinaryTree{

    private HeroNode root;

    public void setRoot(HeroNode root){
        this.root = root;
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


