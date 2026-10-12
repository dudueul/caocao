using System.Collections;
using System.Diagnostics;

public class DuelActionTired : DuelActionBase
{
	private const string m_clipName = "Groggy";

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

	private void AnimationCompleted(tk2dSpriteAnimator caller, tk2dSpriteAnimationClip currentClip)
	{
	}

	private void ActionCompleted()
	{
	}
}
