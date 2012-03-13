package controller;

import motion.MotionCalc;
import system.ErrorManager;
import window.GamePanel;
import window.MainWindow;
import field.FieldView;
import graphics.ShadowManager;

public class GameCore implements Runnable
{
	private static GamePanel gamePanel;
	private static boolean running;

	private static void startGame()
	{
		loadGame();
		while (true)
		{
			try
			{
				long inicio = System.currentTimeMillis();
				mainLoop();
				long fim = System.currentTimeMillis() - inicio;
				if (fim > 50)
					System.out.println(fim);
			}
			catch (Exception e)
			{
				ErrorManager.printAndExit(e);
			}
		}
	}

	private static void mainLoop() throws InterruptedException
	{
		long begin = System.currentTimeMillis();

		logic();

		long dif = calcDelay(begin);
		updateComponentsTimes(dif);
	}

	private static void loadGame()
	{
		try
		{
			Controller.load();
			FieldView.getInstance().load();
			ShadowManager.load(0.1f, 0.1f);
			new MotionCalc().start();
			Mouse.changeArrow(Environment.NOTHING);
			running = true;
		}
		catch (Exception e)
		{
			ErrorManager.printAndExit(e);
		}
	}

	private static void updateComponentsTimes(long dif)
	{
		Controller.updateTime(new Long(dif).intValue());
	}

	private static void logic()
	{
		GameCore.gamePanel.render();
		Controller.execute();
		Controller.verifyPixelPos(Controller.player);
		Controller.verifyPixelPos(Controller.zombieTest);
		Clock.calculate();
	}

	private static long calcDelay(long begin) throws InterruptedException
	{
		long dif = System.currentTimeMillis() - begin;
		if (dif < 1)
		{
			Thread.sleep(1);
		}
		dif = System.currentTimeMillis() - begin;
		return dif;
	}

	@Override
	public void run()
	{
		GameCore.gamePanel = MainWindow.panel;
		startGame();
	}

	public static boolean isRunning()
	{
		return running;
	}

	public static void setRunning(boolean running)
	{
		GameCore.running = running;
	}
}
