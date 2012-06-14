package br.com.cartec.epidemia.window;

import java.awt.Dimension;

import javax.swing.JFrame;

import br.com.cartec.epidemia.controller.GameCore;
import br.com.cartec.epidemia.controller.Keyboard;
import br.com.cartec.epidemia.controller.Mouse;


public class MainWindow extends JFrame
{
	private static final long serialVersionUID = 6577115179726472971L;
	public static GamePanel panel;
	private static Runnable gameCore = new GameCore();

	public MainWindow()
	{
		super("Survive 1.0 - SNAPSHOT");
		this.addKeyListener(new Keyboard());
		this.addMouseListener(new Mouse());
	}

	public static void main(String[] args)
	{
		configWindow();
		new Thread(gameCore).start();
	}

	private static void configWindow()
	{
		MainWindow mWindow = new MainWindow();
		mWindow.setSize(new Dimension(1024, 640));
		panel = new GamePanel();
		mWindow.getContentPane().add(panel);
		mWindow.setVisible(true);
		mWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
