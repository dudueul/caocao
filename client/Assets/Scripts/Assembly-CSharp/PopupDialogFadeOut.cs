using System;
using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class PopupDialogFadeOut : MonoBehaviour
{
	public UISprite blackBg;

	private static PopupDialogFadeOut instance;

	private static TweenColor tweenColor;

	private static bool waitFadeIn;

	private static int version;

	private static int prevVersion;

	public static void FadeOut(int seconds, bool wait, Color toColor, Action callback)
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitFadeOut(int seconds, Action callback)
	{
		return null;
	}

	public static void FadeIn(int seconds, bool wait, Action callback)
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitFadeIn(int seconds, Action callback)
	{
		return null;
	}

	private void OnDestroy()
	{
	}
}
