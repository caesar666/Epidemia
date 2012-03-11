package motion;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import metrics.Position;
import system.ErrorManager;
import field.FieldView;
import field.Tile;

public class MotionCalc
{
	Motion motion = new Motion();

	public void start()
	{
		motion.start();
	}

	public static Route calcRoute(Position a, Position b)
	{
		Position dotA = new Position(a.getX(), a.getY());
		Position dotB = new Position(b.getX(), b.getY());

		int eqX = dotA.getX() - 10;
		int eqY = dotA.getY() - 10;

		dotA.setX(dotA.getX() - eqX);
		dotA.setY(dotA.getY() - eqY);

		dotB.setX(dotB.getX() - eqX);
		dotB.setY(dotB.getY() - eqY);

		Position eqPos = new Position(eqX, eqY);

		int difX = dotA.getX() - dotB.getX();
		int difY = dotA.getY() - dotB.getY();

		if (difX < 0)
			difX *= -1;

		if (difY < 0)
			difY *= -1;

		if (difX > 5 || difY > 5)
			return null;

		List<DefaultMutableTreeNode> listRoutes = Motion.tileListeners.get(PositionB.getKey(
				dotB.getX(), dotB.getY()));

		if (listRoutes != null)
			for (DefaultMutableTreeNode node : listRoutes)
			{
				Route r = getPossibleRoute(node, dotB, eqPos);
				if (r != null)
					return r;
			}

		return null;
	}

	private static Route getPossibleRoute(DefaultMutableTreeNode node, Position dest, Position eqPos)
	{
		List<Position> routeList = new ArrayList<Position>();

		for (TreeNode treeNode : node.getPath())
		{
			DefaultMutableTreeNode nodeNow = ((DefaultMutableTreeNode) treeNode);

			Position p = Motion.getPositionFromNode(nodeNow);
			Position realP = new Position(p.getX() + eqPos.getX(), p.getY() + eqPos.getY());
			Tile tile = FieldView.getInstance().mapTiles.get(realP.getKey());

			if (treeNode.getParent() != null)
			{
				if (!canStep(realP, eqPos, routeList))
					return null;

				if (!tile.isFree())
					return null;
			}
			node = node.getNextNode();
			routeList.add(realP);
		}

		return new Route(routeList);
	}

	private static boolean canStep(Position now, Position eqPos, List<Position> routeList)
	{
		Position old = routeList.get(routeList.size() - 1);
		int difX = old.getX() - now.getX();
		int difY = old.getY() - now.getY();

		if (difX != 0 && difY != 0)
		{
			Position p1 = null;
			Position p2 = null;

			if (now.getX() < old.getX() && now.getY() < old.getY())
			{
				p1 = new Position(old.getX(), old.getY() - 1);
				p2 = new Position(old.getX() - 1, old.getY());
			}
			if (now.getX() > old.getX() && now.getY() < old.getY())
			{
				p1 = new Position(old.getX(), old.getY() - 1);
				p2 = new Position(old.getX() + 1, old.getY());
			}
			if (now.getX() < old.getX() && now.getY() > old.getY())
			{
				p1 = new Position(old.getX() - 1, old.getY());
				p2 = new Position(old.getX(), old.getY() + 1);
			}
			if (now.getX() > old.getX() && now.getY() > old.getY())
			{
				p1 = new Position(old.getX() + 1, old.getY());
				p2 = new Position(old.getX(), old.getY() + 1);
			}

			try	
			{

				if (!(FieldView.getInstance().mapTiles.get(p1.getKey()).isFree() && FieldView.getInstance().mapTiles.get(
						p2.getKey()).isFree()))
				{
					return false;
				}
			}
			catch (Exception e)
			{
				ErrorManager.printAndExit(e);
			}
		}

		return true;
	}
}
