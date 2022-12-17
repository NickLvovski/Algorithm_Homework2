package ru.filatov;

public class Node {
    // класс "узла" дерева
    private final int value;
    private Node left;
    private Node right;
    private Node parent;

    public Node(Node parent, int value){
        this.parent = parent;
        this.value = value;
    }

    public Node(int value){
        this(null, value);
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void print(){
        // метод для вывода информации об узле
        String rightValue = getRight() == null? "null" : Integer.toString(getRight().getValue());
        String leftValue = getLeft() == null? "null" : Integer.toString(getLeft().getValue());
        String parentValue = getParent() == null? "null" : Integer.toString(getParent().getValue());
        String info = String.format(
                """
                Node{value=%d, leftValue=%s, rightValue=%s parent=%s}""",
                getValue(), leftValue, rightValue, parentValue);

        System.out.println(info);
    }
}
