package br.com.cartec.epidemia.field;

import java.util.ArrayList;
import java.util.List;

public class FieldLine
{
	private List<Tile> tiles;
	private int tam = 38;
	
	public FieldLine(int y)
	{
		super();
		tiles = new ArrayList<Tile>();
		for(int i=0; i<tam; i++)
			tiles.add(new Tile(i, y));
	}
	public List<Tile> getTiles()
	{
		return tiles;
	}

	public void setTiles(List<Tile> tiles)
	{
		this.tiles = tiles;
	}
	public int getTam()
	{
		return tam;
	}
	public void setTam(int tam)
	{
		this.tam = tam;
	}
	
	
}
