package people.npc;

import people.Humanoid;
import controller.move.MoveNPC;

public class Zombie extends Humanoid
{
	private MoveNPC move;

	public MoveNPC getMove()
	{
		return move;
	}

	public void setMove(MoveNPC move)
	{
		this.move = move;
	}
}
