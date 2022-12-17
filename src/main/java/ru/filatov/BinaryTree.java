package ru.filatov;

public class BinaryTree {
    private Node rootNode;

    public BinaryTree() {
        rootNode = null;
    }

    public Node getRootNode(){
        return rootNode;
    }

    public Node findNode(int value){
        // поиск элемента в дереве
        // если значение меньше текущего узла, то идем влево
        // иначе идем вправо.
        Node currentNode = rootNode;

        while (currentNode.getValue() != value){
            if (value < currentNode.getValue()) {
                currentNode = currentNode.getLeft();
            }
            else {
                currentNode = currentNode.getRight();
            }
            if (currentNode == null){
                return null;
            }
        }

        return currentNode;
    }

    public void insertNode(int value){
        // добавление узла в дерево
        // логика аналогична поиску.
        Node newNode = new Node(value);

        if(rootNode == null){
            rootNode = newNode;
        }
        else{
            Node currentNode = rootNode;
            Node parentNode;

            while (true){
                parentNode = currentNode;
                if(value == currentNode.getValue()){
                    return;
                }
                else if (value < currentNode.getValue()){
                    currentNode = currentNode.getLeft();
                    if (currentNode == null){
                        parentNode.setLeft(newNode);
                        newNode.setParent(parentNode);
                        return;
                    }
                }
                else {
                    currentNode = currentNode.getRight();
                    if (currentNode == null) {
                        parentNode.setRight(newNode);
                        newNode.setParent(parentNode);
                        return;
                    }
                }
            }
        }
    }

    public void deleteNode(int value){
        Node currentNode = rootNode;
        Node parentNode = rootNode;
        boolean isLeft = true;

        while (currentNode.getValue() != value){
            // поиск элемента осуществляется по алгоритму, описанному выше.
            parentNode = currentNode;
            if(value < currentNode.getValue()){
                isLeft = true;
                currentNode = currentNode.getLeft();
            }
            else{
                isLeft = false;
                currentNode = currentNode.getRight();
            }
            if (currentNode == null){
                return;
            }
        }

        if (currentNode.getLeft() == null && currentNode.getRight() == null){
            // если у узла нет потомков, то спокойно удаляем его.
            if (currentNode == rootNode){
                rootNode = null;
            }
            else if (isLeft) {
                parentNode.setLeft(null);
            }
            else{
                parentNode.setRight(null);
            }
        }
        else if (currentNode.getRight() == null){
            // если у узла есть левый потомок, то в случае,
            // если узел левый, то левый потомок становится левым узлом родителя,
            // если узел правый, то левый потомок становится правым узлом родителя.
            if (currentNode == rootNode){
                rootNode = currentNode.getLeft();
                rootNode.setParent(null);
            }
            else if (isLeft){
                parentNode.setLeft(currentNode.getLeft());
                currentNode.getLeft().setParent(parentNode);
            }
            else{
                parentNode.setRight(currentNode.getLeft());
                currentNode.getLeft().setParent(parentNode);
            }
        }
        else if (currentNode.getLeft() == null){
            // если у узла есть правый потомок, то в случае,
            // если узел левый, то правый потомок становится левым узлом родителя,
            // если узел правый, то его правый потомк становится правым узлом родителя.
            if (currentNode == rootNode){
                rootNode = currentNode.getRight();
                rootNode.setParent(null);
            }
            else if (isLeft){
                parentNode.setLeft(currentNode.getRight());
                currentNode.getRight().setParent(parentNode);
            }
            else{
                parentNode.setRight(currentNode.getRight());
                currentNode.getRight().setParent(parentNode);
            }
        }
        else{
            // если у узла есть и левый, и правый потомок,
            // то эти потомки передаются узлу-наследнику.
            Node heir = recieveHeir(currentNode);
            if (currentNode == rootNode){
                rootNode = heir;
                rootNode.setParent(null);
            }
            else if (isLeft){
                heir.setParent(parentNode);
                parentNode.setLeft(heir);
            }
            else {
                heir.setParent(parentNode);
                parentNode.setRight(heir);
            }
        }
        currentNode.setParent(null);
    }

    private Node recieveHeir(Node node){
        // метод для получения наследника узла
        Node parentNode = node;
        Node heirNode = node;
        Node currentNode = node.getRight();

        while (currentNode != null){
            parentNode = heirNode;
            heirNode = currentNode;
            currentNode = currentNode.getLeft();
        }
        if(heirNode != node.getRight()){
            parentNode.setLeft(heirNode.getRight());
            heirNode.setRight(node.getRight());
        }

        return heirNode;
    }
}
