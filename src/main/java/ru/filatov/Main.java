package ru.filatov;

public class Main{
    public static void main(String[] args) {
        Traversal traversal = Traversal.getInstance();
        int[] values = {51, 43, 12, 45, 1, 52, 53, 3, 4, 212, 90, 34};
        BinaryTree treeB = new BinaryTree();
        BinaryTree treeA = new BinaryTree();

        for(int value : values){
            treeB.insertNode(value);
        }
        traversal.fillTreeA(treeB.getRootNode(), treeA); // заполнение дерева A
        System.out.println("Прямой обход дерева A:");
        traversal.preOrder(treeA.getRootNode());
        System.out.println("\nСимметричный обход дерева B:");
        traversal.inOrder(treeB.getRootNode());
    }
}
