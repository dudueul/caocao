using System.Collections.Generic;
using UnityEngine;
using antilunchbox;

public class PlaySoundBridge : MonoBehaviour, IWeakResourceRef
{
	public AudioClip SFX;

	public bool playOnAwake;

	public bool loop;

	public float delay;

	public float volume;

	public float pitch;

	public SFXType type;

	[SerializeField]
	public string mSoundClipPath;

	private void OnEnable()
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
