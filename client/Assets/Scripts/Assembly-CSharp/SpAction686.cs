using System.Collections;
using System.Diagnostics;

public class SpAction686 : SpActionBase
{
	private int m_auraValue;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	public override void OnApplyAuraTriggerUnit(UserUnit triggerUnit)
	{
	}

	protected override void OnDeactive()
	{
	}

	public void OnTriggerUnitStatChanged(UserUnit triggerUnit)
	{
	}

	[DebuggerHidden]
	public override IEnumerator OnDoCoroutine()
	{
		return null;
	}
}
