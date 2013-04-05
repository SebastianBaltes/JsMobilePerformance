package de.objectcode.perftests;

import de.objectcode.perftests.utils.ShowLogActivity;
import de.objectcode.perftests.utils.StopWatch;

/**
 * based on code from
 * http://benchmarksgame.alioth.debian.org/u32/benchmark.php?test=binarytrees&lang=v8
 * 
 * Each program should - define a tree node class and methods, a tree node
 * record and procedures, or an algebraic data type and functions, or… -
 * allocate a binary tree to 'stretch' memory, check it exists, and deallocate
 * it - allocate a long-lived binary tree which will live-on while other trees
 * are allocated and deallocated - allocate, walk, and deallocate many bottom-up
 * binary trees - allocate a tree - walk the tree nodes, checksum the node items
 * (and maybe deallocate the node) - deallocate the tree - check that the
 * long-lived binary tree still exists
 * 
 * This code is based on the javascript version, not on the java version on that
 * page, because the java version uses multithreading which is dificult to
 * compare to single threaded javascript. And - it's much simpler.
 */
public class BinaryTreesActivity extends ShowLogActivity {

	@Override
	public void perform() {
		log("a bottum-up binary tree structure of depth 13 that is modified and walked");
		final StopWatch w = new StopWatch();
		test(12);
		log(w.stop());
	}

	static class TreeNode {
		private final int item;
		private final TreeNode left, right;

		public TreeNode(final TreeNode left, final TreeNode right,
				final int item) {
			this.item = item;
			this.left = left;
			this.right = right;
		}

		public int itemCheck() {
			if (left == null) {

				return item;
			} else {
				return item + left.itemCheck() - right.itemCheck();
			}
		}

	}

	protected TreeNode bottomUpTree(final int item, final int depth) {
		if (depth > 0) {
			return new TreeNode(bottomUpTree(2 * item - 1, depth - 1),
					bottomUpTree(2 * item, depth - 1), item);
		} else {
			return new TreeNode(null, null, item);
		}
	}

	protected void test(final int n) {
		final int minDepth = 4;
		final int maxDepth = Math.max(minDepth + 2, n);
		final int stretchDepth = maxDepth + 1;

		int check = bottomUpTree(0, stretchDepth).itemCheck();

		final TreeNode longLivedTree = bottomUpTree(0, maxDepth);
		for (int depth = minDepth; depth <= maxDepth; depth += 2) {
			final int iterations = 1 << (maxDepth - depth + minDepth);
			check = 0;
			for (int i = 1; i <= iterations; i++) {
				check += bottomUpTree(i, depth).itemCheck();
				check += bottomUpTree(-i, depth).itemCheck();
			}
			log(iterations * 2 + "\t trees of depth " + depth + "\t check: "
					+ check);
		}

		log("long lived tree of depth " + maxDepth + "\t check: "
				+ longLivedTree.itemCheck());
	}

}
