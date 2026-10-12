using System.Collections;
using UnityEngine;

public abstract class EnergyBarBase : MonoBehaviour
{
	public enum AnimationMode
	{
		Effect = 0,
		Expect = 1
	}

	protected PlayMakerFSM fsm;

	protected Transform current;

	protected Transform delta;

	protected UnitData unitData;

	protected static string expectDamageAnimFsmEventName;

	protected static string expectHealAnimFsmEventName;

	protected static string damageAnimFsmEventName;

	protected static string healAnimFsmEventName;

	public abstract void ShowExpect(float deltaPct);

	public abstract void AddEffect(int from, int to);

	public abstract IEnumerator Animate();

	public abstract void OnAnimCompleted();

	public abstract void OnComplete();

	public abstract EnumData.UnitResType GetBarResType();
}
