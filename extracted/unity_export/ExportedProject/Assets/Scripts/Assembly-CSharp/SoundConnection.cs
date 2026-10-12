using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class SoundConnection
{
	public string level;

	public bool isCustomLevel;

	public List<AudioClip> soundsToPlay;

	public List<float> baseVolumes;

	public SoundManager.PlayMethod playMethod;

	public float minDelay;

	public float maxDelay;

	public float delay;

	public SoundConnection(string lvl, params AudioClip[] audioList)
	{
	}

	public SoundConnection(string lvl, SoundManager.PlayMethod method, params AudioClip[] audioList)
	{
	}

	public SoundConnection(string lvl, SoundManager.PlayMethod method, float delayPlay, params AudioClip[] audioList)
	{
	}

	public SoundConnection(string lvl, SoundManager.PlayMethod method, float minDelayPlay, float maxDelayPlay, params AudioClip[] audioList)
	{
	}

	public void SetToCustom()
	{
	}
}
