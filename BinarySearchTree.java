public class BinarySearchTree {

    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public BinarySearchTree(Node root) {
        this.root = root;
        this.size = 1;
    }

    public int getSize() {
        return size;
    }

    public boolean search(int data) {
        return searchRec(root, data);
    }

    private boolean searchRec(Node node, int data) {
        if (node == null) return false;

        if (data == node.getData()) return true;

        if (data < node.getData())
            return searchRec(node.getLeft(), data);
        else
            return searchRec(node.getRight(), data);
    }


    public void insert(int data) {
        if (search(data)) {
            System.out.println("Input not valid");
            return;
        }
        root = insertRec(root, data);
        size++;
    }

    private Node insertRec(Node node, int data) {
        if (node == null) return new Node(data);

        if (data < node.getData())
            node.setLeft(insertRec(node.getLeft(), data));
        else
            node.setRight(insertRec(node.getRight(), data));

        return node;
    }


    public int height() {
        return heightRec(root);
    }

    private int heightRec(Node node) {
        if (node == null) return 0;

        int leftHeight = heightRec(node.getLeft());
        int rightHeight = heightRec(node.getRight());

        return Math.max(leftHeight, rightHeight) + 1;
    }


    public void print_in_order() {
        printInOrderRec(root);
        System.out.println();
    }

    private void printInOrderRec(Node node) {
        if (node != null) {
            printInOrderRec(node.getLeft());
            System.out.print(node.getData() + " ");
            printInOrderRec(node.getRight());
        }
    }


    private int count;

    public int find_kth_smallest(int k) {
        if (k > size) {
            System.out.println("Input not valid");
            return -1;
        }

        count = 0;
        return kthSmallestRec(root, k);
    }

    private int kthSmallestRec(Node node, int k) {
        if (node == null) return -1;

        int left = kthSmallestRec(node.getLeft(), k);
        if (left != -1) return left;

        count++;
        if (count == k) return node.getData();

        return kthSmallestRec(node.getRight(), k);
    }


    public void delete(int data) {
        if (!search(data)) {
            System.out.println("Input not valid");
            return;
        }

        root = deleteRec(root, data);
        size--;
    }

    private Node deleteRec(Node node, int data) {
        if (node == null) return null;

        if (data < node.getData()) {
            node.setLeft(deleteRec(node.getLeft(), data));
        } else if (data > node.getData()) {
            node.setRight(deleteRec(node.getRight(), data));
        } else {

            // Case 1: Leaf node
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }

            // Case 2: One child
            if (node.getLeft() == null) {
                return node.getRight();
            }

            if (node.getRight() == null) {
                return node.getLeft();
            }

            // Case 3: Two children
            int maxLeft = findMax(node.getLeft());
            node.setData(maxLeft);
            node.setLeft(deleteRec(node.getLeft(), maxLeft));
        }

        return node;
    }

    private int findMax(Node node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node.getData();
    }
}
