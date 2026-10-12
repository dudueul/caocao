using System.Collections.Generic;
using System.Runtime.InteropServices;
using UnityEngine;

public class TypewriterEffect : MonoBehaviour
{
	[StructLayout((LayoutKind)0, Size = 24)]
	private struct FadeEntry
	{
		public int index;

		public string text;

		public float alpha;
	}

	public static TypewriterEffect current;

	public int charsPerSecond;

	public float fadeInTime;

	public float delayOnPeriod;

	public float delayOnNewLine;

	public UIScrollView scrollView;

	public bool keepFullDimensions;

	public List<EventDelegate> onFinished;

	private UILabel mLabel;

	private string mFullText;

	private int mCurrentOffset;

	private float mNextChar;

	private bool mReset;

	private bool mActive;

	private BetterList<FadeEntry> mFade;

	public bool isActive
	{
		get
		{
			return false;
		}
	}

	public void ResetToBeginning()
	{
	}

	public void Finish()
	{
	}

	private void OnEnable()
	{
	}

	private void Update()
	{
	}
}
