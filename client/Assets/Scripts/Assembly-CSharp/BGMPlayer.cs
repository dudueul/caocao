using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class BGMPlayer : Singleton<BGMPlayer>
{
	private bool m_mute;

	private float m_volume;

	private const float fadeTime = 2f;

	private AudioSource m_playingBgm;

	public AudioSource playingBgm
	{
		get
		{
			return null;
		}
	}

	private AudioSource CreateAudioSource()
	{
		return null;
	}

	private void DestroyAudioSource(AudioSource audioSource)
	{
	}

	public void MuteMusic(bool mute)
	{
	}

	public void SetVolume(float volume)
	{
	}

	public void Play(AudioClip audioClip, bool loop)
	{
	}

	[DebuggerHidden]
	private IEnumerator FadeIn(AudioSource audioSource)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator FadeOut(AudioSource audioSource)
	{
		return null;
	}

	public void Stop()
	{
	}

	public void StopImmediately()
	{
	}
}
