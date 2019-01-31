package structures;

/**
 * Binary tree is a simple tree in which each parent node has either 0, 1, or 2
 * child nodes
 */
public class BinaryTree<E extends Comparable<E>> {

	private Node<E> root;

	private static class Node<E> {
		final E value;
		Node<E> left, right;

		Node(E value) {
			this.value = value;
			this.left = this.right = null;
		}
	}

	/**
	 * Traverse the tree in level order and add the new value in the first empty
	 * place
	 */
	public BinaryTree<E> add(E newValue) {
		if (this.root == null) {
			this.root = new Node<E>(newValue);
		} else {
			java.util.Queue<Node<E>> q = new java.util.LinkedList<Node<E>>();
			q.add(this.root);

			// Do level order traversal until we find an empty place.
			while (!q.isEmpty()) {
				Node<E> temp = q.poll();

				// enqueue left child
				if (temp.left != null) {
					q.add(temp.left);
				} else {
					temp.left = new Node<E>(newValue);
					break;
				}

				// enqueue right child
				if (temp.right != null) {
					q.add(temp.right);
				} else {
					temp.right = new Node<E>(newValue);
					break;
				}
			}
		}
		return this;
	}

	/**
	 * Traverse the tree in preorder and return true if the value is found
	 */
	static <E extends Comparable<E>> boolean search(Node<E> node, E value) {
		boolean retValue = false;
		if (node != null) {
			if (node.value.equals(value)) {
				retValue = true;
			} else {
				retValue = search(node.left, value) || search(node.right, value);
			}
		}
		return retValue;
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
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
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
