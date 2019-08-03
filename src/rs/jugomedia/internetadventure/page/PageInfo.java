package rs.jugomedia.internetadventure.page;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PageInfo
{
	public PageInfo(String title) {
		pageName = title;
	}
	public PageInfo() {
		// TODO Auto-generated constructor stub
	}
	public ImageIcon pageIcon = null;
	public String pageName = null;
	public String pageUrl = null;
	public String pageHTML = "";
	public JPanel viewPort = new JPanel();
}