using System.Collections;
using System.Diagnostics;

public class DuelFuncTranslate : DuelActionBase
{
	private float m_delay;

	private int m_moveTileCount;

	private DuelFuncTranslateImpl.MoveSide m_moveSide;

	private bool m_needDust;

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
