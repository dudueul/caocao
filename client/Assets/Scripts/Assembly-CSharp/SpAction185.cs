using System.Collections;
using System.Diagnostics;

public class SpAction185 : SpActionBase
{
	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override float OnGetCalculatedFloatByUnitData(UnitData unitData, float value)
	{
		return 0f;
	}

	[DebuggerHidden]
	public override IEnumerator OnDoCoroutine()
	{
		return null;
	}
}
