package splay;

/**
 * Created by gopalbala on 29/4/17.
 */
public class SplayTree<T extends Comparable<T>> {
    Node<T> root;
    private int size;

    void insert(T data) {
        Node<T> tempNode = root;
        Node<T> parentNode = null;

        while (tempNode != null) {
            parentNode = tempNode;
            if (tempNode.getData().compareTo(data) < 0) {
                tempNode = tempNode.getRightChild();
            } else {
                tempNode = tempNode.getLeftChild();
            }
        }

        tempNode = new Node<T>();
        tempNode.setData(data);
        tempNode.setParent(parentNode);

        if (parentNode == null)
            root = tempNode;
        else if (parentNode.getData().compareTo(tempNode.getData()) < 0)
            parentNode.setRightChild(tempNode);
        else
            parentNode.setLeftChild(tempNode);

        splay(tempNode);
        size++;
    }

    public void inorderTraversal() {
        if (root == null)
            return;
        inorderTraversal(root);
    }

    private void inorderTraversal(Node<T> root) {
        if (root.getLeftChild() != null)
            inorderTraversal(root.getLeftChild());
        System.out.println(root.getData());
        if (root.getRightChild() != null)
            inorderTraversal(root.getRightChild());
    }

    public T getMin() {
        if (root == null)
            return null;
        return getMin(root);
    }

    private T getMin(Node<T> node) {
        if (node.getLeftChild() != null)
            return getMin(node.getLeftChild());
        else
            return node.getData();
    }

    public T getMax() {
        if (root == null)
            return null;
        return getMax(root);
    }

    private T getMax(Node<T> node) {
        if (node.getRightChild() != null)
            return getMax(node.getRightChild());
        return node.getData();
    }

    public int size() {
        return this.size;
    }

    public Node<T> find(T data) {
        Node<T> tempNode = root;

        if (tempNode == null)
            return null;
        while (tempNode != null) {
            if (tempNode.getData().compareTo(data) < 0)
                tempNode = tempNode.getRightChild();
            else if (tempNode.getData().compareTo(data) > 0)
                tempNode = tempNode.getLeftChild();
            else {
                splay(tempNode);
                return tempNode;
            }
        }
        return null;
    }

    private void splay(Node<T> node) {
        while (node.getParent() != null) {
            if (node.getParent().getParent() == null) { // zig situation
                if (node.getParent().getLeftChild() == node)
                    rotateRight(node);
                else
                    rotateLeft(node);
            }
            //zig-Zig situation
            else if(node.getParent().getLeftChild() == node &&
                    node.getParent().getParent().getLeftChild() == node.getParent()) {
                rotateRight(node.getParent().getParent());
                rotateRight(node.getParent());
            }
            //zig-Zig situation
            else if(node.getParent().getRightChild() == node &&
                    node.getParent().getParent().getRightChild() == node.getParent()) {
                rotateLeft(node.getParent().getParent());
                rotateLeft(node.getParent());
            }
            //zig-Zag situation
            else if(node.getParent().getLeftChild() == node &&
                    node.getParent().getParent().getRightChild() == node.getParent()) {
                rotateRight(node.getParent());
                rotateLeft(node.getParent());
            }
            //zig-Zag situation
            else {
                rotateLeft(node.getParent());
                rotateRight(node.getParent());
            }
        }
    }

    private void rotateLeft(Node<T> node) {
        Node<T> tmpRightNode = node.getRightChild();


        if (tmpRightNode != null) {
            Node<T> t = tmpRightNode.getLeftChild();
            node.setRightChild(t);
            if (t != null)
                t.setParent(node);
        }

        if (node.getParent() == null)
            tmpRightNode = root;
        else if (node == node.getParent().getRightChild())
            node.getParent().setRightChild(node);
        else
            node.getParent().setLeftChild(node);

        tmpRightNode.setLeftChild(node);
        node.setParent(tmpRightNode);

    }

    public void rotateRight(Node<T> node) {
        Node<T> tmpLefftNode = node.getLeftChild();
        if (tmpLefftNode != null) {
            Node<T> t = tmpLefftNode.getRightChild();
            node.setLeftChild(t);
            if (t != null)
                t.setParent(node);
        }

        if (node.parent == null)
            root = tmpLefftNode;
        else if (node == node.getParent().getLeftChild())
            node.getParent().setLeftChild(node);
        else
            node.getParent().setRightChild(node);

        tmpLefftNode.setRightChild(node);
        node.setParent(tmpLefftNode);
    }
}
