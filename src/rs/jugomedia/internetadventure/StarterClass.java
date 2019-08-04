package rs.jugomedia.internetadventure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import rs.jugomedia.internetadventure.jpapi.PluginManager;

public class StarterClass {
	PluginManager pm;
	UserInterface browser;
	public static void main(String args[])
	{
		List<String> cargs = new ArrayList<String>();
		Arrays.asList(args, cargs);
		if(!cargs.contains("-nolaf"))
		{
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		StarterClass starter = new StarterClass();
		starter.startUI();
	}
	private void startUI()
	{
		
		pm = new PluginManager(this);
		browser = new UserInterface(this);
		browser.setVisible(true);
	}
	public UserInterface getBrowser() {
		// TODO Auto-generated method stub
		return browser;
	}
}
