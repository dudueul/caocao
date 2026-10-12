using System.Collections;
using System.Diagnostics;

public class DuelFuncFlash : DuelActionBase
{
	private bool m_turnOn;

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

	private void ActionCompleted()
	{
	}
}
