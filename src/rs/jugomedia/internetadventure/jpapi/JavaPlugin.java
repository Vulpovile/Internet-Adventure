package rs.jugomedia.internetadventure.jpapi;

import java.io.File;

public abstract class JavaPlugin {
	PluginManager pluginManager;
	/**
	 * Returns the {@link PluginManager}
	 */
	public final PluginManager getManager()
	{
		return pluginManager;
	}
	/**
	 * Called when the plug-in is first initialized by the {@link PluginManager}
	 */
	public abstract void init();
	/**
	 * Called when the {@link PluginManager} unloads or requests destruction of the {@link JavaPlugin} instance
	 */
	public abstract void destroy();
	/**
	 * Returns a specific plug-in directory for your plug-in.
	 * This is where plugin data should be stored
	 * @return
	 */
	public final File getPluginDirectory() {
		return pluginManager.getPluginDirectory(this);
	}
	
	

}
