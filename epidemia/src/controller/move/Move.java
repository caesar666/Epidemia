package controller.move;

import java.util.ArrayList;
import java.util.List;

import metrics.Direction;
import metrics.Position;
import field.FieldView;
import field.Tile;

public class Move
{
	protected List<Direction> directions = new ArrayList<Direction>();
	protected int mpp = 10;

	protected int internalTime;
	protected boolean walking = false;

	protected Position position;
	protected Position pixelPosition;

	public Move()
	{
	}

	public Move(Position position, Position pixelPosition)
	{
		this.position = position;
		this.pixelPosition = pixelPosition;
	}

	public void update(int time)
	{
		if (this.walking)
			internalTime += time;
		else
			internalTime = 0;
	}

	public List<Direction> getDirections()
	{
		return directions;
	}

	public void setDirections(List<Direction> directions)
	{
		this.directions = directions;
	}

	public int getMpp()
	{
		return mpp;
	}

	public void setMpp(int mpp)
	{
		this.mpp = mpp;
	}

	public boolean isWalking()
	{
		return walking;
	}

	public void setWalking(boolean walking)
	{
		this.walking = walking;
	}

	public void move()
	{
		if (internalTime >= mpp)
			internalTime -= mpp;
		else
			return;

		if (getDirections().contains(Direction.LEFT) && canMove(Direction.LEFT))
		{
			pixelPosition.add('x', -1);
			FieldView.getInstance().getPosition().add('x', 1);
		}
		if (getDirections().contains(Direction.RIGHT) && canMove(Direction.RIGHT))
		{
			pixelPosition.add('x', 1);
			FieldView.getInstance().getPosition().add('x', -1);
		}
		if (getDirections().contains(Direction.DOWN) && canMove(Direction.DOWN))
		{
			pixelPosition.add('y', 1);
			FieldView.getInstance().getPosition().add('y', -1);
		}
		if (getDirections().contains(Direction.UP) && canMove(Direction.UP))
		{
			pixelPosition.add('y', -1);
			FieldView.getInstance().getPosition().add('y', 1);
		}
	}

	private boolean canMove(Direction dir)
	{
		int difX = 0, difY = 0;

		switch (dir)
		{
			case UP:
				difY = -1;
				break;
			case DOWN:
				difY = 1;
				break;
			case LEFT:
				difX = -1;
				break;
			case RIGHT:
				difX = 1;
				break;
		}

		if (!isPixelPosMax(dir))
			return true;

		Position pos = new Position(position.getX() + (difX), position.getY() + (difY));

		Tile tileToGo = FieldView.getInstance().mapTiles.get(pos.getKey());
		if (tileToGo == null || tileToGo.isFree() == false)
			return false;

		return true;
	}

	private boolean isPixelPosMax(Direction dir)
	{
		switch (dir)
		{
			case UP:
				if (pixelPosition.getY() >= 0)
					return false;
				return true;
			case DOWN:
				if (pixelPosition.getY() <= 0)
					return false;
				return true;
			case LEFT:
				if (pixelPosition.getX() >= 0)
					return false;
				return true;
			case RIGHT:
				if (pixelPosition.getX() <= 0)
					return false;
				return true;
		}
		return false;
	}
	
	public void tileChangeListener()
	{
	}
}
