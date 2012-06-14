package br.com.cartec.epidemia.motion;

import java.util.List;

import br.com.cartec.epidemia.metrics.Position;


public class Route
{
	private int index;
	private List<Position> route;

	public Route(List<Position> route)
	{
		this.index = 1;
		this.route = route;
	}

	public Position getNext()
	{
		if (index >= this.route.size())
			return null;
		return this.route.get(index);
	}

	public void increse()
	{
		index++;
	}
}
