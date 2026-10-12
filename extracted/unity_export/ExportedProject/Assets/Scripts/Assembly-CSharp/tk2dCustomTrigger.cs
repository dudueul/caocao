using System;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class tk2dCustomTrigger
{
	[HideInInspector]
	public string displayName;

	public string commonTriggerID;

	[NonSerialized]
	private bool showCreatureTriggers;

	public bool Hit;

	public bool DirectionHit;

	[NonSerialized]
	private bool showEffectList;

	[NonSerialized]
	private bool showSoundOverride;

	[NonSerialized]
	private bool showSoundAdded;

	public tk2dEffect[] EffectList;

	public tk2dSpriteColor SpriteColor;

	[NonSerialized]
	private bool showWroldTriggers;

	public tk2dCameraShake CameraShake;

	public tk2dCameraMove CameraMove;

	public tk2dCameraZoom CameraZoom;

	public tk2dTimeControl TimeControl;

	public List<int> AudioIDs;

	public List<int> AudioIDsAdded;

	private static string ms_directionStr;

	public void Reset()
	{
	}

	public void IgnoreEffectList()
	{
	}

	public void IgnoreSound()
	{
	}

	public static tk2dCustomTrigger operator +(tk2dCustomTrigger left, tk2dCustomTrigger right)
	{
		return null;
	}

	public static void OverrideTrigger(tk2dCustomTrigger overridee, tk2dCustomTrigger overrider)
	{
	}

	public void OverrideValue(tk2dCustomTrigger source)
	{
	}

	public static string GetOverridenDamageEffect(string source, string effectPostfix, string triggerID)
	{
		return null;
	}

	public void CopyFrom(tk2dCustomTrigger source)
	{
	}
}
