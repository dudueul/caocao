using System;
using UnityEngine;

public class tk2dUIProgressBar : MonoBehaviour
{
	private Action OnProgressComplete__BackingField;

	public Transform scalableBar;

	public tk2dClippedSprite clippedSpriteBar;

	public tk2dSlicedSprite slicedSpriteBar;

	private bool initializedSlicedSpriteDimensions;

	private Vector2 emptySlicedSpriteDimensions;

	private Vector2 fullSlicedSpriteDimensions;

	private Vector2 currentDimensions;

	[SerializeField]
	private float percent;

	private bool isProgressComplete;

	public GameObject sendMessageTarget;

	public string SendMessageOnProgressCompleteMethodName;

	public float Value
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public event Action OnProgressComplete
	{
		add
		{
		}
		remove
		{
		}
	}

	private void Start()
	{
	}

	private void InitializeSlicedSpriteDimensions()
	{
	}
}
