package dbs.project.main.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;

import dbs.project.dao.TournamentDao;
import dbs.project.entity.Stadium;
import dbs.project.entity.Tournament;
import dbs.project.entity.TournamentGroup;
import dbs.project.generator.GroupStageGenerator;
import dbs.project.generator.TournamentGenerator;
import dbs.project.service.GroupService;
import dbs.project.service.KnockoutMatchService;
import dbs.project.service.TournamentService;
import dbs.project.service.group.StandingRow;

public class AppGui extends JFrame {

	private static final long serialVersionUID = 1L;

	private final String APP_NAME = "Weltmeisterschaft DB";

	private static final String FUSSBALL_JPG = "/images/fussball.jpg";

	private JList tournamentsList;
	private JPanel mainPanel, groupStageComponents, statistic;
	private JTree knockoutTree;

	public AppGui() {
		this.setName(this.APP_NAME);
		this.setTitle(this.APP_NAME);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 768);

		initComponents();
	}

	public static void main(String[] args) {
		AppGui gui = new AppGui();
		gui.setVisible(true);
	}

	private void initComponents() {

		this.mainPanel = new javax.swing.JPanel();
		this.mainPanel.setName("mainPanel");
		this.mainPanel
				.setLayout(new BoxLayout(this.mainPanel, BoxLayout.X_AXIS));
		this.mainPanel.setBorder(BorderFactory
				.createEmptyBorder(10, 10, 10, 10));

		initLeftComponents();
		initTab();

		this.add(this.mainPanel);
	}

	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = this.getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	private void initTab() {
		JTabbedPane tabbComponents = new JTabbedPane();

		// Vorrunde
		this.groupStageComponents = new JPanel();
		this.groupStageComponents.setLayout(new BoxLayout(
				this.groupStageComponents, BoxLayout.Y_AXIS));
		tabbComponents.add("Vorrunde", new JScrollPane(
				this.groupStageComponents));

		// Finalrunde
		this.knockoutTree = new JTree();
		tabbComponents.add("Finalrunde", this.knockoutTree);
		this.knockoutTree.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,
				10));
		ImageIcon icon = createImageIcon(FUSSBALL_JPG, "Fussballspiel");
		if (icon != null) {
			DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
			renderer.setLeafIcon(icon);
			renderer.setOpenIcon(icon);
			renderer.setClosedIcon(icon);
			this.knockoutTree.setCellRenderer(renderer);
		}

		// Statistik
		this.statistic = new JPanel();
		this.statistic
				.setLayout(new BoxLayout(this.statistic, BoxLayout.Y_AXIS));
		this.statistic.setBorder(BorderFactory
				.createEmptyBorder(10, 10, 10, 10));
		this.statistic.setAlignmentX(0F);
		JScrollPane bla = new JScrollPane(this.statistic);
		bla.setAlignmentX(0F);
		tabbComponents.add("Statisik", bla);

		if (this.tournamentsList.getModel().getSize() > 0) {
			Tournament firstTournament = (Tournament) this.tournamentsList
					.getModel().getElementAt(0);
			refreshTabs(firstTournament);
		}

		this.mainPanel.add(tabbComponents);
	}

	private void refreshTabs(final Tournament tournament) {
		List<TournamentGroup> groups = null;
		// Catch wrong data
		try {
			groups = tournament.getGroupStage().getGroups();
			// Updates tables
			refreshTables(groups);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		// Updates tree
		refreshTree(tournament);

		// Updates statistic
		refreshStatistic(tournament);

		this.mainPanel.validate();
		this.mainPanel.repaint();
	}

	private void refreshStatistic(Tournament tournament) {
		this.statistic.removeAll();

		addLine(this.statistic, "Torschützenkönig",
				TournamentService.getTopscorers(tournament));

		addLine(this.statistic, "Spieler mit den meisten Karten",
				TournamentService.getPlayerWithMostCards(tournament));

		JLabel stadiumLabel = new JLabel("Stadien");
		stadiumLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		stadiumLabel.setAlignmentY(0F);

		JPanel stadiumList = new JPanel();
		stadiumList.setLayout(new BoxLayout(stadiumList, BoxLayout.Y_AXIS));
		stadiumList.setAlignmentY(0F);
		for (Stadium stadium : tournament.getStadiums()) {
			JTextField tmpText = new JTextField(stadium.getCity());
			tmpText.setMaximumSize(new DimensionUIResource(200, 20));
			tmpText.setEditable(false);

			stadiumList.add(tmpText);
		}

		JPanel stadiums = new JPanel();
		stadiums.setLayout(new BoxLayout(stadiums, BoxLayout.X_AXIS));
		stadiums.add(stadiumLabel);
		stadiums.add(stadiumList);
		stadiums.setAlignmentX(0F);
		this.statistic.add(stadiums);

	}

	private void addLine(JPanel component, String label, String text) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setAlignmentY(0F);
		panel.setAlignmentX(0F);

		JLabel tmpLabel = new JLabel(label);
		tmpLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		tmpLabel.setAlignmentX(0F);

		JTextField tmpText = new JTextField(text);
		tmpText.setMaximumSize(new DimensionUIResource(400, 20));
		tmpText.setEditable(false);
		tmpText.setAlignmentX(1F);

		panel.add(tmpLabel);
		panel.add(tmpText);
		component.add(panel);
	}

	private void refreshTables(List<TournamentGroup> groups) {
		this.groupStageComponents.removeAll();
		for (TournamentGroup group : groups) {
			JLabel groupLabel = new JLabel(group.getName());
			groupLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

			JPanel tablePanel = new JPanel();
			JTable tmpJTable = new JTable();
			tmpJTable.setModel(StandingRow.getModel(group));
			tablePanel.setLayout(new BorderLayout());
			tablePanel.add(tmpJTable.getTableHeader(), BorderLayout.PAGE_START);
			tablePanel.add(tmpJTable, BorderLayout.CENTER);

			JPanel schedulePanel = new JPanel();
			JLabel scheduleLabel = new JLabel("Spielplan");
			JTextArea tmpSchedule = new JTextArea();
			tmpSchedule.setText(GroupService.getSchedule(group));
			tmpSchedule.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,
					10));
			tmpSchedule.setEditable(false);
			tmpSchedule.setOpaque(false);
			schedulePanel.setLayout(new BoxLayout(schedulePanel,
					BoxLayout.X_AXIS));
			schedulePanel.add(scheduleLabel);
			schedulePanel.add(tmpSchedule);

			JPanel tmpPanel = new JPanel();
			tmpPanel.setLayout(new BoxLayout(tmpPanel, BoxLayout.Y_AXIS));
			tmpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			tmpPanel.add(groupLabel);
			tmpPanel.add(tablePanel);
			tmpPanel.add(schedulePanel);

			this.groupStageComponents.add(new JScrollPane(tmpPanel));
		}
	}

	private void refreshTree(Tournament tournament) {
		TreeModel treeModel = KnockoutMatchService.getAsTreeModel(tournament
				.getFinalMatch());
		this.knockoutTree.setModel(treeModel);
	}

	private void initLeftComponents() {
		JPanel components = new JPanel();
		BoxLayout bl = new BoxLayout(components, BoxLayout.Y_AXIS);
		components.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		components.setLayout(bl);
		components.setMaximumSize(new Dimension(200, 768));

		JLabel label = new JLabel("Verfügbare Turniere:");
		label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		components.add(label);

		JScrollPane tournamentsScrollPane = new JScrollPane();
		this.tournamentsList = new JList();
		this.tournamentsList
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tournamentsScrollPane.setViewportView(this.tournamentsList);
		refreshList();

		ListSelectionListener listListener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int i = ((JList) e.getSource()).getSelectedIndex();
				if (i != -1) {
					Tournament selectedTournament = (Tournament) AppGui.this.tournamentsList
							.getModel().getElementAt(i);
					refreshTabs(selectedTournament);
				}
			}
		};
		this.tournamentsList.addListSelectionListener(listListener);

		// Button: create new tournament
		JButton createTournamentJava = new JButton();
		createTournamentJava.setText("Turnier generieren (java generator)");
		ActionListener javaButtonPressed = new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					TournamentGenerator.generateTournament();
				} catch (Exception e) {
					e.printStackTrace();
				}

				refreshList();
			}
		};
		createTournamentJava.addActionListener(javaButtonPressed);

		// Button: create new tournament
		JButton createTournamentStoredProcedure = new JButton();
		createTournamentStoredProcedure.setText("Turnier erstellen\n(stored procedure)");
		ActionListener procedureButtonPressed = new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					TournamentDao.generateTournament(TournamentGenerator.randomYear(), "Weltmeisterschaft");
				} catch (Exception e) {
					e.printStackTrace();
				}

				refreshList();
			}
		};
		createTournamentStoredProcedure.addActionListener(procedureButtonPressed);
		
		// Button: simulate group stage
		JButton generateResults = new JButton();
		generateResults.setText("Gruppenphase simulieren (zeitintensiv)");
		ActionListener resultButtonPressed = new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					int i = AppGui.this.tournamentsList.getSelectedIndex();
					if (i != -1) { // there's a tournament selected
						Tournament selectedTournament = (Tournament) AppGui.this.tournamentsList
								.getModel().getElementAt(i);
						GroupStageGenerator.enterResults(selectedTournament
								.getGroupStage());
						refreshTabs(selectedTournament);
					} else
						System.out.println("Please select a tournament first.");
				} catch (Exception e) {
					e.printStackTrace();
				}

				refreshList();
			}
		};
		generateResults.addActionListener(resultButtonPressed);

		// Button: refresh
		JButton refreshData = new JButton();
		refreshData.setText("Daten aktualisieren");
		ActionListener refreshDataButtonPressed = new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					int i = AppGui.this.tournamentsList.getSelectedIndex();
					if (i != -1) { // there's a tournament selected
						Tournament selectedTournament = (Tournament) AppGui.this.tournamentsList
								.getModel().getElementAt(i);
						refreshTabs(selectedTournament);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				refreshList();
			}
		};
		refreshData.addActionListener(refreshDataButtonPressed);

		// Liste hinzufügen
		components.add(tournamentsScrollPane);
		// Button hinzufügen
		components.add(createTournamentStoredProcedure);
		components.add(createTournamentJava);
		components.add(generateResults);
		components.add(refreshData);

		this.mainPanel.add(components);
	}

	private void refreshList() {
		tournamentsList.setModel(TournamentService.getListModel());
	}
}
