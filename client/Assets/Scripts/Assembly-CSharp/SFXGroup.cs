using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class SFXGroup
{
	public string groupName;

	public int specificCapAmount;

	public List<AudioClip> clips;

	public bool independentVolume;

	public bool independentPitch;

	public float volume;

	public float pitch;

	public SFXGroup(string name, int capAmount, params AudioClip[] audioclips)
	{
	}

	public SFXGroup(string name, params AudioClip[] audioclips)
	{
	}
}
