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
			case NAVIGATE:
				if(evt instanceof NavigateEvent)
					onNavigate((NavigateEvent)evt);
				else System.err.println("Invalid class:" + evt.getClass().getName() + "\n Expected RenderRequestEvent");
				break;
			default:
				break;
		}
	}
	public abstract void renderRequest(RenderRequestEvent evt);
	public abstract void onNavigate(NavigateEvent evt);

}
