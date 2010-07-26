package dbs.project.main.gui;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dbs.project.entity.Tournament;
import dbs.project.entity.TournamentGroup;
import dbs.project.service.GroupStageService;
import dbs.project.service.TournamentService;
import dbs.project.service.group.StandingRow;

/**
 * The application's main frame.
 */
public class AppGui extends JFrame {
	private static final long serialVersionUID = 1L;

	private final String APP_NAME = "Weltmeisterschaft DB";
	
    private JPanel mainPanel;
    private JList tournamentsList;
    private javax.swing.JPanel groupStageComponents;

    public AppGui() {
    	this.setName(APP_NAME);
    	this.setTitle(APP_NAME);
    	this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
    	this.setSize(1024, 768);
    	
    	initComponents();
    }
    
    public static void main(String[] args) {
    	AppGui gui = new AppGui();
    	gui.setVisible(true);
    }

    private void initComponents() {

    	mainPanel = new javax.swing.JPanel();
        mainPanel.setName("mainPanel");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        
        initLeftComponents();
        initTab();
        
        this.add(mainPanel);
    }

    private void initTab() {
    	JTabbedPane tabbComponents = new JTabbedPane();
    	groupStageComponents = new JPanel();
		groupStageComponents.setLayout(new BoxLayout(groupStageComponents, BoxLayout.Y_AXIS));

    	Tournament firstTournament = (Tournament) tournamentsList.getModel().getElementAt(0);
        List<TournamentGroup> groups = firstTournament.getGroupPhase().getGroups();
        
        refreshTables(groups);
        
        tabbComponents.add("Vorrunde", groupStageComponents);

    	JPanel knockoutComponents = new JPanel();
        tabbComponents.add("Finalrunde", knockoutComponents);
        
        mainPanel.add(tabbComponents);
	}

	private void refreshTables(List<TournamentGroup> groups) {
		groupStageComponents.removeAll();
		
        for(TournamentGroup group : groups) {
        	JTable tmpJTable = new JTable();
        	JScrollPane tmpJScrollPane = new JScrollPane();
            
        	tmpJTable.setModel(StandingRow.getModel(group));
        	tmpJScrollPane.setViewportView(tmpJTable);
        	
            groupStageComponents.add(tmpJScrollPane);
        }
        
        groupStageComponents.validate();
	}

	private void initLeftComponents() {
		JPanel components = new JPanel();
		components.setLayout(new BoxLayout(components, BoxLayout.Y_AXIS));
		
		JScrollPane tournamentsScrollPane = new JScrollPane();
        tournamentsList = new JList();
        tournamentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tournamentsList.setModel(TournamentService.getListModel());
        tournamentsScrollPane.setViewportView(tournamentsList);
        
        ListSelectionListener listListener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int i = e.getLastIndex();
				System.out.println(i);
				Tournament selectedTournament = (Tournament) tournamentsList.getModel().getElementAt(i);
				refreshTables(selectedTournament.getGroupPhase().getGroups());
			}
		};
		tournamentsList.addListSelectionListener(listListener);

        JButton createTournament = new JButton();
        createTournament.setText("Turnier erstellen"); // NOI18N
        createTournament.setName("tournamentCreateButton"); // NOI18N
        
        //Liste hinzufügen
        components.add(tournamentsScrollPane);
        //Button hinzufügen
        components.add(createTournament);

        mainPanel.add(components);
	}
}
