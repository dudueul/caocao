using System.Collections.Generic;
using UnityEngine;

public class CommonTriggers : ScriptableObject
{
	public List<TriggerGroup> _00_All;

	public List<TriggerGroups> _01_ByJobType;

	public List<UnitID2DamageEffectType> _02_unitidDamageEffects;

	public List<JobType2DamageEffectType> _02_jobTypeDamageEffects;

	public List<TriggerGroups> _03_ByDemageEffectType;

	public List<TriggerGroups> _04_ByUnitID;

	public List<TriggerGroups> _05_BySkinID;

	private static string ms_assetPath;

	private static CommonTriggers ms_instance;

	public static CommonTriggers instance
	{
		get
		{
			return null;
		}
	}

	public void Reset()
	{
	}

	public static void Create()
	{
	}

	public tk2dCustomTrigger GetCommonTriggers(GameObject atkUnitObj, GameObject clipTargetObject, string clipName, string triggerID, bool isDoubleAttack, bool isCritical)
	{
		return null;
	}

	private tk2dCustomTrigger GetTriggersByJobType(GameObject clipTargetObject, string clipName, string triggerID, bool isDoubleAttack, bool isCritical)
	{
		return null;
	}

	private TriggerGroups GetJobTypeGroups(GameObject atkUnitObj)
	{
		return null;
	}

	private tk2dCustomTrigger GetTriggersFromAll(string triggerID, bool isDoubleAttack, bool isCritical)
	{
		return null;
	}

	private TriggerGroups GetDamageEffectTypeGroups(EnumData.DamageEffectType damageEffectType)
	{
		return null;
	}

	private EnumData.DamageEffectType GetDamageEffectType(GameObject atkUnitObj)
	{
		return EnumData.DamageEffectType.None;
	}

	private EnumData.DamageEffectType GetDamageEffectType(int unitID, int jobType)
	{
		return EnumData.DamageEffectType.None;
	}

	private tk2dCustomTrigger GetTriggersByDamageEffectType(GameObject atkUnitObj, string clipName, string triggerID, bool isDoubleAttack, bool isCritical)
	{
		return null;
	}

	private tk2dCustomTrigger GetTriggersByUnitID(GameObject atkUnitObj, string clipName, string triggerID, bool isDoubleAttack, bool isCritical)
	{
		return null;
	}

	private tk2dCustomTrigger GetTriggersBySkinID(GameObject atkUnitObj, string clipName, string triggerID, bool isDoubleAttack, bool isCritical)
	{
		return null;
	}

	private TriggerGroups GetUnitIDGroups(GameObject atkUnitObj)
	{
		return null;
	}

	private TriggerGroups GetSkinIDGroups(GameObject atkUnitObj)
	{
		return null;
	}

	private tk2dCustomTrigger GetTriggers(List<TriggerGroup> searchingTarget, string triggerID, bool isDoubleAttack, bool isCritical)
	{
		return null;
	}
}
