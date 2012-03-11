package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Clock
{
	private static Long totalTime = 0l;

	private static Integer seconds = 0;
	private static Integer minutes = 0;
	private static Integer hours = 0;
	
	private static long lastTime = 0;

	public static void calculate()
	{
		if(lastTime == 0)
			lastTime =  System.currentTimeMillis();
		
		long timePassed = System.currentTimeMillis() - lastTime;
		
		totalTime += timePassed;
		
		while (totalTime > 999)
		{
			seconds++;
			totalTime -= 1000;
			if (seconds > 59)
			{
				minutes++;
				seconds -= 60;
				if (minutes > 59)
				{
					minutes -= 60;
					hours++;
				}
			}
		}
		lastTime = System.currentTimeMillis();
	}
	
	public static void render(Graphics g)
	{
		String clock = "";
		clock += formatNumber(Clock.getHours());
		clock += ":";
		clock += formatNumber(Clock.getMinutes());
		clock += ":";
		clock += formatNumber(Clock.getSeconds());

		g.setColor(Color.BLUE);

		g.setFont(new Font("Courier", Font.BOLD, 20));
		g.drawString(clock, 500, 50);
	}
	
	private static String formatNumber(Integer number)
	{
		if (number.toString().length() > 1)
			return number.toString();
		else
			return "0" + number.toString();
	}


	public static Integer getSeconds()
	{
		return seconds;
	}
	
	public static Integer getMinutes()
	{
		return minutes;
	}
	
	public static Integer getHours()
	{
		return hours;
	}
}
