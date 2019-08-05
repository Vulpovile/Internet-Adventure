package rs.jugomedia.internetadventure.jpapi.event;

public abstract class BrowserEventListener implements EventListener{

	@Override
	public void handle(Event evt) {
		switch (evt.evtType) {
			case RENDER_REQUEST:
				if(evt instanceof RenderRequestEvent)
					renderRequest((RenderRequestEvent)evt);
				else System.err.println("Invalid class:" + evt.getClass().getName() + "\n Expected RenderRequestEvent");
				break;
			default:
				break;
		}
	}
	public abstract void renderRequest(RenderRequestEvent evt);

}
