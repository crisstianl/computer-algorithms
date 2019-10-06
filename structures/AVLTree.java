package structures;

/**
 * AVL tree is a self-balancing binary search tree (BST) where the difference
 * between heights of left and right subtrees cannot be more than 1 for all
 * nodes.
 */
public class AVLTree<E extends Comparable<E>> {

	private Node<E> root;

	private static class Node<E> {
		int height;
		final E value;
		Node<E> left, right;

		Node(E value) {
			this.height = 1;
			this.value = value;
			this.left = this.right = null;
		}

		int hLeft() {
			return left != null ? left.height : 0;
		}

		int hRight() {
			return right != null ? right.height : 0;
		}
	}

	public AVLTree<E> add(E newValue) {
		this.root = add(this.root, newValue);
		return this;
	}

	/**
	 * Traverse the tree using the property left < parent < right and add the enw
	 * value
	 */
	static <E extends Comparable<E>> Node<E> add(Node<E> node, E newValue) {
		// 1. Perform the normal BST insertion
		if (node == null) {
			node = new Node<E>(newValue);
			node.height = 1;
			return node;
		} else if (newValue.compareTo(node.value) < 0) {
			node.left = add(node.left, newValue);
		} else if (newValue.compareTo(node.value) > 0) {
			node.right = add(node.right, newValue);
		} else { // Duplicate keys not allowed
			return node;
		}

		// 2. Update height of this ancestor node
		node.height = 1 + Math.max(node.hLeft(), node.hRight());

		// 3. Get the balance factor of this ancestor node to check whether this node
		// became unbalanced
		final int balance = node.hLeft() - node.hRight();
		// final E vLeft = node.left != null ? node.left.value

		// If this node becomes unbalanced, then there are 4 cases Left Left Case
		if (balance > 1 && newValue.compareTo(node.left.value) < 0) {
			return rightRotate(node);
		}

		// Right Right Case
		if (balance < -1 && newValue.compareTo(node.right.value) > 0) {
			return leftRotate(node);
		}

		// Left Right Case
		if (balance > 1 && newValue.compareTo(node.left.value) > 0) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// Right Left Case
		if (balance < -1 && newValue.compareTo(node.right.value) < 0) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		// return the (unchanged) node pointer
		return node;
	}

	/**
	 * A utility function to right rotate subtree rooted with y
	 */
	static <E extends Comparable<E>> Node<E> rightRotate(Node<E> y) {
		// Perform rotation
		Node<E> x = y.left;
		Node<E> T2 = x.right;
		x.right = y;
		y.left = T2;

		// Update heights
		y.height = Math.max(y.hLeft(), y.hRight()) + 1;
		x.height = Math.max(x.hLeft(), x.hRight()) + 1;

		// Return new root
		return x;
	}

	/**
	 * A utility function to left rotate subtree rooted with x
	 */
	static <E extends Comparable<E>> Node<E> leftRotate(Node<E> x) {
		// Perform rotation
		Node<E> y = x.right;
		Node<E> T2 = y.left;
		y.left = x;
		x.right = T2;

		// Update heights
		x.height = Math.max(x.hLeft(), x.hRight()) + 1;
		y.height = Math.max(y.hLeft(), y.hRight()) + 1;

		// Return new root
		return y;
	}

	/**
	 * Traverse the tree using the property left < parent < right and return true if
	 * the value is found
	 */
	static <E extends Comparable<E>> boolean search(Node<E> node, E value) {
		if (node == null) { // value not found
			return false;
		} else if (value.equals(node.value)) { // value matches the current node
			return true;
		} else if (value.compareTo(node.value) < 0) { // value less than current node hence search the left subtree
			return search(node.left, value);
		} else { // value bigger than current node hence search the right subtree
			return search(node.right, value);
		}
	}

	/**
	 * Compute the "height" of a tree -- the number of nodes along the longest path
	 * from the root node down to the farthest leaf node.
	 */
	static <E extends Comparable<E>> int height(Node<E> node) {
		if (node == null) {
			return 0;
		} else {
			/* compute height of each subtree and return the biggest */
			return Math.max(height(node.left), height(node.right)) + 1;
		}
	}

	/**
	 * Breadth first traversal, the three is traversed level by level from left to
	 * right
	 */
	static <E extends Comparable<E>> void bfs(Node<E> node) {
		if (node != null) {
			java.util.Queue<Node<E>> q = new java.util.LinkedList<Node<E>>();
			q.add(node);

			// Do level order traversal until we find an empty place.
			while (!q.isEmpty()) {
				Node<E> temp = q.poll();
				System.out.print(temp.value + " ");

				// enqueue left child
				if (temp.left != null) {
					q.add(temp.left);
				}

				// enqueue right child
				if (temp.right != null) {
					q.add(temp.right);
				}
			}
		}
	}

	/**
	 * Depth first traversal, the tree is traversed in depth first in preorder
	 * (root-left-right)
	 */
	static <E extends Comparable<E>> void dfs_preorder(Node<E> node) {
		if (node != null) {
			System.out.print(node.value + " ");
			dfs_preorder(node.left);
			dfs_preorder(node.right);
		}
	}

	/**
	 * Depth first traversal, the tree is traversed in depth first in inorder
	 * (left-root-right)
	 */
	static <E extends Comparable<E>> void dfs_inorder(Node<E> node) {
		if (node != null) {
			dfs_inorder(node.left);
			System.out.print(node.value + " ");
			dfs_inorder(node.right);
		}
	}

	/**
	 * Depth first traversal, the tree is traversed in depth first in postorder
	 * (left-right-root)
	 */
	static <E extends Comparable<E>> void dfs_postorder(Node<E> node) {
		if (node != null) {
			dfs_postorder(node.left);
			dfs_postorder(node.right);
			System.out.print(node.value + " ");
		}
	}

	public void print() {
		int h = height(this.root);
		print(java.util.Collections.singletonList(this.root), 1, h);
	}

	/**
	 * Print all nodes, starting from the root, with left and right paddings
	 */
	static <E extends Comparable<E>> void print(java.util.List<Node<E>> nodes, int level, int maxLevel) {
		// recursive stop condition, all nodes are null
		boolean allNodesNull = true;
		for (Object object : nodes) {
			if (object != null) {
				allNodesNull = false;
				break;
			}
		}
		if (allNodesNull) {
			return;
		}

		int floor = maxLevel - level;
		int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
		int firstSpaces = (int) Math.pow(2, (floor)) - 1;
		int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

		// print white spaces
		for (int k = 0; k < firstSpaces; k++) {
			System.out.print(" ");
		}

		java.util.List<Node<E>> newNodes = new java.util.ArrayList<Node<E>>();
		for (Node<E> node : nodes) {
			if (node != null) {
				System.out.print(node.value);
				newNodes.add(node.left);
				newNodes.add(node.right);
			} else {
				newNodes.add(null);
				newNodes.add(null);
				System.out.print(" ");
			}

			// print white spaces
			for (int k = 0; k < betweenSpaces; k++) {
				System.out.print(" ");
			}
		}
		System.out.print("\n");

		for (int i = 1; i <= endgeLines; i++) {
			for (int j = 0; j < nodes.size(); j++) {
				// print white spaces
				for (int k = 0; k < (firstSpaces - i); k++) {
					System.out.print(" ");
				}

				if (nodes.get(j) == null) {
					// print white spaces
					for (int k = 0; k < (endgeLines + endgeLines + i + 1); k++) {
						System.out.print(" ");
					}
					continue;
				}

				if (nodes.get(j).left != null) {
					System.out.print("/");
				} else {
					System.out.print(" ");
				}
				// print white spaces
				for (int k = 0; k < (i + i - 1); k++) {
					System.out.print(" ");
				}

				if (nodes.get(j).right != null) {
					System.out.print("\\");
				} else {
					System.out.print(" ");
				}
				// print white spaces
				for (int k = 0; k < (endgeLines + endgeLines - i); k++) {
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}

		print(newNodes, level + 1, maxLevel);
	}

	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		tree.add(1).add(2).add(3).add(4).add(5).add(6).add(7).add(8).add(9).add(10);
		tree.print();

		System.out.println("Search 7: " + search(tree.root, 7));
		System.out.println("Search 17: " + search(tree.root, 17));

		System.out.print("BFS order: ");
		bfs(tree.root);

		System.out.print("\nDFS preorder: ");
		dfs_preorder(tree.root);

		System.out.print("\nDFS postorder: ");
		dfs_postorder(tree.root);

		System.out.print("\nDFS inorder: ");
		dfs_inorder(tree.root);
	}

}
