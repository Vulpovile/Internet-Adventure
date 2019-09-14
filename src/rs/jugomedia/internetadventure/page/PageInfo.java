package rs.jugomedia.internetadventure.page;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;

import javax.net.ssl.SSLHandshakeException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import rs.jugomedia.internetadventure.customcontrols.TabControl;
import rs.jugomedia.internetadventure.jpapi.event.NavigateEvent;
import rs.jugomedia.internetadventure.jpapi.event.RenderRequestEvent;

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
	public void callNavigate() {
		this.pageURL = tabControl.getMainInterface().getURLBar().getText();
		if(this.tabControl.getMainInterface().getPluginManager().processEvent(new NavigateEvent(this)))
		{
			URL[] url = new URL[1];
			try {
				url[0] = new URL(this.pageURL);
				this.pageHTML = PageNavigator.getSiteString(this, url);
				this.pageURL = url[0].toString();
				this.pageName = url[0].toString();
				this.pageIcon = null;
				this.viewPort.removeAll();
				if(this.tabControl.getMainInterface().getPluginManager().processEvent(new RenderRequestEvent(this)) && this.getViewPort().getComponentCount() == 0)
				{
					//Browser must now somehow render without any renderer!
					//Just puts everything onto a textarea in the viewport
					JTextPane text = new JTextPane();
					text.setEditable(false);
					text.setContentType("text/html");
					text.setText(this.pageHTML);
					getViewPort().add(text);
					getViewPort().repaint();
					this.tabControl.getMainInterface().repaint();
					setProgressText("Done.");
				}
			} catch (SSLHandshakeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}