package structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Binary search tree is a special binary tree in which each parent node has at
 * max 2 child nodes and all nodes from the left of the parent are smaller and
 * all nodes from the right are bigger. The tree is unbalanced (maxHeight -
 * minHeight <= 1)
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

	/**
	 * Traverse the tree using the property left < parent < right and add the enw
	 * value
	 */
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
	 * Breadth first traversal, the three is traversed from left to right level by
	 * level
	 */
	static <E extends Comparable<E>> void bfs(Node<E> node) {
		if (node != null) {
			java.util.Queue<Node<E>> q = new java.util.LinkedList<Node<E>>();
			q.add(node);

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
		print(Collections.singletonList(this.root), 1, h);
	}

	/**
	 * Print all nodes at the given level
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

	/**
	 * Print all nodes, starting from the root, with left and right paddings
	 */
	static <E extends Comparable<E>> void print(List<Node<E>> nodes, int level, int maxLevel) {
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

		List<Node<E>> newNodes = new ArrayList<Node<E>>();
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
		// because the tree is not balanced the order of insertion is important
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.add(6).add(8).add(4).add(7).add(5).add(2).add(9).add(1).add(10).add(3);
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
