using System.Collections.Generic;
using AnimationOrTween;
using UnityEngine;

[ExecuteInEditMode]
public class UIPlayAnimation : MonoBehaviour
{
	public static UIPlayAnimation current;

	public Animation target;

	public Animator animator;

	public string clipName;

	public Trigger trigger;

	public Direction playDirection;

	public bool resetOnPlay;

	public bool clearSelection;

	public EnableCondition ifDisabledOnPlay;

	public DisableCondition disableWhenFinished;

	public List<EventDelegate> onFinished;

	[HideInInspector]
	[SerializeField]
	private GameObject eventReceiver;

	[HideInInspector]
	[SerializeField]
	private string callWhenFinished;

	private bool mStarted;

	private bool mActivated;

	private bool dragHighlight;

	private bool dualState
	{
		get
		{
			return false;
		}
	}

	private void Awake()
	{
	}

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnHover(bool isOver)
	{
	}

	private void OnPress(bool isPressed)
	{
	}

	private void OnClick()
	{
	}

	private void OnDoubleClick()
	{
	}

	private void OnSelect(bool isSelected)
	{
	}

	private void OnToggle()
	{
	}

	private void OnDragOver()
	{
	}

	private void OnDragOut()
	{
	}

	private void OnDrop(GameObject go)
	{
	}

	public void Play(bool forward)
	{
	}

	public void Play(bool forward, bool onlyIfDifferent)
	{
	}

	public void PlayForward()
	{
	}

	public void PlayReverse()
	{
	}

	private void OnFinished()
	{
	}
}
