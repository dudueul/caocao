using System.Collections;
using System.Diagnostics;

public class SpAction480 : SpActionBase
{
	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override bool OnGetCalculatedBoolResult(bool isSimulate, UnitData[] applyTargets)
	{
		return false;
	}

	[DebuggerHidden]
	public override IEnumerator OnDoCoroutine()
	{
		return null;
	}
}
