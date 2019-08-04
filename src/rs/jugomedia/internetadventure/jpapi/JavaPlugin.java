package rs.jugomedia.internetadventure.jpapi;

import java.io.File;
/**
 * JPAPI (Jar Plugin API) plugin class for creating your own plugins<br>
 * Extend from this class to create your own plugins.<br><br>
 * Each plugin must contain a plugin.info file, which on the first line contains the name<br>
 * and on the second line contains the class path (com.example.plugin.MyPlugin)
 * @author Vulpovile
 */
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
