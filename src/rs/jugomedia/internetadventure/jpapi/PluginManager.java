package rs.jugomedia.internetadventure.jpapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;

import rs.jugomedia.internetadventure.StarterClass;
import rs.jugomedia.internetadventure.UserInterface;
import rs.jugomedia.internetadventure.Util;
import rs.jugomedia.internetadventure.jpapi.event.Event;
import rs.jugomedia.internetadventure.jpapi.event.EventListener;
/**
 * JPAPI (Jar Plugin API) Plugin Manager, general purpose plugin loader for Internet Adventure<br>
 * Using URLClassLoader, To create plugins, refer to {@link JavaPlugin}
 * @author Vulpovile
 */
public class PluginManager {
	private static boolean isDefined = false;
	private StarterClass starterClass;
	private HashMap<String, JavaPlugin> plugins = new HashMap<String, JavaPlugin>();
	private HashMap<JavaPlugin, String> names = new HashMap<JavaPlugin, String>();
	private HashMap<Event.Type, ArrayList<RegisteredEvent>> events = new HashMap<Event.Type, ArrayList<RegisteredEvent>>();
	/**
	 * API version to allow usage of new features when possible, but keep compatible with older versions of JPAPI
	 */
	public static final float API_VERSION = 0.1F;
	private static final File pluginDir = Util.getPluginDir();
	
	/**
	 * Registers an event and returns a {@link RegisteredEvent} object that can be used to unregister the event.
	 * <br>To unregister, use the {@link unregisterEvent} method.
	 * <br>Returns null on invalid parameters
	 * @return {@link RegisteredEvent}
	 * @param {@link Event.Type} type
	 * @param {@link EventListener} listener
	 * @param {@link JavaPlugin} plugin
	 * @param {@link Event.Priority} priority
	 */
	public RegisteredEvent registerEvent(Event.Type type, EventListener listener, JavaPlugin plugin, Event.Priority priority)
	{
		if(type != null && listener != null && plugin != null && priority != null)
		{
			RegisteredEvent event = new RegisteredEvent(type, listener, plugin, priority);
			ArrayList<RegisteredEvent> evtList = events.get(type);
			if(evtList == null)
			{
				evtList = new ArrayList<RegisteredEvent>();
				events.put(type, evtList);
			}
			
			for(int i = 0; i < evtList.size(); i++)
			{
				System.out.println(evtList.get(i).getPriority().compareTo(priority));
				if(evtList.get(i).getPriority().compareTo(priority) > 0)
				{
					evtList.add(i, event);
					return event;
				}
			}
			evtList.add(event);
			return event;
		}
		return null;
	}
	/**
	 * 
	 */
	public boolean processEvent(Event evt)
	{
		if(evt != null)
		{
			ArrayList<RegisteredEvent> evtList = events.get(evt.getType());
			if(evtList != null)
			{
				for(int i = 0; i < evtList.size(); i++)
				{
					evtList.get(i).getListener().handle(evt);
				}
			}
			return !evt.isCancelled();
		}
		else throw new RuntimeException("Event parameter cannot be null");
	}
	/**
	 * Unregisters a registered event when passed a {@link RegisteredEvent} object
	 * Does nothing with invalid parameters
	 * @param {@link RegisteredEvent} event
	 */
	public void unregisterEvent(RegisteredEvent event)
	{
		if(event != null)
		{
			ArrayList<RegisteredEvent> evtList = events.get(event.getType());
			if(evtList != null)
			{
				evtList.remove(event);
				if(evtList.size() == 0)
				{
					events.remove(event.getType());
				}
			}
		}
	}
	
	/**
	 * Used to instantiate a Plugin Manager.
	 * This will throw an exception, do not use in a plugin
	 */
	public PluginManager(StarterClass starterClass)
	{
		if(isDefined)
			throw new RuntimeException("PluginManager already defined");
		isDefined = true;
		if(starterClass != null)
			this.starterClass = starterClass;
		else throw new RuntimeException("PluginManager cannot be defined without a valid StarterClass instance");
		loadPlugins();
	}
	
	/**
	 * Returns the global plugin directory for all plugin.
	 * Not for storing data for your plugin
	 * @return {@link java.io.File}
	 */
	public final File getGeneralPluginDirectory() {
		return pluginDir;
	}
	/**
	 * @deprecated
	 * Reloads all the plugins
	 * Unsafe, refrain from use
	 */
	public void reload()
	{
		
	}
	//Loads the plugins
	private void loadPlugins()
	{
		if(!pluginDir.isDirectory())
		{
			pluginDir.mkdir();
		}
		else
		{
			File[] jars = pluginDir.listFiles();
			ArrayList<URL> urls = new ArrayList<URL>();
			ArrayList<String> mainClass = new ArrayList<String>();
			ArrayList<String> name = new ArrayList<String>();
			if(jars != null)
			for(int i = 0; i < jars.length; i++)
			{
				String pluginName = jars[i].getName();
				try
				{
					if(jars[i].getName().endsWith(".jar"))
					{
						URLClassLoader classLoader = new URLClassLoader(new URL[]{jars[i].toURI().toURL()});
						BufferedReader reader = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream("plugin.info")));
						pluginName = reader.readLine();
						String mainClassStr = reader.readLine();
						reader.close();
						urls.add(jars[i].toURI().toURL());
						mainClass.add(mainClassStr);
						name.add(pluginName);
					}
				}
				catch (Exception e)
				{
					//MinecraftServer.log.severe("Failed to load plugin " + pluginName+" (is it out of date?):");
					e.printStackTrace();
				}
				catch (Error e)
				{
					//MinecraftServer.log.severe("Plugin " + pluginName+" crashed while attempting to load. (is it out of date?):");
					e.printStackTrace();
				}
			}
			URL[] uarr = new URL[urls.size()];
			urls.toArray(uarr);
			URLClassLoader classLoader = new URLClassLoader(uarr);
			for(int i = 0; i < urls.size(); i++)
			{
				try
				{
				Class<?> pluginClass;
				pluginClass = classLoader.loadClass(mainClass.get(i));
				JavaPlugin plugin = (JavaPlugin) pluginClass.newInstance();
				plugin.pluginManager = this;
				this.plugins.put(name.get(i), plugin);
				this.names.put(plugin, name.get(i));
				
				}
				catch (Exception e)
				{
					//MinecraftServer.log.severe("Failed to load plugin " + name.get(i)+" (is it out of date?):");
					e.printStackTrace();
				}
				catch (Error e)
				{
					//MinecraftServer.log.severe("Plugin " + name.get(i)+" crashed while attempting to load. (is it out of date?):");
					e.printStackTrace();
				}
			}
			ArrayList<JavaPlugin> plugins = new ArrayList<JavaPlugin>(this.plugins.values());
			for(int i = 0; i < plugins.size(); i++)
			{
				try{
					plugins.get(i).init();
				}
				catch (Exception e)
				{
					//MinecraftServer.log.severe("Failed to load plugin " + name.get(i)+" (is it out of date?):");
					e.printStackTrace();
				}
				catch (Error e)
				{
					//MinecraftServer.log.severe("Plugin " + name.get(i)+" crashed while attempting to load. (is it out of date?):");
					e.printStackTrace();
				}
			}
		}
	}
	
	//Return a plugin directory
	final File getPluginDirectory(JavaPlugin jp) {
		String className = names.get(jp);
		return new File(pluginDir, className);
	}
	
	/**
	 * Returns the browser instance
	 */
	public UserInterface getBrowser()
	{
		return starterClass.getBrowser();
	}
	
	
	
}
