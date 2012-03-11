package graphics.animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation
{
	private List<BufferedImage> listSprites = new ArrayList<BufferedImage>();
	private int index;
	private int numberSprites;
	private int delay;
	private int clock;
	
	public Animation(List<BufferedImage> listSprites, int delay)
	{
		this.delay = delay;
		this.listSprites = listSprites;
		this.numberSprites = this.listSprites.size();
		this.index = 0;
	}

	public void update(int time)
	{
		this.clock += time;
		while(this.clock >= this.delay)
		{
			this.incrementIndex();
			this.clock -= this.delay;
		}
	}
	
	public BufferedImage getImage()
	{
		return listSprites.get(index);
	}
	
	private void incrementIndex()
	{
		this.index++;
		if(this.index >= numberSprites)
		{
			index = 0;
		}
	}
}
