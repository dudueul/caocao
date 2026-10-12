using UnityEngine;

public class BattleMapFade : MonoBehaviour
{
	public enum applyTarget
	{
		All = 0,
		ExceptUnits = 1
	}

	public Color From;

	public Color To;

	public float Duration;

	public float Delay;

	public bool IgnoreTimescale;

	public applyTarget targetType;

	private void OnEnable()
	{
	}
}
