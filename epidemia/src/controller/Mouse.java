package controller;

import graphics.ImageStore;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import metrics.Position;

public class Mouse implements MouseListener
{
	private static Cursor fakeCursor;
	private static BufferedImage realCursor;

	@Override
	public void mouseClicked(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON1)
			Controller.shot(new Position(e.getX(), e.getY()));
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	public static void changeArrow(Environment environment)
	{
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		fakeCursor = toolKit.createCustomCursor(ImageStore.getImage("void"), new Point(0, 0), null);

		switch (environment)
		{
			case NOTHING:
				realCursor = ImageStore.getImage("crosshair1");
				break;
		}
	}
	
	public static Cursor getFakeCursor()
	{
		return fakeCursor;
	}

	public static void setFakeCursor(Cursor fakeCursor)
	{
		Mouse.fakeCursor = fakeCursor;
	}

	public BufferedImage getRealCursor()
	{
		return realCursor;
	}

	public void setRealCursor(BufferedImage realCursor)
	{
		Mouse.realCursor = realCursor;
	}

}
