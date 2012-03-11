package window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JPanel;

import metrics.Position;
import people.PlayerCharacter;
import system.ErrorManager;
import controller.Clock;
import controller.Controller;
import controller.GameCore;
import controller.Mouse;
import field.FieldView;
import field.Tile;
import graphics.ImageStore;
import graphics.ShadowManager;

public class GamePanel extends JPanel
{
	private static final long serialVersionUID = -1809568356243249099L;

	private void renderAll(Graphics g)
	{

		clearPanel(g);
		FieldView.getInstance().render(g);
		Controller.renderPeople(g);
		ShadowManager.render((Graphics2D) g);
		drawShot(g);
		drawCursor(g);
		Clock.render(g);

		// loadField(); depois quando o server tiver pronto por esse kra na
		// ativa

	}

	private void drawShot(Graphics g)
	{
		if (Controller.shot == null)
			return;

		g.setColor(Color.RED);

		Position playerPos = FieldView.getInstance().mapTiles.get(
				PlayerCharacter.playerPos.getKey()).getPixelPos();

		playerPos.add('x', Tile.size / 2);
		playerPos.add('y', Tile.size / 2);

		bresenham(playerPos, Controller.shot, g);
	}

	private void drawShotPixel(Position p, Graphics g)
	{
		g.fillRect(p.getX(), p.getY(), 1, 1);
	}

	private void bresenham(Position p1, Position p2, Graphics g)
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
		for (; Math.abs(x2) < 1000 && Math.abs(y2) < 1000; multi++)
		{
			x2 = p2.getX();
			y2 = p2.getY();
			multi++;
			x2 =x2 + (deltaX * multi);
			y2 =y2 + (deltaY * multi);
		}

		p2 = new Position(p2.getX() + deltaX * multi, p2.getY() + deltaY * multi);

		deltaX = p2.getX() - p1.getX();
		deltaY = p2.getY() - p1.getY();

		boolean invert = false;
		if ((Math.abs(deltaY) >= Math.abs(deltaX) && p1.getY() > p2.getY())
				|| (Math.abs(deltaY) < Math.abs(deltaX) && deltaY < 0))
		{
			invert = true;
		}
		drawShotPixel(p1, g);

		if (!invert)
		{
			teste1(g, x, y, erro, deltaX, deltaY);
		}
		else
		{
			 x = p2.getX();
			 y = p2.getY();
			 deltaX = p1.getX() - p2.getX();
			 deltaY = p1.getY() - p2.getY();
			
			teste1(g, x, y, erro, deltaX, deltaY);
		}

	}

	private void teste1(Graphics g, int x, int y, int erro, int deltaX, int deltaY)
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
						drawShotPixel(new Position(x, y), g);
						erro += deltaY;
					}
					else
					{
						x++;
						y++;
						drawShotPixel(new Position(x, y), g);
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
						drawShotPixel(new Position(x, y), g);
						erro += deltaY - deltaX;
					}
					else
					{
						y++;
						drawShotPixel(new Position(x, y), g);
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
						drawShotPixel(new Position(x, y), g);
						erro += deltaY;
					}
					else
					{
						x--;
						y++;
						drawShotPixel(new Position(x, y), g);
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
						drawShotPixel(new Position(x, y), g);
						erro += deltaY + deltaX;
					}
					else
					{
						y++;
						drawShotPixel(new Position(x, y), g);
						erro += deltaX;
					}
				}
			}
		}
	}

	private void drawCursor(Graphics g)
	{
		this.setCursor(Mouse.getFakeCursor());
		Point p = MouseInfo.getPointerInfo().getLocation();

		g.drawImage(ImageStore.getImage("crosshair1"), new Double(p.getX()).intValue() - Tile.size
				/ 2, new Double(p.getY()).intValue() - Tile.size / 2, null);
	}

	private void clearPanel(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1024, 640);
	}

	public void render()
	{
		this.repaint();
	}

	public void paint(Graphics g)
	{
		try
		{
			if (!GameCore.isRunning())
				return;

			renderAll(g);
		}
		catch (Exception e)
		{
			ErrorManager.printAndExit(e);
		}
	}

	// private void loadField()
	// {
	// if (!Keyboard.moving)
	// return;
	//
	// if (fieldView.isFieldUpOver())
	// {
	// fieldView.getPosition().add('y', -Tile.size);
	// List<FieldLine> lista = new ArrayList<FieldLine>();
	//
	// lista.add(new FieldLine(newCode));
	// for (FieldLine line : fieldView.getLines())
	// {
	// lista.add(line);
	// }
	// lista.remove(lista.size() - 1);
	// fieldView.setLines(lista);
	// }
	//
	// if (fieldView.isFieldDownOver())
	// {
	// fieldView.getPosition().add('y', Tile.size);
	// fieldView.getLines().remove(0);
	// fieldView.getLines().add(new FieldLine(newCode));
	// }
	//
	// if (fieldView.isFieldRightOver())
	// {
	//
	// fieldView.getPosition().add('x', Tile.size);
	// for (FieldLine line : fieldView.getLines())
	// {
	// line.getTiles().remove(0);
	// line.getTiles().add(new Tile());
	// }
	// }
	//
	// if (fieldView.isFieldLeftOver())
	// {
	// fieldView.getPosition().add('x', -Tile.size);
	// for (FieldLine line : fieldView.getLines())
	// {
	// List<Tile> tiles = new ArrayList<Tile>();
	// tiles.add(new Tile());
	// for (int i = line.getTam() - 1; i > 0; i--)
	// {
	// tiles.add(line.getTiles().get(i));
	// }
	// line.setTiles(tiles);
	// }
	// }
	// }
}
