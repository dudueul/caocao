using System.Collections;
using System.Diagnostics;

public class DuelActionCast : DuelActionBase
{
	private const string m_clipName = "Attack";

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
