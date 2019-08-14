package rs.jugomedia.internetadventure;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;

import rs.jugomedia.internetadventure.customcontrols.TabControl;
import rs.jugomedia.internetadventure.jpapi.event.UIStateEvent;

import javax.swing.JMenu;

public class UserInterface extends JFrame implements WindowListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFieldUrl;
	private StarterClass starter;
	private TabControl tabControl = new TabControl(this);
	private JScrollPane scrlViewPort = new JScrollPane();
	private JProgressBar progressBar = new JProgressBar();
	private JLabel lblProgress = new JLabel("Done");
	public TabControl getTabControl()
	{
		return tabControl;
	}

	UserInterface(StarterClass starter) {
		addWindowListener(this);
		this.starter = starter;
		setTitle("Internet Adventure");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		
		
		panel.add(lblProgress, BorderLayout.WEST);
		
		panel.add(progressBar, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		
		tabControl.setPreferredSize(new Dimension(-1, 30));
		panel_1.add(tabControl, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		txtFieldUrl = new JTextField();
		panel_4.add(txtFieldUrl, BorderLayout.CENTER);
		txtFieldUrl.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.WEST);
		
		JButton btnGo = new JButton(">");
		panel_4.add(btnGo, BorderLayout.EAST);
		
		
		scrlViewPort.setBackground(Color.WHITE);
		scrlViewPort.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(scrlViewPort, BorderLayout.CENTER);
	}

	@Override
	public void setVisible(boolean visible)
	{
		if(visible)
		{
			if(this.starter.pm.processEvent(new UIStateEvent(UIStateEvent.State.UI_SHOW)))
				super.setVisible(visible);
		}
		else
		{
			if(this.starter.pm.processEvent(new UIStateEvent(UIStateEvent.State.UI_HIDE)))
				super.setVisible(visible);
		}
			
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}


	@Override
	public void windowClosing(WindowEvent e) {
		if(this.starter.pm.processEvent(new UIStateEvent(UIStateEvent.State.UI_CLOSE)))
			super.dispose();
	}


	@Override
	public void windowClosed(WindowEvent e) {
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public JTextField getURLBar() {
		return txtFieldUrl;
	}

	public JScrollPane getScrollPane() {
		return scrlViewPort;
	}

	public JProgressBar getProgressBar() {
		return this.progressBar;
	}

	public JLabel getProgressText() {
		// TODO Auto-generated method stub
		return lblProgress;
	}

}
