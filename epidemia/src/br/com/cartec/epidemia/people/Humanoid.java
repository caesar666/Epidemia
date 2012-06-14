package br.com.cartec.epidemia.people;

import br.com.cartec.epidemia.controller.move.Move;
import br.com.cartec.epidemia.metrics.Direction;
import br.com.cartec.epidemia.metrics.Position;

public abstract class Humanoid
{
	private Position position = null;
	private Position pixelPos = new Position(0, 0);
	private Direction direction;
	private int speed = 4;
	private int ppm = 500;
	

	public Position getPosition()
	{
		return position;
	}

	public void setPosition(Position position)
	{
		this.position = position;
	}

	public int getSpeed()
	{
		return speed;
	}

	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	public Position getPixelPos()
	{
		return pixelPos;
	}

	public void setPixelPos(Position pixelPos)
	{
		this.pixelPos = pixelPos;
	}

	public Direction getDirection()
	{
		return direction;
	}

	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}

	public int getPpm()
	{
		return ppm;
	}

	public void setPpm(int ppm)
	{
		this.ppm = ppm;
	}
	
	public abstract Move getMove();
}
