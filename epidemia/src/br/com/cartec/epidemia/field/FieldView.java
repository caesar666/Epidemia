package br.com.cartec.epidemia.field;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.cartec.epidemia.controller.Controller;
import br.com.cartec.epidemia.metrics.Position;


public class FieldView
{
	public Map<String, Tile> mapTiles = new HashMap<String, Tile>();
	private List<FieldLine> lines;
	private Position position = new Position(-(Tile.size * 2), -(Tile.size * 2));
	private int tam = 24;

	private static FieldView fieldView;

	public static FieldView getInstance()
	{
		if (fieldView == null)
			fieldView = new FieldView();

		return fieldView;
	}

	public void load()
	{
		lines = new ArrayList<FieldLine>();
		for (int i = 0; i < tam; i++)
			lines.add(new FieldLine(i));
	}

	private FieldView()
	{
	}

	public boolean isFieldUpOver()
	{
		int mapTam = fieldView.tam * Tile.size;
		return fieldView.position.getY() + mapTam >= mapTam - Tile.size;
	}

	public boolean isFieldDownOver()
	{
		return this.getPosition().getY() <= -(Tile.size * 3);
	}

	public void render(Graphics g)
	{
		FieldView fieldView = FieldView.getInstance();
		Position pos = new Position(fieldView.getPosition().getX(), fieldView.getPosition().getY());

		for (FieldLine line : FieldView.getInstance().getLines())
		{
			for (Tile tile : line.getTiles())
			{
				int x = pos.getX();
				int y = pos.getY();
				tile.setPixelPos(new Position(x, y));
				g.drawImage(tile.image, x, y, null);
				pos.setX(x + 32);

				// g.setColor(Color.BLACK);
				// g.drawString(tile.code(), x, y + 12);
			}
			pos.setX(FieldView.getInstance().getPosition().getX());
			pos.setY(pos.getY() + 32);
		}

		// Console
		// drawConsole(g);

		g.drawString(Controller.player.getPixelPos().toString(), 0, 60);
	}

	private void drawConsole(Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 400, 200);

		g.setColor(Color.WHITE);
		g.drawString(Controller.player.getPosition().toString(), 0, 15);
		g.drawString(Controller.zombieTest.getPosition().toString(), 0, 30);
		if (Controller.player.getDirection() != null)
			g.drawString(Controller.player.getDirection().toString(), 0, 45);
	}

	public boolean isFieldRightOver()
	{
		// TODO kaiser usar conf para tamanhos aqui
		return this.getPosition().getX() <= -(Tile.size * 3);
	}

	public boolean isFieldLeftOver()
	{

		int mapTam = this.getLines().get(0).getTam() * Tile.size;
		return fieldView.position.getX() + mapTam >= mapTam - Tile.size;
	}

	public Tile getTileForPixel(Position pos)
	{
		int x = pos.getX();
		int y = pos.getY();
		
		int difx = Math.abs(x - this.getPosition().getX());
		int dify = Math.abs(y - this.getPosition().getY());
		
		x = difx / Tile.size;
		y = dify / Tile.size;
		
		return FieldView.getInstance().getLines().get(y).getTiles().get(x);
	}

	public List<FieldLine> getLines()
	{
		return lines;
	}

	public void setLines(List<FieldLine> lines)
	{
		this.lines = lines;
	}

	public Position getPosition()
	{
		return position;
	}

	public void setPosition(Position position)
	{
		this.position = position;
	}
}
