package rs.jugomedia.internetadventure;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Color;

import javax.swing.JButton;

import rs.jugomedia.internetadventure.customcontrols.TabControl;
import javax.swing.JMenu;

public class UserInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	StarterClass starter;


	public UserInterface(StarterClass starter) {
		this.starter = starter;
		setTitle("Internet Adventure");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 866, 711);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblProgress = new JLabel("Done");
		panel.add(lblProgress, BorderLayout.WEST);
		
		JProgressBar progressBar = new JProgressBar();
		panel.add(progressBar, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		TabControl panel_3 = new TabControl(this);
		panel_3.setPreferredSize(new Dimension(-1, 30));
		panel_1.add(panel_3, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		panel_4.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.WEST);
		
		JButton button = new JButton(">");
		panel_4.add(button, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel_2, BorderLayout.CENTER);
	}

}
