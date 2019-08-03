package rs.jugomedia.internetadventure;

import rs.jugomedia.internetadventure.jpapi.PluginManager;

public class StarterClass {
	PluginManager pm;
	UserInterface browser;
	public static void main(String args[])
	{
		StarterClass starter = new StarterClass();
		starter.startUI();
	}
	public void startUI()
	{
		pm = new PluginManager(this);
		browser = new UserInterface(this);
		browser.setVisible(true);
	}
}
