package field;

import graphics.ImageStore;

import java.awt.image.BufferedImage;
import java.util.Random;

import metrics.Position;

public class Tile
{
	public static final int size = 32;
	
	public BufferedImage image;
	private boolean free = false;
	private boolean upFree = true;
	private Position position;
	private Position pixelPos;
	
	private String img = "";
	
	public Tile(int x, int y)
	{
		this.position = new Position(x, y);

		Random r = new Random(System.nanoTime());
		if (r.nextInt(10) < 4)
		{
			img = "floor";
			free = false;
			upFree = false;
		}
		else
		{
			img = "grass";
			free = true;
			upFree = true;
		}
		
		this.image = ImageStore.getImage(img);
		FieldView.getInstance().mapTiles.put(this.position.getKey(), this);
	}

	public boolean isFree()
	{
		return free;
	}

	public void setFree(boolean free)
	{
		this.free = free;
	}

	public Position getPosition()
	{
		return position;
	}

	public void setPosition(Position position)
	{
		this.position = position;
	}

	public String code()
	{
		return this.position.toString();
	}

	public Position getPixelPos()
	{
		return pixelPos;
	}

	public void setPixelPos(Position pixelPos)
	{
		this.pixelPos = pixelPos;
	}

	public boolean isUpFree()
	{
		return upFree;
	}

	public void setUpFree(boolean upFree)
	{
		this.upFree = upFree;
	}
}
