package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.imageio.ImageIO;

import system.ErrorManager;

public class ImageStore
{
	private static Map<String, BufferedImage> store;

	public static void load()
	{
		store = new HashMap<String, BufferedImage>();
		
		try
		{
			Properties props = new Properties();
			URL url = ClassLoader.getSystemResource("properties/imgs.properties");
			props.load(url.openStream());
			
			for(Entry<Object, Object> line : props.entrySet())
				store.put((String)line.getKey(), loadImage((String)line.getValue()));
			
		}
		catch (Exception e)
		{
			ErrorManager.printAndExit(e);
		}
	}
	
	public static BufferedImage getImage(String code)
	{
		if(store == null)
			load();
		
		return store.get(code);
	}

	private static BufferedImage loadImage(String path) throws IOException 
	{
		return ImageIO.read(ImageStore.class.getClassLoader().getResourceAsStream(path));
	}
}
