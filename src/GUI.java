import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.SwingConstants;

public class GUI extends JFrame {
	//panel size
	final int X = 1280;
	final int Y = 777;
	final static int width = 1280;
	final static int height = 777;

	private Generator spinGenerator;

	private JFrame frmGroupFitnessWorkout;
	private JPanel startPanel = new JPanel();

	// Spin Class variables
	private JPanel cyclingPanel = new JPanel();
	private String dtrpnTimeOfClassText;
	private JButton rdbtnMinutes45 = new JButton("45 minutes");
	private JButton rdbtnMinutes60 = new JButton("60 minutes");
	private JButton rdbtnMinutes75 = new JButton("75 minutes");
	private JLabel lblClassTime = new JLabel();
	private  int classTime = -1;
	private JButton btnMinuteGo = new JButton("Minute Go");
	private JButton btnTabata = new JButton("TABATA");
	private JButton btnCreateClass = new JButton("Generate Class");
	private String endingDrill = "";
	private JLabel lblEndingDrill = new JLabel();
	private ArrayList<Drill> spinClass = new ArrayList<Drill>();
	private JTextPane textPaneShowClass = new JTextPane();
	private String classText = "";
	private JButton btnAcceptClass = new JButton("Write Class");


	private JPanel strengthPanel = new JPanel();
	
	private JPanel salutationPanel = new JPanel();
	private final JLabel lblHaveAGreatClass = new JLabel("Have a Great Class!");
	private final JLabel lblBuckyPic = new JLabel("");

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmGroupFitnessWorkout.setVisible(true);
					window.frmGroupFitnessWorkout.setBounds(0, 0, 1, 1);
					window.frmGroupFitnessWorkout.setTitle("Group Fitness Class Generator");
					window.frmGroupFitnessWorkout.setSize(width, height);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		// set up the frame
		frmGroupFitnessWorkout = new JFrame();
		frmGroupFitnessWorkout.setTitle("Group Fitness Workout Generator");
		frmGroupFitnessWorkout.setBackground(new Color(238, 238, 238));
//		frmGroupFitnessWorkout.setBounds(250, 200, X, Y);
		frmGroupFitnessWorkout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGroupFitnessWorkout.getContentPane().setLayout(new CardLayout(0, 0));
		
		// set up title panel
		startPanel.setBackground(Color.WHITE);
		startPanel.setBounds(0, 0, X, Y);
		frmGroupFitnessWorkout.getContentPane().add(startPanel);
		startPanel.setLayout(null);
		
		JLabel lblGroupFitnessWorkout = new JLabel("Group Fitness Workout Generator");
		lblGroupFitnessWorkout.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblGroupFitnessWorkout.setBounds(X/2 - 460/2, 6, 460, 71);
		startPanel.add(lblGroupFitnessWorkout);
		
		JLabel lblSelectYourClass = new JLabel("Select Your Class:");
		lblSelectYourClass.setBounds(61, 273, 134, 16);
		startPanel.add(lblSelectYourClass);
		
		JButton btnCycling = new JButton("Cycling");
		btnCycling.setBounds(X/5 - 91/2, lblSelectYourClass.getY()+lblSelectYourClass.getHeight()+10, 91, 29);
		startPanel.add(btnCycling);
		
		JLabel RecSportsPic = new JLabel("");
		RecSportsPic.setIcon(new ImageIcon(GUI.class.getResource("/img/RecSports.png")));
		RecSportsPic.setBounds(X/2 - 358/2, 38, 358, 251);
		startPanel.add(RecSportsPic);
		
		btnCycling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cyclingClass();
			}
		});
		
		JButton btnStrength = new JButton("Strength");
		btnStrength.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				strengthClass();
			}
		});
		btnStrength.setBounds(X/5 - 91/2, lblSelectYourClass.getY()+lblSelectYourClass.getHeight()+btnCycling.getHeight()+10, 91, 29);
		startPanel.add(btnStrength);
		frmGroupFitnessWorkout.getContentPane().add(strengthPanel, "name_6375641039729");
		strengthPanel.setLayout(null);
		strengthPanel.setBackground(Color.WHITE);
		
		JLabel strengthTitleLabel = new JLabel("Strength Class Generator");
		strengthTitleLabel.setBounds(0, 0, 212, 16);
		strengthTitleLabel.setLocation(X/2-strengthTitleLabel.getWidth()/2, 0);
		strengthPanel.add(strengthTitleLabel);
		
		JLabel lblNothingHereYet = new JLabel("Nothing Here Yet!");
		lblNothingHereYet.setBounds(311, 146, 212, 46);
		strengthPanel.add(lblNothingHereYet);
		
		
		cyclingPanel.setBackground(Color.WHITE);
		cyclingPanel.setBounds(0, 0, 800, 450);
		frmGroupFitnessWorkout.getContentPane().add(cyclingPanel);
		cyclingPanel.setLayout(null);
		
		JLabel spinTitleLabel = new JLabel("Cycling Class Generator");
		spinTitleLabel.setBounds(0, 0, 212, 16);
		spinTitleLabel.setLocation(X/2-spinTitleLabel.getWidth()/2, 0);
		cyclingPanel.add(spinTitleLabel);
	
// display spinclass time
		dtrpnTimeOfClassText = "Duration of Class:";
		lblClassTime.setText(dtrpnTimeOfClassText);
		lblClassTime.setBounds(231, 85, 292, 16);
		cyclingPanel.add(lblClassTime);
		
		rdbtnMinutes45.setBounds(54, 83, 141, 23);
		cyclingPanel.add(rdbtnMinutes45);
		
		rdbtnMinutes60.setBounds(54, 83 + 20, 141, 23);
		cyclingPanel.add(rdbtnMinutes60);
		
		rdbtnMinutes75.setBounds(54, 83+40, 141, 23);
		cyclingPanel.add(rdbtnMinutes75);
		
		btnMinuteGo.setBounds(54, 172, 141, 23);
		btnMinuteGo.setVisible(false);
		cyclingPanel.add(btnMinuteGo);
		
		btnTabata.setBounds(54, 196, 141, 23);
		btnTabata.setVisible(false);
		cyclingPanel.add(btnTabata);
		
		lblEndingDrill.setText("Final Drill:");
		lblEndingDrill.setBounds(231, 174, 292, 16);
		lblEndingDrill.setVisible(false);
		cyclingPanel.add(lblEndingDrill);
				
		btnCreateClass.setBounds(54, 270, 141, 23);
		btnCreateClass.setVisible(false);
		cyclingPanel.add(btnCreateClass);
		
		textPaneShowClass.setBounds(231, 228, 525, 200);
		textPaneShowClass.setVisible(false);
		cyclingPanel.add(textPaneShowClass);
		
		btnAcceptClass.setBounds(54, 351, 141, 23);
		btnAcceptClass.setVisible(false);
		cyclingPanel.add(btnAcceptClass);
		
		frmGroupFitnessWorkout.getContentPane().add(salutationPanel, "name_61232441989078");
		salutationPanel.setLayout(null);
		salutationPanel.setBackground(Color.RED);
		lblHaveAGreatClass.setFont(new Font("Monotype Corsiva", Font.PLAIN, 28));
		lblHaveAGreatClass.setHorizontalAlignment(SwingConstants.CENTER);
		lblHaveAGreatClass.setBounds(X/2 - 252/2, 17, 252, 99);
		salutationPanel.add(lblHaveAGreatClass);
		lblBuckyPic.setIcon(new ImageIcon(GUI.class.getResource("/img/pushUpBuckey.png")));
		lblBuckyPic.setBounds(X/2 - 577/2, 100, 577, 267);
		
		salutationPanel.add(lblBuckyPic);
		
	}
	private void cyclingClass()
	{
		spinGenerator = new Generator();

		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startPanel.setVisible(true);
				cyclingPanel.setVisible(false);
			}
		});
		btnMenu.setBounds(0, 6, 78, 21);
		cyclingPanel.add(btnMenu);
		
		rdbtnMinutes45.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtrpnTimeOfClassText = "Duration of Class: 45 minutes";
				classTime = 45;
				lblClassTime.setText(dtrpnTimeOfClassText);
				btnTabata.setVisible(true);
				btnMinuteGo.setVisible(true);
				lblEndingDrill.setVisible(true);
				
				if((endingDrill.equals("TABATA") || endingDrill.equalsIgnoreCase("Minute Go")) && classTime > 0)
				{
					btnCreateClass.setVisible(true);
					textPaneShowClass.setVisible(true);
				}
			}
		});
		
		rdbtnMinutes60.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTabata.setVisible(true);
				btnMinuteGo.setVisible(true);
				lblEndingDrill.setVisible(true);
				
				dtrpnTimeOfClassText =  "Duration of Class: 60 minutes";
				classTime = 60;
				lblClassTime.setText(dtrpnTimeOfClassText);
				
				if((endingDrill.equals("TABATA") || endingDrill.equalsIgnoreCase("Minute Go")) && classTime > 0)
				{
					btnCreateClass.setVisible(true);
					textPaneShowClass.setVisible(true);
				}
			}
		});
		
		rdbtnMinutes75.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTabata.setVisible(true);
				btnMinuteGo.setVisible(true);
				lblEndingDrill.setVisible(true);
				
				dtrpnTimeOfClassText =  "Duration of Class: 75 minutes";
				classTime = 75;
				lblClassTime.setText(dtrpnTimeOfClassText);
				
				if((endingDrill.equals("TABATA") || endingDrill.equalsIgnoreCase("Minute Go")) && classTime > 0)
				{
					btnCreateClass.setVisible(true);
					textPaneShowClass.setVisible(true);
				}
			}
		});
		
		btnMinuteGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endingDrill = "Final Drill: Minute Go";
				lblEndingDrill.setText(endingDrill);
				endingDrill = "Minute Go";
				
				if((endingDrill.equals("TABATA") || endingDrill.equalsIgnoreCase("Minute Go")) && classTime > 0)
				{
					btnCreateClass.setVisible(true);
					textPaneShowClass.setVisible(true);
				}
			}
		});
		btnTabata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endingDrill = "Final Drill: TABATA";
				lblEndingDrill.setText(endingDrill);
				endingDrill = "TABATA";
				
				if((endingDrill.equals("TABATA") || endingDrill.equalsIgnoreCase("Minute Go")) && classTime > 0)
				{
					btnCreateClass.setVisible(true);
					textPaneShowClass.setVisible(true);
				}
			}
		});
	
		btnCreateClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAcceptClass.setVisible(true);
				classText = "";
				if(!spinClass.isEmpty())
					spinClass.clear();
				
				spinGenerator.setUp(classTime, endingDrill);
				spinClass = spinGenerator.createWorkout();
				for(Drill d: spinClass)
					classText = classText + d.getName()+"  {"+d.getTime()+ "}"+ "\n";
				textPaneShowClass.setText(classText);
			}
		});
		
		btnAcceptClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spinGenerator.writeWorkout();
				salutationPanel.setVisible(true);
				cyclingPanel.setVisible(false);
			}
		});
		startPanel.setVisible(false);
		cyclingPanel.setVisible(true);
				
	}
	
	public void strengthClass()
	{
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startPanel.setVisible(true);
				strengthPanel.setVisible(false);
			}
		});
		btnMenu.setBounds(0, 6, 78, 21);
		strengthPanel.add(btnMenu);
		
		
		startPanel.setVisible(false);
		strengthPanel.setVisible(true);
	}
}
