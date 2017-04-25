package avl;

/**
 * Created by gopalbala on 23/4/17.
 */
public class AvlTree<T extends Comparable<T>> {
    private Node<T> root;

    public void insert(T data) {
        root = insert(root, data);
    }

    public void delete(T data) {
        root = delete(root, data);
    }

    private Node<T> delete(Node<T> node, T data) {
        if (node == null) {
            return node;
        }
        if (node.getData().compareTo(data) < 0) {
            node.setLeftChild(delete(node.getLeftChild(), data));
        } else if (node.getData().compareTo(data) > 0) {
            node.setRightChild(delete(node.getRightChild(), data));
        } else {
            //we fond the node to delete
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                node = null;
                return node;
            } else if (node.getLeftChild() == null) {
                Node<T> tmpNode = node.getRightChild();
                node = null;
                return tmpNode;
            } else if (node.getRightChild() == null) {
                Node<T> tmpNode = node.getLeftChild();
                node = null;
                return tmpNode;
            }
            Node<T> tmpNode = getPredecessor(node.getLeftChild());
            node.setData(tmpNode.getData());
            node.setLeftChild(delete(node.getLeftChild(),node.data));
        }
        node.setHeight(Math.max(height(node.getLeftChild()),height(node.getRightChild())) +1);
        return settleDeletion(node);
    }

    private Node<T> settleDeletion(Node<T> node) {
        int balance = getBalance(node);
        if (balance > 1) {
            // left right heavy situation: left rotation on parent + right rotation on grandparent
            if (getBalance(node.getLeftChild()) <0){
                node.setLeftChild(leftRotation(node.getLeftChild()));
            }
            return rightRotation(node);
        }
        if (balance < 1) {
            if (getBalance(node.getRightChild()) > 0) {
                node.setRightChild(rightRotation(node.getRightChild()));
            }
            return leftRotation(node);
        }
        return node;
    }

    private Node<T> getPredecessor(Node<T> node) {
        Node<T> predecessor = node;
        while (predecessor.getRightChild() != null)
            predecessor = predecessor.getRightChild();

        return predecessor;
    }

    private Node<T> insert(Node<T> node, T data) {
        if (node == null) {
            Node<T> newNode = new Node<T>();
            newNode.setData(data);
            return newNode;
        }
        if (data.compareTo(node.getData()) < 0) {
            insert(node.getLeftChild(), data);
        } else {
            insert(node.getRightChild(), data);
        }
        node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
        return settleViolation(data, node);
    }

    private Node<T> settleViolation(T data, Node<T> node) {
        int balance = getBalance(node);
        if (balance > 1 && data.compareTo(node.getLeftChild().getData()) < 0) {
            //tree is left heavy
            return rightRotation(node);
        }
        if (balance < -1 && data.compareTo(node.getRightChild().getData()) > 0) {
            //tree is right heavy
            return leftRotation(node);
        }
        if (balance > 1 && data.compareTo(node.getLeftChild().getData()) > 0) {
            //tree is left right heavy
            node.setLeftChild(leftRotation(node.getLeftChild()));
            return rightRotation(node);
        }
        if (balance < 1 && data.compareTo(node.getRightChild().getData()) < 0) {
            //tree is right left heavy
            node.setRightChild(rightRotation(node.getRightChild()));
            return leftRotation(node);
        }
        return node;
    }

    private Node<T> leftRotation(Node<T> node) {
        System.out.println("Rotating on left node: " + node);
        Node<T> tmpRightNode = node.getRightChild();
        Node<T> t = tmpRightNode.getLeftChild();
        tmpRightNode.setLeftChild(node);
        node.setRightChild(t);

        node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
        tmpRightNode.setHeight(Math.max(height(tmpRightNode.getLeftChild()), height(tmpRightNode.getRightChild())) + 1);

        return tmpRightNode;
    }

    private Node<T> rightRotation(Node<T> node) {
        System.out.println("Rotating on right node: " + node);
        Node<T> tmpLeftNode = node.getLeftChild();
        Node<T> t = tmpLeftNode.getRightChild();
        tmpLeftNode.setRightChild(node);
        node.setLeftChild(t);

        node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
        tmpLeftNode.setHeight(Math.max(height(tmpLeftNode.getLeftChild()), height(tmpLeftNode.getRightChild())) + 1);

        return tmpLeftNode;
    }

    private int height(Node<T> node) {
        if (node == null)
            return -1;
        return node.getHeight();
    }

    private int getBalance(Node<T> node) {
        if (node == null)
            return 0;
        return height(node.getLeftChild()) - height(node.getRightChild());
    }

    public void traverse() {

        if (root == null)
            return;

        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node<T> node) {

        if (node.getLeftChild() != null)
            inOrderTraversal(node.getLeftChild());

        System.out.println(node);

        if (node.getRightChild() != null)
            inOrderTraversal(node.getRightChild());
    }
}
