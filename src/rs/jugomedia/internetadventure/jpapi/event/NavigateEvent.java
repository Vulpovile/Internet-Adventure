package rs.jugomedia.internetadventure.jpapi.event;

import rs.jugomedia.internetadventure.page.PageInfo;

public class NavigateEvent extends Event {
	private PageInfo page;
	public NavigateEvent(PageInfo page)
	{
		this.evtType = Type.UI_STATE;
		this.page = page;
	}
	
	public PageInfo getPageInfo()
	{
		return page;
	}
	/*
	@Override
	@Deprecated
	public final void cancel()
	{
		throw new RuntimeException("Server save events cannot be cancelled!");
	}*/
}
