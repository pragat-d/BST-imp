package BST;

public class BSTimp {
	public class Node {
		int val;
		Node right;
		Node left;

		Node() {
		}

		Node(int val) {
			this.val = val;
			this.right = null;
			this.left = null;
		}
	}

	Node root;

	BSTimp() {
		root = null;
	}

	public void insert(int num) {
		root = insertRec(root, num);
	}

	public Node insertRec(Node insert, int num) {
		if (insert == null)
			return new Node(num);
		if (insert.val > num) {
			insert.left = insertRec(insert.left, num);
		} else {
			insert.right = insertRec(insert.right, num);
		}

		return insert;
	}

	public void print() {
		printRec(root);
	}

	private void printRec(Node traverse) {

		if (traverse.left != null)
			printRec(traverse.left);
		System.out.println(traverse.val);
		if (traverse.right != null)
			printRec(traverse.right);

	}

	public void treeMin() {
		Node min = treeMinRec(root);
		System.out.println(min.val);
	}

	private Node treeMinRec(Node min) {
		if (min.left != null)
			return treeMinRec(min.left);
		return min;
	}

	public void parent(int num) {
		if (root.val == num)
			System.out.println("Root node does not have parent node");
		else {
			Node parent = parentRec(root, num, root);
			System.out.println(parent.val);
		}
	}

	private Node parentRec(Node node, int num, Node parent) {
		if (node.val == num)
			return parent;
		if (node.val > num)
			return parentRec(node.left, num, node);
		else if (node.val < num)
			return parentRec(node.right, num, node);

		return parent;

	}

	public void searchNode(int num) {
		Node found = searchNodeRec(root, num);
		System.out.println(found.val);
	}

	private Node searchNodeRec(Node found, int num) {
		if (found.val == num)
			return found;
		if (found.val > num)
			return searchNodeRec(found.left, num);
		else
			return searchNodeRec(found.right, num);
	}

	public void successor(int num) {
		Node nodeSearch = searchNodeRec(root, num);
		Node successor = successorRec(nodeSearch, num);
		System.out.println(successor.val);
	}

	private Node successorRec(Node successor, int num) {
		if (successor.right != null)
			return treeMinRec(successor.right);

		Node y = parentRec(root, num, root);

		while (y != null && successor.val == y.right.val) {
			successor = y;
			y = parentRec(root, successor.val, root);
		}
		return y;
	}
	
	public void delete(int num) {
		 Node node = deleteRec(root,num);
		 //System.out.println(node.val);
	}
	
	private Node deleteRec(Node deleteNode , int num) {
		Node searchNode = searchNodeRec(root,num);
		Node y ;
		Node x;
		if(searchNode.left == null || searchNode.right == null) {
			y = searchNode;
		}else {
			y = successorRec(searchNode, num);
		}
		if(y.left != null)
			x = y.left;
		else
			x = y.right;
		if(x != null) {
			Node parentOfx = parentRec(root, x.val, root);
			Node parentOfy = parentRec(root, y.val, root);
			parentOfx = parentOfy;
		}
		Node parentOfy = parentRec(root, y.val, root);
		if(parentRec(root, y.val, root) == null) {
			root = x;
		}else if(parentOfy.left == y)
			parentOfy.left =x;
		else
			parentOfy.right = x;
		
		if(y != searchNode) {
			searchNode.val =y.val;
		}
		return y;
	}

	public static void main(String args[]) {
		BSTimp bst1 = new BSTimp();
		bst1.insert(15);
		bst1.insert(5);
		bst1.insert(16);
		bst1.insert(3);
		bst1.insert(12);
		bst1.insert(20);
		bst1.insert(10);
		bst1.insert(13);
		bst1.insert(18);
		bst1.insert(23);
		bst1.insert(6);
		bst1.insert(7);
		bst1.delete(5);
		bst1.print();
		bst1.treeMin();
		bst1.successor(12);
	}
}
