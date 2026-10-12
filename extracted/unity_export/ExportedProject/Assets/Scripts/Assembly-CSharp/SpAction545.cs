using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

public class SpAction545 : SpActionBase
{
	private Dictionary<int, List<UnitData>> targetUnitDatas;

	private Dictionary<int, List<IntVector2>> moveDestPos;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public void AddPullTarget(int atkIdx, UnitData targetUnitData)
	{
	}

	public void AddMoveDestPos(int atkIdx, IntVector2 pos)
	{
	}

	public bool CanMoveOn(int atkIndex, IntVector2 destPos)
	{
		return false;
	}

	public override float OnGetCalculatedFloatByUnitData(UnitData targetUnitData, float value)
	{
		return 0f;
	}

	[DebuggerHidden]
	public override IEnumerator AnimatePassiveEffect(UnitData effectTargetUnit, int atkIdx, int[] triggerTypes)
	{
		return null;
	}

	protected override void OnDeactive()
	{
	}

	private void Clear(int? atkIndex = null)
	{
	}
}
