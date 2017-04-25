package redblack;

/**
 * Created by gopalbala on 23/4/17.
 */
public class RedBlackTree<T extends Comparable<T>> {
    Node<T> root;

    public void inorderTraversal() {
        if (root == null)
            return;
        inorderTraversal(root);
    }

    private void inorderTraversal(Node<T> node) {
        if (node.getLeftChild() != null) {
            inorderTraversal(node.getLeftChild());
        }
        System.out.println(node.getData());
        if (node.getRightChild() != null) {
            inorderTraversal(node.getRightChild());
        }
    }

    public void insert(T data) {
        Node<T> node = new Node<T>();
        node.setData(data);

        root = insertNode(root, node);
        fixViolations(node);
    }

    private void fixViolations(Node<T> node) {
        Node<T> parentNode = null;
        Node<T> grandParentNode = null;

        //fix till the root or node color is black and parent is red
        while (node != root && node.getColor() != NodeColor.BLACK
                && node.getParent().getColor() == NodeColor.RED) {
            parentNode = node.getParent();
            grandParentNode = node.getParent().getParent();
            if (parentNode == grandParentNode.getLeftChild()) {
                Node<T> uncle = grandParentNode.getRightChild();
                if (uncle != null && uncle.getColor() == NodeColor.RED) {
                    //case 1 scenario
                    grandParentNode.setColor(NodeColor.RED);
                    parentNode.setColor(NodeColor.BLACK);
                    uncle.setColor(NodeColor.BLACK);
                    node = grandParentNode;
                } else { // uncle is black
                    //check if the node is right child
                    if (node == parentNode.getRightChild()) {
                        leftRotate(parentNode);
                        node = parentNode;
                        parentNode = node.getParent();
                    }
                    //case 3
                    //node is the left child and uncle is black
                    rightRotate(grandParentNode);
                    System.out.println("Re-coloring " + parentNode + " + " + grandParentNode);
                    NodeColor tempColor = parentNode.getColor();
                    parentNode.setColor(grandParentNode.getColor());
                    grandParentNode.setColor(tempColor);
                    node = parentNode;
                }
            } else { // symmetry of the previous if condition
                //parent is right child
                Node<T> uncle = parentNode.getLeftChild();
                if (uncle != null && uncle.getColor() == NodeColor.RED) {
                    //case 1 scenario
                    grandParentNode.setColor(NodeColor.RED);
                    parentNode.setColor(NodeColor.BLACK);
                    uncle.setColor(NodeColor.BLACK);
                    node = grandParentNode;
                } else {
                    if (node == node.getLeftChild()) {
                        rightRotate(parentNode);
                        node = parentNode;
                        parentNode = node.getParent();
                    }
                    leftRotate(grandParentNode);
                    System.out.println("Re-coloring " + parentNode + " + " + grandParentNode);
                    NodeColor tempColor = parentNode.getColor();
                    parentNode.setColor(grandParentNode.getColor());
                    grandParentNode.setColor(tempColor);
                    node = parentNode;
                }
            }
        }

        if( root.getColor() == NodeColor.RED ) {
            System.out.println("Re-coloring the root to black...");
            root.setColor(NodeColor.BLACK);
        }
    }

    private void rightRotate(Node<T> node) {
        System.out.println("Rotating on node: " + node);
        Node<T> tempLeftNode = node.getLeftChild();
        node.setRightChild(tempLeftNode.getRightChild());

        if (node.getLeftChild() != null) {
            node.getLeftChild().setParent(node);
        }

        tempLeftNode.setParent(node.getParent());

        if (tempLeftNode.getParent() == null)
            root = tempLeftNode;
        else if (node == node.getParent().getLeftChild())
            node.getParent().setLeftChild(tempLeftNode);
        else
            node.getParent().setRightChild(tempLeftNode);

        tempLeftNode.setRightChild(node);
        node.setParent(tempLeftNode);
    }

    private void leftRotate(Node<T> node) {
        System.out.println("Rotating on node: " + node);
        Node<T> tempRightNode = node.getRightChild();
        tempRightNode.setLeftChild(node);

        if (node.getRightChild() != null) {
            node.getRightChild().setParent(node);
        }

        tempRightNode.setParent(node.getParent());

        if (tempRightNode.getParent() == null)
            root = tempRightNode;
        else if (node == node.getParent().getLeftChild())
            node.getParent().setLeftChild(tempRightNode);
        else
            node.getParent().setRightChild(tempRightNode);

        tempRightNode.setLeftChild(node);
        node.setParent(tempRightNode);
    }

    private Node<T> insertNode(Node<T> root, Node<T> node) {
        if (root == null)
            return node;
        if (node.getData().compareTo(root.getData()) < 0) {
            root.setLeftChild(insertNode(root.getLeftChild(), node));
            root.getLeftChild().setParent(root);
        } else if (node.getData().compareTo(root.getData()) > 0) {
            root.setRightChild(insertNode(root.getRightChild(), node));
            root.getRightChild().setParent(root);
        }
        return root;
    }
}
