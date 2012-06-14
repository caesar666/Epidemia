package br.com.cartec.epidemia.metrics;

public class Axes
{
	private boolean x;
	
	public Axes(char c)
	{
		if(c == 'x')
			x = true;
		else
			x = false;
	}
	
	public Axes(Direction dir)
	{
		x = dir.equals(Direction.LEFT) || dir.equals(Direction.RIGHT);
	}
	
	public boolean isX()
	{
		return x;
	}
	
	public boolean isY()
	{
		return !x;
	}
}
