package bst;

/**
 * Created by gopalbala on 22/4/17.
 */
public class BinarySearchTree<T extends Comparable<T>> {
    Node<T> root;

    public void insert(T data){
        if (root == null){
            root = new Node<T>();
            root.setData(data);
        }else {
            insertData(data, root);
        }
    }

    private void insertData(T data, Node<T> root) {
        if (data.compareTo(root.getData())<0){
            if (root.getLeftChild() != null)
                insertData(data,root.getLeftChild());
            else {
                Node<T> newNode = new Node<T>();
                newNode.setData(data);
                root.setLeftChild(newNode);
            }
        }else {
            if (root.getRightChild()!=null)
                insertData(data,root.getRightChild());
            else {
                Node<T> newNode = new Node<T>();
                newNode.setData(data);
                root.setRightChild(newNode);
            }
        }
    }

    public T getMaxValue() {
        if (root == null)
            return null;
        else return getMax(root);
    }

    public T getMinValue() {
        if (root == null)
            return null;
        else return getMin(root);
    }

    private T getMin(Node<T> root) {
        while (root.getLeftChild() != null) {
            root = root.getLeftChild();
        }
        return root.data;
    }

    private T getMax(Node<T> root) {
        while (root.getRightChild() != null){
            root = root.getRightChild();
        }
        return root.getData();
    }

    public void inorderTraversal(){
        if (root != null)
            inorderTraversal(root);
    }

    private void inorderTraversal(Node<T> node) {
        if (node == null) {
            return;
        }

        if (node.getLeftChild() != null){
            inorderTraversal(node.getLeftChild());
        }

        System.out.print(node + "  -->  ");

        if (node.getRightChild() != null) {
            inorderTraversal(node.getRightChild());
        }
    }

    public void delete(T data) {
        if (root != null)
            delete(root, data);
    }

    private Node<T> delete(Node<T> node, T data) {
        if (node == null)
            return node;
        if (data.compareTo(node.getData()) <0){
            node.setLeftChild(delete(node.getLeftChild(), data));
        }
        else if(data.compareTo(node.getData()) >0) {
            node.setRightChild(delete(node.getRightChild(), data));
        }else {
            //found the node to be removed
            //case 1: both left and right child are null i.e. leaf node
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                node = null;
                return null;
            }
            //removing item with one right child
            else if(node.getLeftChild() == null) {
                Node<T> tmpNode = node.getRightChild();
                node = null;
                return tmpNode;
            }
            //removing item with one left child
            else if( node.getRightChild() == null) {
                Node<T> tmpNode = node.getLeftChild();
                node = null;
                return tmpNode;
            }
            //item with two nodes not null
            Node<T> tmpNode = getPredecessor(node.getLeftChild());
            node.setData(tmpNode.getData());
            node.setLeftChild(delete(node.getLeftChild(), tmpNode.getData()));
        }
        return node;
    }

    private Node<T> getPredecessor(Node<T> node) {
        if(node.getRightChild() != null) {
            return getPredecessor(node.getRightChild());
        }
        System.out.println("Predecessor is : " + node.data);
        return node;
    }
}
