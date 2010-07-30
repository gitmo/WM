package dbs.project.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import dbs.project.dao.KnockoutMatchDao;
import dbs.project.entity.GroupStage;
import dbs.project.entity.KnockoutMatch;
import dbs.project.entity.Team;
import dbs.project.entity.Tournament;
import dbs.project.entity.TournamentGroup;
import dbs.project.exception.TiedMatch;

public class KnockoutMatchService {

	private static int nodeCounter = 1;
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
				return ((KnockoutMatch) parent).getChildren().get(index);
			}

			public int getChildCount(Object parent) {
				return ((KnockoutMatch) parent).getChildren().size();
			}

			public int getIndexOfChild(Object parent, Object child) {
				int i = 0;
				for (KnockoutMatch tmpChild : ((KnockoutMatch) parent)
						.getChildren())
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

	/**
	 * get all matches of the knockout stage
	 * 
	 * @param root
	 * @return
	 */
	public static List<KnockoutMatch> getAllMatches(KnockoutMatch root) {
		List<KnockoutMatch> matches = new LinkedList<KnockoutMatch>();
		
		if(root == null)
			return matches;
		
		// BFS iteration
		Stack<KnockoutMatch> stack = new Stack<KnockoutMatch>();
		stack.add(root);
		KnockoutMatch tmpNode;
		while (stack.size() > 0) {
			tmpNode = stack.pop();
			if (!(tmpNode.getChildren().size() == 0))
				stack.addAll(tmpNode.getChildren());
			matches.add(tmpNode);
		}

		return matches;
	}

	/**
	 * Updates the knockout tree if some games are already played
	 * 
	 * @param group
	 */
	public static void updatesKnockoutTree(TournamentGroup group) {
		Tournament tournament = TournamentService.getByGroup(group);
		nodeCounter = 0;
		recUpdatesKnockoutTree(tournament.getFinalMatch(), tournament.getGroupStage(),0);
	}

	private static void recUpdatesKnockoutTree(KnockoutMatch node,
			GroupStage groupStage, int height) {
		// Achtelfinale
		if (node.getChildren().size() < 1) {
			int i = nodeCounter++;
			Team hostTeam, guestTeam;
			if (i % 2 == 0) {
				hostTeam = GroupService.getFirst(groupStage.getGroups().get(i));
				guestTeam = GroupService.getSecond(groupStage.getGroups().get(
						(i + 1)));
			} else {
				hostTeam = GroupService.getFirst(groupStage.getGroups().get(i));
				guestTeam = GroupService.getSecond(groupStage.getGroups().get(
						i - 1));
			}

			node.setHostTeam(hostTeam);
			node.setGuestTeam(guestTeam);
			KnockoutMatchDao.save(node);
			return;
		}

		KnockoutMatch hostChild = node.getChildren().get(0);
		KnockoutMatch guestChild = node.getChildren().get(1);

		if (hostChild.isPlayed() && guestChild.isPlayed()) {
			Team hostTeam = null, guestTeam = null;
			try {
				hostTeam = MatchService.getWinner(hostChild);
				guestTeam = MatchService.getWinner(guestChild);
			} catch (TiedMatch e) {
			}

			node.setHostTeam(hostTeam);
			node.setHostTeam(guestTeam);
		}

		height++;
		recUpdatesKnockoutTree(hostChild, groupStage,height);
		recUpdatesKnockoutTree(guestChild, groupStage,height);
	}

}
