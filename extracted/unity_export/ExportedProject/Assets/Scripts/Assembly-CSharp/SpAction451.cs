using System.Collections;
using System.Diagnostics;

public class SpAction451 : SpActionBase
{
	private UnitData m_lastHitter;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public UnitData LastHitter
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public void SetLastHitter(UnitData unitData)
	{
	}

	[DebuggerHidden]
	public override IEnumerator OnDoCoroutine()
	{
		return null;
	}
}
