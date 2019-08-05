package rs.jugomedia.internetadventure.page;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PageInfo
{
	public PageInfo(String title) {
		this();
		pageName = title;
	}
	public PageInfo() {
		viewPort.setBackground(Color.WHITE);
	}
	public void setPersistance(boolean isPersistant)
	{
		this.isPersistant = isPersistant;
	}
	public boolean isPersistant()
	{
		return this.isPersistant;
	}
	public ImageIcon pageIcon = null;
	public String pageName = null;
	public String pageUrl = null;
	public String pageHTML = "";
	public boolean isPersistant = false;
	public JPanel viewPort = new JPanel();
}