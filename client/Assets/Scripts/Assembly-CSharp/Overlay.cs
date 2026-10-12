using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class Overlay : MonoBehaviour
{
	public static Overlay instance;

	public GameObject screenFadePanel;

	public UITexture fadeTexture;

	public GameObject topDeco;

	public GameObject bottomDeco;

	public GameObject leftDeco;

	public GameObject rightDeco;

	public PopupGamePause gamePause;

	public GameObject debugPanel;

	public UILabel debugLabel;

	public bool showHeapMemory;

	private Color m_baseColor;

	private bool m_isFadeCompleted;

	private void Awake()
	{
	}

	private void Start()
	{
	}

	private void OnDestroy()
	{
	}

	private void OnLoadingStart()
	{
	}

	private void OnLoadingEnd()
	{
	}

	private void ScreenDecoChangeStatus(bool active)
	{
	}

	public void ShowGamePause()
	{
	}

	public void CloseGamePause()
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowHeapMemory()
	{
		return null;
	}

	public GameObject InstantiateCenterPopupText()
	{
		return null;
	}

	public GameObject InstantiateCenterMiddleAutoPopupText()
	{
		return null;
	}

	public GameObject InstantiateCenterPopupTextNoInput()
	{
		return null;
	}

	public void Fade(Color color, float startAlpha, float endAlpha, float duration, bool jumpToStartAlpha)
	{
	}

	public void Fade(float startAlpha, float endAlpha, float duration, bool jumpToStartAlpha)
	{
	}

	public void FadeTo(Color color, float endAlpha, float duration)
	{
	}

	public void FadeUp(float duration)
	{
	}

	public void FadeDown(float duration)
	{
	}

	public bool IsFadeInState()
	{
		return false;
	}

	public bool IsFadeOutState()
	{
		return false;
	}

	public bool IsFadingState()
	{
		return false;
	}

	public void FadeOut(float duration, Color color)
	{
	}

	public void FadeIn(float duration)
	{
	}

	private void OnFadeFinished()
	{
	}

	[DebuggerHidden]
	public IEnumerator WaitFadeFinish()
	{
		return null;
	}
}
