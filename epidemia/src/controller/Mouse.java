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

	
	public void mouseClicked(MouseEvent e)
	{
	}

	
	public void mouseEntered(MouseEvent e)
	{
	}

	
	public void mouseExited(MouseEvent e)
	{
	}

	
	public void mousePressed(MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON1)
			Controller.shot(new Position(e.getX(), e.getY()));
	}

	
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
