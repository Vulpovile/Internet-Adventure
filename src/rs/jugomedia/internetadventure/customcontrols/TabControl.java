package rs.jugomedia.internetadventure.customcontrols;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.UIManager;

import rs.jugomedia.internetadventure.UserInterface;
import rs.jugomedia.internetadventure.page.PageInfo;

public class TabControl extends JPanel implements MouseListener, MouseMotionListener {
	
	/**
	 * 
	 */
	private static final int tWidth = 150;
	private static final int tHeight = 25;
	private static final int tGap = 2;
	private static final long serialVersionUID = 1L;
	private Vector<PageInfo> pages = new Vector<PageInfo>();
	private PageInfo currentPage;
	private UserInterface mainInterface;
	private int highTabIdx = -1;
	
	/**
	 * Create the panel.
	 */
	public TabControl(UserInterface mainInterface) {
		this.mainInterface = mainInterface;	
		pages.add(new PageInfo("Title"));
		pages.add(new PageInfo());
		pages.add(new PageInfo());
		currentPage = new PageInfo("Selected Page");
		pages.add(currentPage);
		pages.add(new PageInfo("Title"));
		pages.add(new PageInfo());
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	public void addPage(PageInfo page)
	{
		pages.add(page);
	}
	public void removePage(PageInfo page)
	{
		pages.remove(page);
	}
	public int getPageIndex(PageInfo page)
	{
		return pages.indexOf(page);
	}
	public PageInfo removePageByIndex(int index)
	{
		return pages.remove(index);
	}
	public PageInfo pageAtIndex(int index)
	{
		return pages.get(index);
	}
	public int getPageUnderMouse(int x, int y)
	{
		if(y > tGap && y < tGap+tHeight)
		{
			for(int i = 0; i < pages.size(); i++)
			{
				if(x > i*tWidth && x < i*tWidth+tWidth)
					return i;
			}
		}
		return -1;
	}
	public void paintComponent(Graphics g)
	{
		
		int gradProgress = 2;
		super.paintComponent(g);
		g.setColor(this.getBackground().darker().darker());
		g.drawLine(0, tHeight+tGap, this.getWidth(), tHeight+tGap);
		g.setColor(this.getBackground().brighter().brighter());
		g.drawLine(0, tHeight+tGap+1, this.getWidth(), tHeight+tGap+1);
		for(int i = pages.size()-1; i >= 0; i--)
		{
			PageInfo page = pages.get(i);
			if(currentPage == page)
				continue;
			//Draw the gradient
			g.setColor(getBackground());
			if(highTabIdx == i)
				g.setColor(getBackground().brighter().brighter());
			for(int j = 0; j < tHeight-1; j++)
			{
				if(j < tHeight / 2)
					g.setColor(new Color(g.getColor().getRed()-gradProgress,g.getColor().getGreen()-gradProgress,g.getColor().getBlue()-gradProgress));
				else if(j > tHeight+4 / 2)
					g.setColor(new Color(g.getColor().getRed()+gradProgress,g.getColor().getGreen()+gradProgress,g.getColor().getBlue()+gradProgress));
				g.drawLine(i*tWidth, j+tGap+1, i*tWidth+tWidth-1+j, j+tGap+1);
			}
			//Draw the icon and text
			if(page.pageIcon != null)
				g.drawImage(page.pageIcon.getImage(), i*tWidth+3, tGap+3, 16, 16, this);
			else
			{
				Icon ico = UIManager.getIcon("FileView.fileIcon");
				ico.paintIcon(this, g, i*tWidth+3, tGap+3);
			}
			String title = "Blank page";
			if(page.pageName != null)
				title = page.pageName;
			else if(page.pageUrl != null)
				title = page.pageUrl;
			g.setColor(Color.BLACK);
			g.drawString(title, i*tWidth+22, tGap+18);
			//Draw the lines
			g.setColor(this.getBackground().darker().darker());
			if(i == 0)
			g.drawLine(i*tWidth-1, tGap, i*tWidth-1, tGap+tHeight);
			g.drawLine(i*tWidth-1, tGap, i*tWidth+tWidth, tGap);
			g.drawLine(i*tWidth+tWidth, tGap, i*tWidth+tWidth+tHeight, tHeight+tGap);
			g.setColor(Color.WHITE);
			g.drawLine(i*tWidth, tGap+1, i*tWidth+tWidth, tGap+1);
			g.drawLine(i*tWidth+tWidth, tGap+1, i*tWidth+tWidth+tHeight-2, tHeight+tGap-1);
		}
		//Draw the current page
		if(currentPage != null)
		{
			gradProgress = 1;
			int i = getPageIndex(currentPage);
			g.setColor(getBackground());
			for(int j = tHeight; j >= 0 ; j--)
			{
				if(g.getColor().getRed() < 255-gradProgress && g.getColor().getGreen() < 255-gradProgress && g.getColor().getBlue() < 255-gradProgress)
					g.setColor(new Color(g.getColor().getRed()+gradProgress,g.getColor().getGreen()+gradProgress,g.getColor().getBlue()+gradProgress));
				g.drawLine(i*tWidth, j+tGap+1, i*tWidth+tWidth-1+j, j+tGap+1);
			}
			//Draw the icon and text
			if(currentPage.pageIcon != null)
				g.drawImage(currentPage.pageIcon.getImage(), i*tWidth+3, tGap+3, 16, 16, this);
			else
			{
				Icon ico = UIManager.getIcon("FileView.fileIcon");
				ico.paintIcon(this, g, i*tWidth+3, tGap+3);
			}
			String title = "Blank page";
			if(currentPage.pageName != null)
				title = currentPage.pageName;
			else if(currentPage.pageUrl != null)
				title = currentPage.pageUrl;
			g.setColor(Color.BLACK);
			g.drawString(title, i*tWidth+22, tGap+18);
			//Draw the lines
			g.setColor(this.getBackground().darker().darker());
			g.drawLine(i*tWidth-1, tGap, i*tWidth-1, tGap+tHeight);
			g.drawLine(i*tWidth-1, tGap, i*tWidth+tWidth, tGap);
			g.drawLine(i*tWidth+tWidth, tGap, i*tWidth+tWidth+tHeight, tHeight+tGap);
			g.setColor(Color.WHITE);
			g.drawLine(i*tWidth, tGap+1, i*tWidth+tWidth, tGap+1);
			g.drawLine(i*tWidth+tWidth, tGap+1, i*tWidth+tWidth+tHeight-2, tHeight+tGap-1);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int tab = getPageUnderMouse(e.getX(), e.getY());
		if(tab != -1)
		{
			currentPage = pages.get(tab);
			repaint();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		highTabIdx = -1;
		repaint();
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		highTabIdx = getPageUnderMouse(e.getX(), e.getY());
		repaint();
	}
}


