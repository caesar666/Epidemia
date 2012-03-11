package metrics;


public class Position
{
	private int x;
	private int y;

	
	public Position(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
	}

	public void add(char axis, int qtd)
	{
		if (axis == 'x')
			this.x += qtd;
		else
			this.y += qtd;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	@Override
	public String toString()
	{
		return getX() + "x" + getY();
	}

	public String getKey()
	{
		return (this.x + "x" + this.y);
	}

	@Override
	public boolean equals(Object o2)
	{
		if (o2 == null)
			return false;
		Position p2 = ((Position) o2);

		int x = p2.getX();
		int y = p2.getY();

		return x == this.getX() && y == this.getY();
	}
}
