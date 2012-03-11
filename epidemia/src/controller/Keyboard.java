package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import metrics.Direction;
import metrics.Position;
import motion.MotionCalc;
import people.PlayerCharacter;
import controller.move.Move;

public class Keyboard implements KeyListener
{
	int keysPressed = 0;
	int directionPressed = 0;
	Move move;

	@Override
	public void keyPressed(KeyEvent key)
	{
		Character keyChar = Character.toLowerCase(key.getKeyChar());
		pressed(keyChar);
	}

	@Override
	public void keyReleased(KeyEvent key)
	{
		Character keyChar = Character.toLowerCase(key.getKeyChar());
		keysPressed--;

		if (keyChar == 'w' || keyChar == 'a' || keyChar == 's' || keyChar == 'd')
		{
			this.directionPressed--;
			move.getDirections().remove(char2Dir(keyChar));

			if (directionPressed <= 0)
			{
				directionPressed = 0;
				Controller.player.getMove().setWalking(false);
				Controller.player.setDirection(null);
			}
			else
			{
				this.directionPressed--;
				keysPressed++;

				try
				{
					Character c = dir2Char(move.getDirections().get(0));
					move.getDirections().clear();
					pressed(c);
				}
				catch (Exception e)
				{
					//TODO as vezes da merda aqui mas se nao fizer nda nao tem prob aehaueh
				}
			}

		}

	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
	}

	private void pressed(Character keyChar)
	{
		PlayerCharacter player = Controller.player;
		move = player.getMove();
		if (move.getDirections().contains(char2Dir(keyChar)))
			return;

		if (keyChar == 'w')
		{
			keysPressed++;
			directionPressed++;
			move.getDirections().add(Direction.UP);
			player.getMove().setWalking(true);
		}
		if (keyChar == 's')
		{
			keysPressed++;
			directionPressed++;
			move.getDirections().add(Direction.DOWN);
			player.getMove().setWalking(true);
		}
		if (keyChar == 'a')
		{
			keysPressed++;
			directionPressed++;
			move.getDirections().add(Direction.LEFT);
			player.getMove().setWalking(true);
		}
		if (keyChar == 'd')
		{
			keysPressed++;
			directionPressed++;
			move.getDirections().add(Direction.RIGHT);
			player.getMove().setWalking(true);
		}

		if (keyChar == 'o')
		{
			Position dotA = Controller.zombieTest.getPosition();
			Position dotB = Controller.player.getPosition();

			Controller.zombieTest.getMove().setRoute(MotionCalc.calcRoute(dotA, dotB));
			if (Controller.zombieTest.getMove().getRoute() != null)
				Controller.zombieTest.getMove().setWalking(true);
		}
	}

	private Direction char2Dir(char c)
	{
		switch (c)
		{
			case 'w':
				return Direction.UP;
			case 's':
				return Direction.DOWN;
			case 'a':
				return Direction.LEFT;
			case 'd':
				return Direction.RIGHT;
		}
		return null;
	}

	private Character dir2Char(Direction d)
	{
		switch (d)
		{
			case UP:
				return 'w';
			case DOWN:
				return 's';
			case LEFT:
				return 'a';
			case RIGHT:
				return 'd';
		}
		return null;
	}

}
