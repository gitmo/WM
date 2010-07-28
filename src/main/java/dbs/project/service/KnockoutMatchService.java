package dbs.project.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import dbs.project.entity.KnockoutMatch;

public class KnockoutMatchService {

	/**
	 * JTree requires a TreeModel for DataRepresentation getAsTreeModel returns
	 * the KnockoutStage in a TreeModel
	 * 
	 * @param knockoutStage
	 * @return
	 */
	public static TreeModel getAsTreeModel(KnockoutMatch rootMatch) {
		class KnockoutTree implements TreeModel {
			KnockoutMatch root;

			KnockoutTree(KnockoutMatch finale) {
				this.root = finale;
			}

			public Object getChild(Object parent, int index) {
				return ((KnockoutMatch) parent).getChilds().get(index);
			}

			public int getChildCount(Object parent) {
				return ((KnockoutMatch) parent).getChilds().size();
			}

			public int getIndexOfChild(Object parent, Object child) {
				int i = 0;
				for (KnockoutMatch tmpChild : ((KnockoutMatch) parent)
						.getChilds())
					if (tmpChild == (KnockoutMatch) child)
						return i;
					else
						i++;

				return -1;
			}

			public Object getRoot() {
				return this.root;
			}

			public boolean isLeaf(Object node) {
				return (getChildCount(node) == 0) ? true : false;
			}

			public void addTreeModelListener(TreeModelListener l) {
			}

			public void removeTreeModelListener(TreeModelListener l) {
			}

			public void valueForPathChanged(TreePath path, Object newValue) {
			}

		}

		return new KnockoutTree(rootMatch);

	}

	public static List<KnockoutMatch> getAllMatches(KnockoutMatch root) {
		List<KnockoutMatch> matches = new LinkedList<KnockoutMatch>();

		// BFS iteration
		Stack<KnockoutMatch> stack = new Stack<KnockoutMatch>();
		stack.add(root);
		KnockoutMatch tmpNode;
		while (stack.size() > 0) {
			tmpNode = stack.pop();
			if (tmpNode.getChilds().size() == 0)
				matches.add(tmpNode);
			else
				stack.addAll(tmpNode.getChilds());
		}

		return matches;
	}

}
