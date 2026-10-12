using System.Collections.Generic;
using UnityEngine;
using antilunchbox;

public class UIPlaySound : MonoBehaviour, IWeakResourceRef
{
	public enum Trigger
	{
		OnClick = 0,
		OnMouseOver = 1,
		OnMouseOut = 2,
		OnPress = 3,
		OnRelease = 4,
		Custom = 5,
		OnEnable = 6,
		OnDisable = 7
	}

	public AudioClip audioClip;

	public SFXType sfxType;

	public Trigger trigger;

	public float volume;

	public float pitch;

	private bool mIsOver;

	[SerializeField]
	public string mAudioClipPath;

	private bool canPlay
	{
		get
		{
			return false;
		}
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

	private void OnSelect(bool isSelected)
	{
	}

	public void Play()
	{
	}

	public void DisconnectResourceRef(List<string> adjustedPaths)
	{
	}

	void IWeakResourceRef.ConnectResourceRef()
	{
	}
}
