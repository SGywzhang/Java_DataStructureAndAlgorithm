package HuffmanCodePackage;

import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.io.*;
import java.util.*;

public class HuffmanCode {

    static Map<Byte, String> huffmanCodes = new HashMap<>();

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {

        String src = "/Users/zhangyaowen/Desktop/1.png";
        String dst = "/Users/zhangyaowen/Desktop/1.zip";

        //zipFile(src,dst);

        unzipFile(dst, "/Users/zhangyaowen/Desktop/recover.png");

//        String content = "i like like like java do you like a java";
//
//        byte[] contentBytes = content.getBytes();
//
//        System.out.println(contentBytes.length);
//
//        List<Node> nodes = getNodes(contentBytes);
//
//        //System.out.println(getNodes(contentBytes));
//
//        System.out.println("Huffman tree");
//
//        Node huffmanTreeRoot = createHuffmanTree(nodes);
//
//        huffmanTreeRoot.preOrder();
//
//        getCodes(huffmanTreeRoot);
//
//        System.out.println("huffman table: " + huffmanCodes);
//
//        byte[] huffmanCodeBytes = zip(contentBytes,huffmanCodes);
//
//        System.out.println("huffmanCodeBytes: " + Arrays.toString(huffmanCodeBytes));

//        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
//
//        System.out.println("huffmanCodeBytes: " + Arrays.toString(huffmanCodeBytes));
//
//        byte[] recover = decode(huffmanCodeBytes);
//
//        System.out.println(new String(recover));
    }

    public static void zipFile(String srcFile, String dstFile) {

        try (InputStream is = new BufferedInputStream(new FileInputStream(srcFile));
             ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(dstFile))) {

            byte[] data = new byte[is.available()];

            is.read(data);

            byte[] huffmanBytes = huffmanZip(data);

            os.writeObject(huffmanBytes);
            os.writeObject(huffmanCodes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unzipFile(String srcFile, String dstFile) {

        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(srcFile));
             OutputStream os = new BufferedOutputStream(new FileOutputStream(dstFile))) {

            byte[] huffmanBytes = (byte[]) is.readObject();

            Map<Byte, String> huffmanCodes = (Map<Byte, String>) is.readObject();

            byte[] bytes = decode(huffmanBytes, huffmanCodes);

            os.write(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static byte[] decode(byte[] huffmanBytes, Map<Byte, String> huffmanCodes) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < huffmanBytes.length; i++) {

            byte b = huffmanBytes[i];

            boolean flag = (i == huffmanBytes.length - 1);

            sb.append(byteToBitString(!flag, b));
        }

        Map<String, Byte> map = new HashMap<>();

        huffmanCodes.forEach((k, v) -> map.put(v, k));

        List<Byte> list = new ArrayList<>();

        for (int i = 0; i < sb.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                String key = sb.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }

            }
            list.add(b);
            i += count;
        }

        byte[] b = new byte[list.size()];

        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    private static String byteToBitString(boolean flag, Byte b) {

        int temp = b;

        if (flag)
            temp |= 256;

        String str = Integer.toBinaryString(temp);

        if (flag)
            return str.substring(str.length() - 8);
        else
            return str;
    }

    private static byte[] huffmanZip(byte[] bytes) {

        List<Node> nodes = getNodes(bytes);

        Node huffmanTreeRoot = createHuffmanTree(nodes);

        getCodes(huffmanTreeRoot);

        return zip(bytes, huffmanCodes);
    }

    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        StringBuilder sb = new StringBuilder();

        for (byte b : bytes) {
            sb.append(huffmanCodes.get(b));
        }

        int len;

        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }

        byte[] huffmanCodesBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String strByte;
            if (i + 8 > sb.length()) {
                strByte = sb.substring(i);
            } else {
                strByte = sb.substring(i, i + 8);
            }
            huffmanCodesBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodesBytes;
    }


    private static void getCodes(Node node, String code, StringBuilder sb) {

        StringBuilder sb2 = new StringBuilder(sb);

        sb2.append(code);

        if (node != null) {

            if (node.data == null) {
                getCodes(node.left, "0", sb2);
                getCodes(node.right, "1", sb2);
            } else {
                huffmanCodes.put(node.data, sb2.toString());
            }
        }
    }

    private static Map<Byte, String> getCodes(Node root) {

        if (root == null) {
            return null;
        }

        getCodes(root.left, "0", sb);
        getCodes(root.right, "1", sb);

        return huffmanCodes;
    }

    private static void preOrder(Node root) {

        if (root != null) root.preOrder();
        else System.out.println("Tree is empty");

    }

    private static List<Node> getNodes(byte[] bytes) {

        List<Node> nodes = new ArrayList<>();

        Map<Byte, Integer> counts = new HashMap<>();

        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {

                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        counts.forEach((k, v) -> {
            nodes.add(new Node(k, v));
        });
        return nodes;
    }

    private static Node createHuffmanTree(List<Node> list) {

        while (list.size() > 1) {

            Collections.sort(list);

            Node leftNode = list.get(0);
            Node rightNode = list.get(1);

            Node parent = new Node(null, leftNode.weight + rightNode.weight);

            parent.left = leftNode;
            parent.right = rightNode;

            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parent);
        }
        return list.get(0);
    }
}

class Node implements Comparable<Node> {
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) this.left.preOrder();
        if (this.right != null) this.right.preOrder();
    }
}
