import java.util.Random;

public class BinaryTree<E extends Comparable<E>> {
    private static class Node<T extends Comparable<T>> {
        public T data;
        public Node<T> left, right;

        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node<E> root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(Node<E> sNode) {
        this.root = sNode;
    }

    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        this.root = new Node<E>(data);
        if (leftTree != null) {
            this.root.left = leftTree.root;
        } else {
            this.root.left = null;
        }
        if (rightTree != null) {
            this.root.right = rightTree.root;
        } else {
            this.root.right = null;
        }
    }

    public BinaryTree<E> getLeftSubtree() {
        if (this.root.left == null) {
            return null;
        }
        return new BinaryTree<E>(this.root.left);
    }

    public BinaryTree<E> getRightSubtree() {
        if (this.root.left == null) {
            return null;
        }
        return new BinaryTree<E>(this.root.right);
    }

    /**
     * get root data
     * 
     * @return data of root, otherwise null if not null
     */
    public E getData() {
        return this.root == null ? null : this.root.data;
    }

    public boolean isLeaf() {
        return this.root.left == null && this.root.right == null && root != null;
    }

    private void preOrder(Node<E> sampleRoot) {
        if (sampleRoot == null) {
            System.out.println();
            return;
        }
        System.out.print(root.data.toString() + " ");
        preOrder(sampleRoot.left);
        preOrder(sampleRoot.right);
    }

    public void preOrderTraverse() {
        preOrder(root);
    }

    private void postOrder(Node<E> sampleRoot) {
        if (sampleRoot == null) {
            System.out.println();
            return;
        }
        postOrder(sampleRoot.left);
        postOrder(sampleRoot.right);
        System.out.print(root.data.toString() + " ");
    }

    public void postOrderTraverse() {
        postOrder(root);
    }

    private void InOrder(Node<E> sampleRoot) {
        if (sampleRoot == null) {
            System.out.println();
            return;
        }
        InOrder(sampleRoot.left);
        System.out.print(root.data.toString() + " ");
        InOrder(sampleRoot.right);
    }

    public void inOrderTraverse() {
        InOrder(root);
    }

    /**
     * insert node randomly as a leaf
     * 
     * @param item
     */
    public void insertNode(E item) {
        Node<E> newNode = new Node<E>(item);
        Node<E> parent = null, current;
        int randNum;
        Random rand = new Random();

        if (this.root == null) {
            this.root = newNode;
        } else {
            current = this.root;
            randNum = rand.nextInt(2);
            while (current != null) {
                parent = current;
                if (randNum == 0) {
                    current = current.left;
                } else if (randNum == 1) {
                    current = current.right;
                }
            }
            if (randNum == 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
    }

    public int treeHeight() {
        return Height(root);
    }

    private int Height(BinaryTree.Node<E> root2) {
        if (root2 == null) {
            return 0;
        }
        return 1 + Math.max(Height(root2.left), Height(root2.right));
    }

    private int CountLeftChild(Node<E> node) {
        if (node.left == null && node.right == null) {
            return 0;
        } else if (node.left != null && node.right == null) {
            return 1 + CountLeftChild(node.left);
        } else {
            return 0 + CountLeftChild(node.right);
        }
    }

    public int treeCountLeftChild() {
        return CountLeftChild(root);
    }

    public int countOneChildBT() {
        return countOneChild(root);
    }

    private int countOneChild(Node<E> node) {
        if (node.left == null && node.right == null) {
            return 0;
        } else if (node.left != null && node.right != null) {
            return 0 + countOneChild(node.left) + countOneChild(node.right);
        } else {
            if (node.left == null) {
                return 1 + countOneChild(node.right);
            } else {
                return 1 + countOneChild(node.left);
            }
        }
    }
}
