using System.Collections;
using System.Diagnostics;
using BattleRecorder;
using UnityEngine;

public class EffectDragonAnger : EffectBase
{
	private EnumData.Skills m_skillId;

	private const string MissEffectNameTemplate = "02";

	public EffectDragonAnger(UnitObject triggerUnit, UnitObject targetUnit, EnumData.Skills skillId)
		: base(null, null)
	{
	}

	public override void _Apply()
	{
	}

	public override void _Revert()
	{
	}

	[DebuggerHidden]
	public IEnumerator ShowNormalMagicEffect()
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator ShowMissMagicEffect()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowMagicEffect(string effectPrefabPoolName, GameObject effectPrefabObj)
	{
		return null;
	}

	private GameObject GetMissMagicEffect()
	{
		return null;
	}

	public override bool NeedToRecord()
	{
		return false;
	}

	public static EffectDragonAnger Read(OptimizedBinaryReader r)
	{
		return null;
	}
}
