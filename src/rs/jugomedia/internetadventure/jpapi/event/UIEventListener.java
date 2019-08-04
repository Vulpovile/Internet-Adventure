package rs.jugomedia.internetadventure.jpapi.event;

public abstract class UIEventListener implements EventListener{

	@Override
	public void handle(Event evt) {
		switch (evt.evtType) {
			case UI_STATE:
				if(evt instanceof UIStateEvent)
					stateChange((UIStateEvent)evt);
				else System.err.println("Invalid class:" + evt.getClass().getName() + "\n Expected UIStateEvent");
				break;
			default:
				break;
		}
	}
	public abstract void stateChange(UIStateEvent evt);

}
