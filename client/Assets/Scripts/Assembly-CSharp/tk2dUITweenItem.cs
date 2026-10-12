using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class tk2dUITweenItem : tk2dUIBaseItemControl
{
	private Vector3 onUpScale;

	public Vector3 onDownScale;

	public float tweenDuration;

	public bool canButtonBeHeldDown;

	[SerializeField]
	private bool useOnReleaseInsteadOfOnUp;

	private bool internalTweenInProgress;

	private Vector3 tweenTargetScale;

	private Vector3 tweenStartingScale;

	private float tweenTimeElapsed;

	public bool UseOnReleaseInsteadOfOnUp
	{
		get
		{
			return false;
		}
	}

	private void Awake()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void ButtonDown()
	{
	}

	private void ButtonUp()
	{
	}

	[DebuggerHidden]
	private IEnumerator ScaleTween()
	{
		return null;
	}

	public void InternalSetUseOnReleaseInsteadOfOnUp(bool state)
	{
	}
}
