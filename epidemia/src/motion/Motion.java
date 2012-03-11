package motion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;

import metrics.Position;

public class Motion
{
	public static Map<String, List<DefaultMutableTreeNode>> tileListeners = new HashMap<String, List<DefaultMutableTreeNode>>();

	protected void start()
	{
		PositionB p = new PositionB(10, 10, 0);
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(p);
		montarTree(node, 0, -1);
		tileListeners.get(PositionB.getKey(15, 15)).get(0).getPath();

		for (Entry<String, List<DefaultMutableTreeNode>> listenerList : tileListeners.entrySet())
			Collections.sort(listenerList.getValue(), new RouteComparator());
	}

	private void montarTree(DefaultMutableTreeNode oneNode, int cx, int cy)
	{
		int x = getPositionFromNode(oneNode).getX();
		int y = getPositionFromNode(oneNode).getY();

		float w = getPositionFromNode(oneNode).getWeight();

		if (w >= 5)
			return;

		float w1 = w + 1;
		float w2 = w + 1;
		float w3 = w + 1;
		float w4 = w + 1;
		float w5 = w + 1;
		float w6 = w + 1;
		float w7 = w + 1;
		float w8 = w + 1;

		if (cx != 0 && cy != 1)
			w1 += 0.1;
		if (cx != -1 && cy != 1)
			w2 += 0.1;
		if (cx != -1 && cy != 0)
			w3 += 0.1;
		if (cx != -1 && cy != -1)
			w4 += 0.1;
		if (cx != 0 && cy != -1)
			w5 += 0.1;
		if (cx != 1 && cy != -1)
			w6 += 0.1;
		if (cx != 1 && cy != 0)
			w7 += 0.1;
		if (cx != 1 && cy != 1)
			w8 += 0.1;

		List<PositionB> list = new ArrayList<PositionB>();
		list.add(new PositionB(x, y + 1, w1));
		list.add(new PositionB(x - 1, y + 1, w2));
		list.add(new PositionB(x - 1, y, w3));
		list.add(new PositionB(x - 1, y - 1, w4));
		list.add(new PositionB(x, y - 1, w5));
		list.add(new PositionB(x + 1, y - 1, w6));
		list.add(new PositionB(x + 1, y, w7));
		list.add(new PositionB(x + 1, y + 1, w8));

		int i = 0;
		for (PositionB newPoint : list)
		{
			Integer cx2 = 0;
			Integer cy2 = 0;
			calcDif(i, cx2, cy2);

			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newPoint);
			oneNode.add(newNode);
			addOnMap(newPoint, newNode);
			montarTree(newNode, cx2, cy2);
		}
	}

	private void calcDif(int i, Integer cx2, Integer cy2)
	{
		if (i == 0)
		{
			cx2 = 0;
			cy2 = 1;
		}
		if (i == 1)
		{
			cx2 = -1;
			cy2 = 1;
		}
		if (i == 2)
		{
			cx2 = -1;
			cy2 = 0;
		}
		if (i == 3)
		{
			cx2 = -1;
			cy2 = -1;
		}
		if (i == 4)
		{
			cx2 = 0;
			cy2 = -1;
		}
		if (i == 5)
		{
			cx2 = 1;
			cy2 = -1;
		}
		if (i == 6)
		{
			cx2 = 1;
			cy2 = 0;
		}
		if (i == 7)
		{
			cx2 = 1;
			cy2 = 1;
		}
	}

	public static PositionB getPositionFromNode(DefaultMutableTreeNode node)
	{
		return ((PositionB) node.getUserObject());
	}

	private void addOnMap(PositionB newPoint, DefaultMutableTreeNode newNode)
	{
		int newX = newPoint.getX();
		int newY = newPoint.getY();

		if (tileListeners.get(PositionB.getKey(newX, newY)) == null)
		{
			List<DefaultMutableTreeNode> nodeList = new ArrayList<DefaultMutableTreeNode>();
			nodeList.add(newNode);
			tileListeners.put(PositionB.getKey(newX, newY), nodeList);
		}
		else
		{
			tileListeners.get(PositionB.getKey(newX, newY)).add(newNode);
		}
	}
}

class PositionB extends Position
{
	private int direction;
	private float weight;
	static int qtd;

	public PositionB(int x, int y, float w)
	{
		super(x, y);
		this.weight = w;
		qtd++;
	}

	public int getDirection()
	{
		return direction;
	}

	public void setDirection(int direction)
	{
		this.direction = direction;
	}

	public static String getKey(int x, int y)
	{
		return x + "x" + y;
	}

	public String toString()
	{
		return getX() + "x" + getY() + " :" + weight;
	}

	public float getWeight()
	{
		return weight;
	}

	public void setWeight(float weight)
	{
		this.weight = weight;
	}
}

class RouteComparator implements Comparator<DefaultMutableTreeNode>
{
	@Override
	public int compare(DefaultMutableTreeNode d1, DefaultMutableTreeNode d2)
	{
		Float w1 = new Float(Motion.getPositionFromNode(d1).getWeight());
		Float w2 = new Float(Motion.getPositionFromNode(d2).getWeight());

		return w1.compareTo(w2);
	}

}
