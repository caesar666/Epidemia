package br.com.cartec.epidemia.controller.firegun;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.cartec.epidemia.controller.AutoRender;
import br.com.cartec.epidemia.field.FieldView;
import br.com.cartec.epidemia.metrics.Position;



public class ShotLine implements AutoRender
{
	private List<Position> line = new ArrayList<Position>();
	private final Color lineColor = Color.RED;

	boolean inverse = false;

	public void calculeShot(Position p1, Position p2)
	{
		int x, y, erro, deltaX, deltaY;
		erro = 0;
		x = p1.getX();
		y = p1.getY();
		deltaX = p2.getX() - p1.getX();
		deltaY = p2.getY() - p1.getY();

		int multi = 2;

		int x2 = p2.getX();
		int y2 = p2.getY();
		for (; Math.abs(x2) < 100 && Math.abs(y2) < 100; multi++)
		{
			x2 = p2.getX();
			y2 = p2.getY();
			multi++;
			x2 = x2 + (deltaX * multi);
			y2 = y2 + (deltaY * multi);
		}

		p2 = new Position(p2.getX() + deltaX * multi, p2.getY() + deltaY * multi);

		deltaX = p2.getX() - p1.getX();
		deltaY = p2.getY() - p1.getY();

		if ((Math.abs(deltaY) >= Math.abs(deltaX) && p1.getY() > p2.getY())
				|| (Math.abs(deltaY) < Math.abs(deltaX) && deltaY < 0))
		{
			inverse = true;
		}

		if (!inverse)
		{
			teste1(x, y, erro, deltaX, deltaY);
		}
		else
		{
			x = p2.getX();
			y = p2.getY();
			deltaX = p1.getX() - p2.getX();
			deltaY = p1.getY() - p2.getY();

			teste1(x, y, erro, deltaX, deltaY);

			Collections.reverse(this.line);

			List<Position> lineTemp = new ArrayList<Position>();
			this.inverse = false;
			for (int i = 0; i < this.line.size(); i++)
			{
				if (!continueLine(line.get(i)))
					break;
				lineTemp.add(line.get(i));
			}
			line.clear();
			line = lineTemp;
		}
	}

	private void teste1(int x, int y, int erro, int deltaX, int deltaY)
	{
		if (deltaX >= 0)
		{
			if (Math.abs(deltaX) >= Math.abs(deltaY))
			{
				for (int i = 1; i < Math.abs(deltaX); i++)
				{
					if (erro < 0)
					{
						x++;
						if (!addPixel(new Position(x, y)))
							return;
						erro += deltaY;
					}
					else
					{
						x++;
						y++;
						if (!addPixel(new Position(x, y)))
							return;
						erro += deltaY - deltaX;
					}
				}
			}
			else
			{
				for (int i = 1; i < Math.abs(deltaY); i++)
				{
					if (erro < 0)
					{
						x++;
						y++;
						if (!addPixel(new Position(x, y)))
							return;
						erro += deltaY - deltaX;
					}
					else
					{
						y++;
						if (!addPixel(new Position(x, y)))
							return;
						erro -= deltaX;
					}
				}
			}
		}
		else
		{
			if (Math.abs(deltaX) >= Math.abs(deltaY))
			{
				for (int i = 1; i < Math.abs(deltaX); i++)
				{
					if (erro < 0)
					{
						x--;
						if (!addPixel(new Position(x, y)))
							return;
						erro += deltaY;
					}
					else
					{
						x--;
						y++;
						if (!addPixel(new Position(x, y)))
							return;
						erro += deltaY + deltaX;
					}
				}
			}
			else
			{
				for (int i = 1; i < Math.abs(deltaY); i++)
				{
					if (erro < 0)
					{
						x--;
						y++;
						if (!addPixel(new Position(x, y)))
							return;
						erro += deltaY + deltaX;
					}
					else
					{
						y++;
						if (!addPixel(new Position(x, y)))
							return;
						erro += deltaX;
					}
				}
			}
		}
	}

	private boolean addPixel(Position p)
	{
		if (continueLine(p))
		{
			this.line.add(p);
			return true;
		}
		return false;
	}

	private boolean continueLine(Position p)
	{
		// fuck shit this shit all shit
		if (this.inverse)
			return true;
		return FieldView.getInstance().getTileForPixel(p).isUpFree();
	}

	
	public void render(Graphics g)
	{
		g.setColor(lineColor);

		Position begin = line.get(0);
		Position end = line.get(line.size() - 1);
		g.drawLine(begin.getX(), begin.getY(), end.getX(), end.getY());
	}
}
