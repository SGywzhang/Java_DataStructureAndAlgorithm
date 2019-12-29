package BinarySearchTreePackage;

public class BinarySearchTreeDemo {

    public static void main(String[] args) {

        int[] arr = {7,3,10,12,5,1,9,0};

        BinarySearchTree bt = new BinarySearchTree();

        for (int i = 0; i < arr.length ; i++) {

            bt.add(new Node(arr[i]));
        }

        //bt.inOrder();

        //bt.delNode(2);
        bt.delNode(7);
        //bt.delNode(9);

        bt.inOrder();
    }
}

class BinarySearchTree{

    private Node root;

    public Node search(int val){

        if(root == null){
            return null;
        }
        else{
            return root.search(val);
        }
    }

    public Node searchParent(int val){

        if(root == null){
            return null;
        }
        else{
            return root.searchParent(val);
        }
    }


    public int delRightTreeMin (Node node){

        Node target = node;

        while(target.left != null){
            target = target.left;
        }

        delNode(target.val);

        return  target.val;
    }


    public void delNode(int val){
        if(root == null) return;
        else{
            Node targetNode = search(val);

            if(targetNode == null){
                return;
            }

            if(root.left == null && root.right == null){
                root = null;
                return;
            }

            Node parent = searchParent(val);

            if(targetNode.left == null && targetNode.right==null){

                if(parent.left != null && parent.left.val == val){
                    parent.left = null;
                }
                else if(parent.right != null && parent.right.val == val){
                    parent.right = null;
                }
            }
            else if(targetNode.left != null && targetNode.right != null){
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.val = minVal;
            }
            else{
                if(targetNode.left != null){
                    if(parent != null){
                        if(parent.left == targetNode){
                            parent.left = targetNode.left;
                        }
                        else{
                            parent.right = targetNode.left;
                        }
                    }
                    else{
                        root = targetNode.left;
                    }

                }else{
                    if(parent != null) {
                        if (parent.left == targetNode) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    }
                    else{
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    public void add(Node node){

        if(root == null){

            root = node;
        }
        else{
            root.add(node);
        }
    }

    public void inOrder(){

        if(root != null){
            root.inOrder();
        }
        else{
            System.out.println("Empty");
        }

    }
}

class Node{
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
    }

    public Node search (int val){

        if(val == this.val){
            return this;
        }
        else if(val < this.val){
            if(this.left == null) return null;
            return this.left.search(val);
        }
        else{

            if(this.right == null) return null;
            return this.right.search(val);
        }
    }

    public Node searchParent(int val){

        if((this.left != null &&  this.left.val == val) ||
           (this.right != null &&  this.right.val == val)){
            return this;
        }else{
            if(val < this.val && this.left != null){
                return this.left.searchParent(val);
            }
            else if(val >= this.val && this.right != null){
                return this.right.searchParent(val);
            }
            else{
                return null;
            }
        }
    }

    public void add(Node node){

        if(node == null) return;

        if(node.val < this.val){

            if(this.left == null){
                this.left = node;
            }else{
                this.left.add(node);
            }
        }
        else{
            if(this.right == null){
                this.right = node;
            }
            else{
                this.right.add(node);
            }
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
    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}
