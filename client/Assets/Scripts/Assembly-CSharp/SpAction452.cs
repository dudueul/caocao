using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

public class SpAction452 : SpActionBase
{
	public const float ART_DMG_RED_PCT_VALUE = 70f;

	private const int MaxApplyCnt = 2;

	private List<List<ulong>> m_applyUnitSrls;

	private List<UnitData> m_turnOrderUnitDatas;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	[DebuggerHidden]
	public override IEnumerator OnDoCoroutine()
	{
		return null;
	}

	private bool CheckApplyUnit(ulong unitSrl)
	{
		return false;
	}
}
