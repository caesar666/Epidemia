package br.com.cartec.epidemia.controller.move;

import br.com.cartec.epidemia.metrics.Direction;
import br.com.cartec.epidemia.metrics.Position;
import br.com.cartec.epidemia.motion.Route;

public class MoveNPC extends Move
{
	private Route route;

	public MoveNPC(Position position, Position pixelPosition)
	{
		super(position, pixelPosition);
	}

	public void move()
	{
		if (internalTime >= mpp)
			internalTime -= mpp;
		else
			return;
		
		int speed = 1;

		Position next = route.getNext();
		if (next == null)
		{
			this.setWalking(false);
			return;
		}

		Position now = this.position;

		Direction d = null;

		if (next.getX() < now.getX())
		{
			d = Direction.LEFT;

			if (next.getY() < now.getY())
				d = Direction.UL;
			if (next.getY() > now.getY())
				d = Direction.DL;
		}
		else if (next.getX() > now.getX())
		{
			d = Direction.RIGHT;

			if (next.getY() < now.getY())
				d = Direction.UR;
			if (next.getY() > now.getY())
				d = Direction.DR;
		}
		else if (next.getX() == now.getX())
		{
			if (next.getY() < now.getY())
				d = Direction.UP;
			if (next.getY() > now.getY())
				d = Direction.DOWN;
		}
		
		if(d == null)
		{
			System.out.println();
		}

		switch (d)
		{
			case UL:
				pixelPosition.add('y', -speed);
				pixelPosition.add('x', -speed);
				break;
			case UR:
				pixelPosition.add('y', -speed);
				pixelPosition.add('x', speed);
				break;
			case DL:
				pixelPosition.add('y', speed);
				pixelPosition.add('x', -speed);
				break;
			case DR:
				pixelPosition.add('y', speed);
				pixelPosition.add('x', speed);
				break;
			case LEFT:
				pixelPosition.add('x', -speed);
				break;
			case RIGHT:
				pixelPosition.add('x', speed);
				break;
			case DOWN:
				pixelPosition.add('y', speed);
				break;
			case UP:
				pixelPosition.add('y', -speed);
				break;

		}
	}

	public void tileChangeListener()
	{
		if (route.getNext().getX() == position.getX() && route.getNext().getY() == position.getY())
			this.route.increse();
	}

	public Route getRoute()
	{
		return route;
	}

	public void setRoute(Route route)
	{
		this.route = route;
	}

}
