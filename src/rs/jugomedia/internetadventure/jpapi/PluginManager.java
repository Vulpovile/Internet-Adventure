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

public class PluginManager {
	private StarterClass starterClass;
	private HashMap<String, JavaPlugin> plugins = new HashMap<String, JavaPlugin>();
	private HashMap<JavaPlugin, String> names = new HashMap<JavaPlugin, String>();
	/**
	 * API version to allow usage of new features when possible, but keep compatible with older versions of JPAPI
	 */
	public static final float API_VERSION = 0.1F;
	private static final File pluginDir = Util.getPluginDir();
	
	
	public PluginManager(StarterClass starterClass)
	{
		if(starterClass != null)
		this.starterClass = starterClass;
		else throw new RuntimeException("no");
		loadPlugins();
	}
	
	/**
	 * Returns the global plug-in directory for all plug-in.
	 * Not for storing data for your plug-in
	 * @return
	 */
	public final File getGeneralPluginDirectory() {
		// TODO Auto-generated method stub
		return pluginDir;
	}
	//Should not be able to be called by plugins :/
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (Error e)
				{
					//MinecraftServer.log.severe("Plugin " + pluginName+" crashed while attempting to load. (is it out of date?):");
					// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (Error e)
				{
					//MinecraftServer.log.severe("Plugin " + name.get(i)+" crashed while attempting to load. (is it out of date?):");
					// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (Error e)
				{
					//MinecraftServer.log.severe("Plugin " + name.get(i)+" crashed while attempting to load. (is it out of date?):");
					// TODO Auto-generated catch block
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
	
	
	
}