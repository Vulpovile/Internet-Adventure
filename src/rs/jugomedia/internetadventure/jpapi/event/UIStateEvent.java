package rs.jugomedia.internetadventure.jpapi.event;

public class UIStateEvent extends Event {
	private State state;
	public static enum State
	{
		UI_CLOSE, UI_HIDE, UI_MAX, UI_STYLIZE, UI_LOAD, UI_SHOW
	}
	public UIStateEvent(State state)
	{
		this.evtType = Type.UI_STATE;
		this.state = state;
	}
	
	public State getState()
	{
		return state;
	}
	/*
	@Override
	@Deprecated
	public final void cancel()
	{
		throw new RuntimeException("Server save events cannot be cancelled!");
	}*/
}
