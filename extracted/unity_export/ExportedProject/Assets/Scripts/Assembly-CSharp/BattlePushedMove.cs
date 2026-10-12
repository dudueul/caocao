using System.Collections;
using System.Diagnostics;
using Caocao;

public class BattlePushedMove : Move
{
	private static float ms_moveStepTime;

	private UnitFSMQueue m_unitFSMQueue;

	private MoveParam[] m_moveParams;

	private IntVector2 m_befGridPos;

	private UnitDirection m_befDirection;

	private int m_currentOnGoingIdx;

	private MoveParam m_currentParams;

	public void StartAnimation(IntVector2 toGridPos)
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate(IntVector2 toGridPos)
	{
		return null;
	}

	private void MoveStep(int start)
	{
	}

	private void MoveStep(MoveParam startParmas, MoveParam endParams)
	{
	}

	private void UnitMoveStepStarted(MoveParam parameters)
	{
	}

	private void UnitMoveStepUpdated(float value)
	{
	}

	private void UnitMoveStepCompleted(MoveParam parameters)
	{
	}

	private void UnitMoveCompleted()
	{
	}
}
