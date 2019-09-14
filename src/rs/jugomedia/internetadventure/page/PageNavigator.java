package rs.jugomedia.internetadventure.page;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.SSLHandshakeException;

public class PageNavigator {
	public URL getURLFromString(String location)
	{
		URI url = URI.create(location);
		
		try {
			return url.toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getSiteString(PageInfo page, URL[] url)
			throws IOException, SSLHandshakeException {
		
		URLConnection con = url[0].openConnection();
		con.setRequestProperty("User-Agent", "MSIE 4");
	    
		InputStream in = null;
		try{
		in = new BufferedInputStream(con.getInputStream());
		}catch(IOException ex)
		{
			if(con instanceof HttpURLConnection)
				in = new BufferedInputStream(((HttpURLConnection) con).getErrorStream());
			else throw ex;
		}
		if(con instanceof HttpURLConnection)
			url[0] = con.getURL();
		System.out.println(con.getURL());
		page.setMaxProgress(Math.max(con.getContentLength(), in.available()));

		String bytearr = new String();
		byte[] buffer = new byte[1024];
		page.setProgress(0);
		page.setProgressText("Transferring from " + url[0].toString() + "...");
		while ((in.read(buffer)) > 0)
		{
			
			page.setProgress(page.getProgress()+1024);
			//frame.progressBar.repaint();
			//frame.progressBar.revalidate();
			bytearr+=new String(buffer);
			buffer = new byte[1024];
		}
		in.close();
		page.setProgress(page.getMaxProgress());
		page.setProgressText("Transferred.");
		return bytearr;
	}
	
	public static InputStream getSiteData(PageInfo page, URL[] url)
			throws IOException, SSLHandshakeException {
		
		URLConnection con = url[0].openConnection();
		con.setRequestProperty("User-Agent", "MSIE 4");
	    
		InputStream in = null;
		try{
		in = new BufferedInputStream(con.getInputStream());
		}catch(IOException ex)
		{
			if(con instanceof HttpURLConnection)
				in = new BufferedInputStream(((HttpURLConnection) con).getErrorStream());
			else throw ex;
		}
		if(con instanceof HttpURLConnection)
			url[0] = con.getURL();
		System.out.println(con.getURL());
		page.setMaxProgress(Math.max(con.getContentLength(), in.available()));

		ByteArrayOutputStream bytearr = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		page.setProgress(0);
		page.setProgressText("Transferring from " + url[0].toString() + "...");
		while ((in.read(buffer)) > 0)
		{
			
			page.setProgress(page.getProgress()+1024);
			//frame.progressBar.repaint();
			//frame.progressBar.revalidate();
			bytearr.write(buffer);
			buffer = new byte[1024];
		}
		in.close();
		page.setProgress(page.getMaxProgress());
		return new ByteArrayInputStream(bytearr.toByteArray());
	}
}
