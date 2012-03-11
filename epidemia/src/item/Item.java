package item;

import java.awt.image.BufferedImage;

public abstract class Item
{
	BufferedImage img;
	String name;
	String description;
	int quantity;

	public BufferedImage getImg()
	{
		return img;
	}

	public void setImg(BufferedImage img)
	{
		this.img = img;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

}
