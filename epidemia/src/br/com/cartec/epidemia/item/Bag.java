package br.com.cartec.epidemia.item;

import java.util.ArrayList;
import java.util.List;

public class Bag
{
	private List<Item> itemList = new ArrayList<Item>();
	private int size = 6;

	public boolean addItem(Item item)
	{
		for (Item bagItem : itemList)
		{
			if (bagItem.getName().equals(item.getName()))
			{
				if (bagItem.getQuantity() < 20)
					bagItem.setQuantity(bagItem.getQuantity() + 1);
			}
		}

		if (this.size >= this.itemList.size())
			return false;

		this.itemList.add(item);
		return true;
	}
}
