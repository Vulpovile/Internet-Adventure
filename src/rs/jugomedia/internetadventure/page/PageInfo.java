package rs.jugomedia.internetadventure.page;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import rs.jugomedia.internetadventure.customcontrols.TabControl;

public class PageInfo
{
	public PageInfo(String title, TabControl tc) {
		this(tc);
		setPageName(title);
	}
	public PageInfo(TabControl tc) {
		this.tabControl = tc;
		getViewPort().setBackground(Color.WHITE);
	}
	public void setPersistance(boolean isPersistant)
	{
		this.isPersistant = isPersistant;
	}
	public boolean isPersistant()
	{
		return this.isPersistant;
	}
	private ImageIcon pageIcon = null;
	private String pageName = null;
	private String pageURL = null;
	private String pageHTML = "";
	private boolean isPersistant = false;
	private String progText = "Done";
	private JPanel viewPort = new JPanel();
	private int maxProg = 0;
	private int prog = 0;
	private TabControl tabControl;
	public void setMaxProgress(int max) {
		maxProg = max;
		if(tabControl.isCurrentPage(this))
		{
			tabControl.getMainInterface().getProgressBar().setMaximum(maxProg);
		}
	}
	public int getMaxProgress() {
		return maxProg;
	}
	public void setProgress(int progress) {
		prog = progress;
		if(tabControl.isCurrentPage(this))
		{
			tabControl.getMainInterface().getProgressBar().setMaximum(maxProg);
			tabControl.getMainInterface().getProgressBar().setValue(progress);
		}
	}
	public int getProgress() {
		return prog;
	}
	public void setProgressText(String string) {
		progText = string;
		if(tabControl.isCurrentPage(this))
		{
			tabControl.getMainInterface().getProgressText().setText(progText);
		}
	}
	public JPanel getViewPort() {
		return viewPort;
	}
	public void setViewPort(JPanel viewPort) {
		this.viewPort = viewPort;
	}
	public ImageIcon getPageIcon() {
		return pageIcon;
	}
	public void setPageIcon(ImageIcon pageIcon) {
		this.pageIcon = pageIcon;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getPageURL() {
		return pageURL;
	}
	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}
	public String getPageHTML() {
		return pageHTML;
	}
	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}
}