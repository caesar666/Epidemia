package br.com.cartec.epidemia.people;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import br.com.cartec.epidemia.controller.move.Move;
import br.com.cartec.epidemia.graphics.ImageStore;
import br.com.cartec.epidemia.graphics.animation.Animation;
import br.com.cartec.epidemia.metrics.Position;



public class PlayerCharacter extends Humanoid
{
	public static final Position playerPos = new Position(18, 11);
	private Animation graphic = null;
	private Move move;

	public PlayerCharacter()
	{
		List<BufferedImage> listAsh = new ArrayList<BufferedImage>();
		listAsh.add(ImageStore.getImage("ash1"));
		listAsh.add(ImageStore.getImage("ash2"));
		listAsh.add(ImageStore.getImage("ash3"));
		listAsh.add(ImageStore.getImage("ash4"));

		graphic = new Animation(listAsh, 100);
	}

	public Animation getGraphic()
	{
		return graphic;
	}

	public void setGraphic(Animation graphic)
	{
		this.graphic = graphic;
	}

	public Move getMove()
	{
		return move;
	}

	public void setMove(Move move)
	{
		this.move = move;
	}
}
