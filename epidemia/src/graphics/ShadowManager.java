package graphics;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class ShadowManager
{
	private static List<List<Shadow>> list = new ArrayList<List<Shadow>>();

	public static void load(float beginX, float beginY)
	{
		float horizMod = -0.1f;
		float vertMod = -0.1f;

		float yOld = beginY;
		for (int y = 0; y < 20; y++)
		{
			if (y == (20 / 2) )
				vertMod *= -1;

			float yMod = yOld + vertMod;

			List<Shadow> line = new ArrayList<Shadow>();
			list.add(line);

			Shadow shadowOld = null;
			for (int x = 0; x < 32; x++)
			{
				if (x == (32 / 2))
					horizMod *= -1;

				if (shadowOld == null)
					shadowOld = new Shadow(beginY, 0f);

				Shadow shadowNow = new Shadow(shadowOld.getAlphaX() + horizMod, yMod);

				shadowOld = shadowNow;
				line.add(shadowNow);
			}
			horizMod *= -1;
			if (y == 19)
				vertMod *= -1;
			
			yOld = yMod;
		}
	}

	public static void loadBU()
	{
		// float trans = -0.1f;
		// for (int y = 0; y < 20; y++)
		// {
		// List<Shadow> line = new ArrayList<Shadow>();
		// list.add(line);
		//
		// Shadow shadowOld = null;
		// for (int x = 0; x < 32; x++)
		// {
		// if (x == (32 / 2) + 1)
		// trans *= -1;
		// if (shadowOld == null)
		// shadowOld = new Shadow(2.0f);
		//
		// Shadow shadowNow = new Shadow(shadowOld.getAlpha() + trans);
		// shadowOld = shadowNow;
		// line.add(shadowNow);
		// }
		// trans *= -1;
		// }
	}

	public static void render(Graphics2D g)
	{
		int x = 0, y = 0;

		for (List<Shadow> line : list)
		{
			x = 0;
			for (Shadow shadow : line)
			{
				g.setColor(Color.BLACK);
				g.fillRect(x, y, 32, 32);
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
						getLogicAlpha(shadow.getAlpha()+1.0f)));

				// System.out.println(getLogicAlpha(shadow.getAlpha()));
				x += 32;
			}
			y += 32;
		}
	}

	private static float getLogicAlpha(float alpha)
	{
		if (alpha > 0.99)
			return 0.99f;

		if (alpha < 0.0)
			return 0.0f;

		return alpha;
	}
}
