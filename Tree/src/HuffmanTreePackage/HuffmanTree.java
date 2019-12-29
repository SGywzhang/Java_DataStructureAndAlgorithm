package HuffmanTreePackage;

import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

    public static void main(String[] args) {

        int[] arr = {13,7,8,3,29,6,1};

        preOrder(createHuffmanTree(arr));


    }

    public static Node createHuffmanTree(int[] arr){
        List<Node> nodes = new ArrayList<>();

        for (int val: arr) {
            nodes.add(new Node(val));
        }

        while(nodes.size() > 1){
            Collections.sort(nodes);

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node parent = new Node(leftNode.val + rightNode.val);

            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);

            nodes.add(parent);
        }
        return nodes.get(0);
    }

    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("this is an empty tree");
        }
    }
}

class Node implements Comparable<Node>{
    int val;
    Node left;
    Node right;

    public void preOrder(){

        System.out.println(this);

        if(this.left != null){
            this.left.preOrder();
        }

        if(this.right != null){
            this.right.preOrder();
        }
    }

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.val - o.val;
    }
}
