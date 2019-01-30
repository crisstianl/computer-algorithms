package structures;

/**
 * Binary search tree is a special binary tree in which each parent node has
 * either at max 2 child nodes and all nodes from the left of the parent are
 * smaller and all node from the right are bigger. The tree is unbalanced
 * (maxHeight - minHeight <= 1)
 */
public class BinarySearchTree<E extends Comparable<E>> {

	private Node<E> root;

	private static class Node<E> {
		final E value;
		Node<E> left, right;

		Node(E value) {
			this.value = value;
			this.left = this.right = null;
		}
	}

	public BinarySearchTree<E> add(E newValue) {
		if (this.root == null) {
			this.root = new Node<E>(newValue);
		} else {
			add(this.root, newValue);
		}
		return this;
	}

	static <E extends Comparable<E>> void add(Node<E> node, E newValue) {
		if (newValue.compareTo(node.value) < 0) {
			if (node.left == null) {
				node.left = new Node<E>(newValue);
			} else {
				add(node.left, newValue);
			}
		} else {
			if (node.right == null) {
				node.right = new Node<E>(newValue);
			} else {
				add(node.right, newValue);
			}
		}
	}

	public E search(E value) {
		return search(this.root, value);
	}

	static <E extends Comparable<E>> E search(Node<E> node, E value) {
		if (node == null) { // value not found
			return null;
		} else if (value.equals(node.value)) { // value matches the current node
			return node.value;
		} else if (value.compareTo(node.value) < 0) { // value less than current node hence search the left subtree
			return search(node.left, value);
		} else { // value bigger than current node hence search the right subtree
			return search(node.right, value);
		}
	}

	public int height() {
		return height(this.root);
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
	 * Breadth first traversal, the three is traversed from left to right on each
	 * level
	 */
	static <E extends Comparable<E>> void bfs(Node<E> node, int height) {
		for (int i = 1; i <= height; i++) {
			print(node, i);
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
		for (int i = 1; i <= h; i++) {
			// print indent spaces
			for (int j = 1; j <= h - i; j++) {
				System.out.print(" ");
			}
			print(this.root, i);
			System.out.print("\n");
		}
	}

	/**
	 * Print nodes at the given level
	 */
	static <E extends Comparable<E>> void print(Node<E> node, int level) {
		if (node == null) {
			return;
		} else if (level == 1) {
			System.out.print(node.value + " ");
		} else if (level > 1) {
			print(node.left, level - 1);
			print(node.right, level - 1);
		}
	}

	public static void main(String[] args) {
		// since the tree is not balanced the order of insertion is important
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.add(6).add(8).add(4).add(7).add(5).add(2).add(9).add(1).add(10).add(3);
		tree.print();

		System.out.print("BFS: ");
		bfs(tree.root, tree.height());

		System.out.print("\nDFS preorder: ");
		dfs_preorder(tree.root);

		System.out.print("\nDFS postorder: ");
		dfs_postorder(tree.root);

		System.out.print("\nDFS inorder: ");
		dfs_inorder(tree.root);
	}

}
