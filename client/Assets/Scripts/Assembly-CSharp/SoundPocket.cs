using System.Collections.Generic;
using UnityEngine;

public class SoundPocket : MonoBehaviour
{
	public string pocketName;

	public SoundPocketType pocketType;

	public List<AudioClip> pocketClips;

	public List<string> sfxGroups;

	public List<string> clipToGroupKeys;

	public List<string> clipToGroupValues;

	public List<int> sfxPrePoolAmounts;

	public List<float> sfxBaseVolumes;

	public List<float> sfxVolumeVariations;

	public List<float> sfxPitchVariations;

	private Dictionary<string, string> clipsInGroups;

	public bool showAsGrouped;

	public List<bool> showSFXDetails;

	public int groupAddIndex;

	public int autoPrepoolAmount;

	public float autoBaseVolume;

	public float autoVolumeVariation;

	public float autoPitchVariation;

	private void Awake()
	{
	}

	public void Setup()
	{
	}

	private void SetupDictionaries()
	{
	}

	public void DestroyMe()
	{
	}
}
