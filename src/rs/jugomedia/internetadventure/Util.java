package rs.jugomedia.internetadventure;

import java.io.File;

public class Util {
	enum OS {
		windows, macos, solaris, linux, unknown
	};

	private static OS getPlatform() {
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("win")) {
			return OS.windows;
		}
		if (osName.contains("mac")) {
			return OS.macos;
		}
		if (osName.contains("solaris") || osName.contains("sunos")) {
			return OS.solaris;
		}
		if (osName.contains("linux") || osName.contains("unix")) {
			return OS.linux;
		}
		return OS.unknown;
	}
	
	public static File getDataDir()
	{
		File dataDir = new File(getAppData(), "Internet Adventure/");
		if(!dataDir.exists()&& !dataDir.mkdirs())
		{
			throw new RuntimeException("Failed to create application plugin directory");	
		}
		return dataDir;
	}
	
	public static File getPluginDir()
	{
		File dataDir = new File(getDataDir(), "Plugins/");
		if(!dataDir.exists()&& !dataDir.mkdirs())
		{
			throw new RuntimeException("Failed to create application plugin directory");	
		}
		return dataDir;
	}

	public static File getAppData() {
		File workingDirectory;
		String userHome = System.getProperty("user.home", ".");
		OS os = Util.getPlatform();
		switch (os) {
			case linux:
			case solaris: {
				workingDirectory = new File(userHome + '/');
				break;
			}
			case windows: {
				String applicationData = System.getenv("APPDATA");
				if (applicationData != null) {
					workingDirectory = new File(applicationData);
					break;
				}
				workingDirectory = new File(userHome + '/');
				break;
			}
			case macos: {
				workingDirectory = new File(userHome, "Library/Application Support/");
				break;
			}
			default: {
				workingDirectory = new File(userHome + '/');
			}
		}
		if (!workingDirectory.exists() && !workingDirectory.mkdirs()) {
			throw new RuntimeException("The working directory could not be created: " + workingDirectory);
		}
		return workingDirectory;
	}
}
