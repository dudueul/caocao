using System.Collections;
using System.Diagnostics;

public class SpAction442 : SpActionBase
{
	private int totalSubsideTurn;

	private SpActionIntState m_healedTurn;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	private EnumData.Conditions subsideCondition
	{
		get
		{
			return EnumData.Conditions.NONE;
		}
	}

	public int healedTurn
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public override void ResetIntState()
	{
	}

	[DebuggerHidden]
	public override IEnumerator OnDoCoroutine()
	{
		return null;
	}

	public void OnHeal(EffectBase skillEffect)
	{
	}
}
