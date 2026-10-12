using System.Collections;
using System.Diagnostics;

public class DuelCmdEndBattle : DuelCmdBase
{
	private float cmdDuration;

	public override void SetData(object[] args)
	{
	}

	[DebuggerHidden]
	public override IEnumerator ProcessCommand()
	{
		return null;
	}

	public override void OnCommandStarted()
	{
	}

	public override void OnCommandFinished()
	{
	}
}
