using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Text;
using UnityEngine;

public class DuelActionMove3Times : DuelActionBase
{
	private static StringBuilder ms_sb;

	private UnitAnimation m_unitAnimation;

	private List<MoveParam> m_itweenParams;

	public override void StartAnimation()
	{
	}

	public override void SetData(EnumData.DuelAction action, string[] args)
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate()
	{
		return null;
	}

	private void MoveStep(MoveParam parmas)
	{
	}

	private void UnitMoveStepStarted(Vector2 toWorldPos)
	{
	}

	private void UnitMoveStepCompleted(int currentStepIdx)
	{
	}

	private void UnitMoveCompleted()
	{
	}
}
