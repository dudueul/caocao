using System.Collections;
using System.Diagnostics;

public class DuelFuncVanish : DuelActionBase
{
	private bool m_doVanish;

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
