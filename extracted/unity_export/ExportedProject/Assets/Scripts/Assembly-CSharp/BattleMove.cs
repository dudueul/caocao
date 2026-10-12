using System.Collections;
using System.Diagnostics;
using Caocao;
using UnityEngine;

public class BattleMove : Move
{
	private static float ms_moveStepTime;

	private const float ms_moveStepDefaultTime = 0.1f;

	private UnitFSMQueue m_unitFSMQueue;

	private MoveParam[] m_moveParams;

	private IntVector2 m_befGridPos;

	private UnitDirection m_befDirection;

	private const float gap = 2.1599998f;

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

	private static bool NeedToMoveCamera(IntVector2 toGridPos, Camera camera)
	{
		return false;
	}

	private void MoveCamera(Vector3 toWorldPos, Camera cam)
	{
	}

	private float GetMoveSpeedFactorBySide()
	{
		return 0f;
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
