using System.Collections;
using System.Diagnostics;
using Caocao;
using UnityEngine;

internal class DuelFuncTranslateImpl : MonoBehaviour
{
	public enum MoveSide
	{
		Front = 0,
		Back = 1,
		Left = 2,
		Right = 3
	}

	private float m_playTime;

	private float m_delay;

	private int m_moveTileCount;

	private MoveSide m_moveSide;

	public void StartAnimation(float playTime, float delay, int moveTileCount, MoveSide dir)
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate(float playTime, float delay, int moveTileCount, MoveSide dir)
	{
		return null;
	}

	private void OnUnitTranslateStarted()
	{
	}

	private void OnUnitTranslateCompleted()
	{
	}

	private UnitDirection GetUnitMoveDir(UnitDirection curUnitForward)
	{
		return UnitDirection.back;
	}

	private IntVector2 GetMoveDist(IntVector2 curUnitGridPos, UnitDirection dir)
	{
		return default(IntVector2);
	}
}
