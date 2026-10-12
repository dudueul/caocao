using System.Collections.Generic;
using UnityEngine;

public class SFXPoolInfo
{
	public int currentIndexInPool;

	public int prepoolAmount;

	public float baseVolume;

	public float volumeVariation;

	public float pitchVariation;

	public List<float> timesOfDeath;

	public List<GameObject> ownedAudioClipPool;

	public SFXPoolInfo(int index, int minAmount, List<float> times, List<GameObject> pool, float baseVol = 1f, float volVar = 0f, float pitchVar = 0f)
	{
	}
}
