package br.com.cartec.epidemia.metrics;

public class MathUtils
{
	public static boolean between(Position pos, int v1, int v2, char axis)
	{
		int value = 0;
		switch (axis)
		{
			case 'x':
				value = pos.getX();
				break;

			case 'y':
				value = pos.getY();
				break;
		}
		
		return (value <= v2 && value >= v1);
	}
}
