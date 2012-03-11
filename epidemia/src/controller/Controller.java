package controller;

import java.awt.Graphics;

import metrics.Position;
import people.Humanoid;
import people.PlayerCharacter;
import people.npc.Zombie;
import controller.move.Move;
import controller.move.MoveNPC;
import field.FieldView;
import field.Tile;
import graphics.ImageStore;

public class Controller
{
	private static int time;
	
	public static Position shot;

	public static PlayerCharacter player;
	public static Zombie zombieTest;

	public static void load()
	{
		player = new PlayerCharacter();
		player.setPosition(PlayerCharacter.playerPos);
		player.setMove(new Move(player.getPosition(), player.getPixelPos()));

		zombieTest = new Zombie();
		zombieTest.setPosition(new Position(18, 16));
		zombieTest.setMove(new MoveNPC(zombieTest.getPosition(), zombieTest.getPixelPos()));
	}

	public static void renderPeople(Graphics g)
	{
		g.drawImage(player.getGraphic().getImage(), 1024 / 2, (640 / 2) - 32, null);

		Tile tileZombie = FieldView.getInstance().mapTiles.get(zombieTest.getPosition().getKey());

		g.drawImage(ImageStore.getImage("zombie_teste"), tileZombie.getPixelPos().getX()
				+ zombieTest.getPixelPos().getX() + 4, zombieTest.getPixelPos().getY()
				+ tileZombie.getPixelPos().getY() + 4, null);

	}

	public static void execute()
	{
		player.getMove().move();
		zombieTest.getMove().move();
	}

	public static void updateTime(int time)
	{
		Controller.time += time;
		player.getGraphic().update(time);
		player.getMove().update(time);
		zombieTest.getMove().update(time);
	}

	public static void verifyPixelPos(Humanoid human)
	{
		Position pp = human.getPixelPos();
		Position tp = human.getPosition();

		int acceptable = Tile.size / 2;

		if (human instanceof Zombie)
			acceptable = Tile.size;

		if (pp.getX() > acceptable)
		{
			tp.add('x', 1);
			pp.add('x', -Tile.size);
			human.getMove().tileChangeListener();
		}

		if (pp.getX() < -acceptable)
		{
			tp.add('x', -1);
			pp.add('x', Tile.size);
			human.getMove().tileChangeListener();
		}

		if (pp.getY() > acceptable)
		{
			tp.add('y', 1);
			pp.add('y', -Tile.size);
			human.getMove().tileChangeListener();
		}

		if (pp.getY() < -acceptable)
		{
			tp.add('y', -1);
			pp.add('y', Tile.size);
			human.getMove().tileChangeListener();
		}
	}
	
	public static void shot(Position target)
	{
		shot = target;
	}
}
