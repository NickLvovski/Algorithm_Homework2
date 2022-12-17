package ru.filatov;

public class Traversal {
    private static final Traversal INSTANCE = new Traversal();

    private Traversal() {}

    public static Traversal getInstance(){
        return INSTANCE;
    }

    public void preOrder(Node node){
        // прямой обход
        node.print();

        if (node.getLeft() != null){
            preOrder(node.getLeft());
        }
        if (node.getRight() != null){
            preOrder(node.getRight());
        }
    }

    public void postOrder(Node node){
        // обратный обход
        if(node.getLeft() != null){
            postOrder(node.getLeft());
        }
        if(node.getRight() != null){
            postOrder(node.getRight());
        }

        node.print();
    }

    public void inOrder(Node node){
        // симметричный обход
        if(node.getLeft() != null){
            inOrder(node.getLeft());
        }

        node.print();

        if(node.getRight() != null){
            inOrder(node.getRight());
        }
    }

    public void fillTreeA(Node node, BinaryTree newTree){
        // инициализация дерева A в обратном порядке.
        if(node.getLeft() != null){
            fillTreeA(node.getLeft(), newTree);
        }
        if(node.getRight() != null){
            fillTreeA(node.getRight(), newTree);
        }

        newTree.insertNode(node.getValue());
    }
}
