using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using BattlePlay;
using UnityEngine;

public class UnitRelationMan : MonoBehaviour
{
	private class PassiveData
	{
		public PassiveList passiveList;

		public EnumData.UnitRelationPassiveTriggerType passiveTriggerType;

		private List<UserUnit> units;

		private int needUnitNum;

		private bool isBuff;

		public bool activated;

		private bool[] unitActivated;

		private bool[] adjacentMark;

		private SpActionBase[] spActions;

		public bool needUpdate
		{
			get
			{
				return false;
			}
		}

		private int passiveId
		{
			get
			{
				return 0;
			}
		}

		private int passiveValue
		{
			get
			{
				return 0;
			}
		}

		private int triggerRateValue
		{
			get
			{
				return 0;
			}
		}

		private int conditionVal1
		{
			get
			{
				return 0;
			}
		}

		private int conditionVal2
		{
			get
			{
				return 0;
			}
		}

		public PassiveData(PassiveList passiveList, EnumData.UnitRelationPassiveTriggerType passiveTriggerType, List<UserUnit> units, int needUnitNum, bool isBuff)
		{
		}

		public void UpdateActivating(ControlFlow.statusType status, UnitRelationMan relationMan)
		{
		}

		private void ActivePassive(UnitRelationMan relationMan)
		{
		}

		private void ActivePassiveUnit(int index, UnitRelationMan relationMan)
		{
		}

		private void DeactivePassiveUnit(int index)
		{
		}

		public void Reset()
		{
		}
	}

	private class RelationData
	{
		private List<PassiveData> m_passiveData;

		private bool holdedUpdate;

		public void AddPassive(int passiveListId, EnumData.UnitRelationPassiveTriggerType triggerType, List<UserUnit> units, int needUnitNum, bool isBuff)
		{
		}

		public void UpdatePassiveActivating(ControlFlow.statusType status, UnitRelationMan relationMan)
		{
		}

		public void Reset()
		{
		}
	}

	private class EffectInfo
	{
		public bool playing;

		public bool[] playedMarker;
	}

	private const string RELATION_BUFF_EFFECT_PREFAB = "Fx_Relation_Buff";

	private const string RELATION_DEBUFF_EFFECT_PREFAB = "Fx_Relation_Debuff";

	private Dictionary<ulong, List<RelationData>> m_userRelations;

	private Dictionary<UnitData, EffectInfo> m_effectPlayedUnits;

	private Dictionary<UnitData, Tuple<EnumData.UnitRelationPassiveTriggerType, bool>> effectStorage;

	private void Start()
	{
	}

	private void OnBattleStart()
	{
	}

	private void OnActionReverted()
	{
	}

	private void SetUserUnitRelation(BattleUser battleUser)
	{
	}

	private void SetUserUnitRelation(ulong uid, List<int> relationList)
	{
	}

	private void SetUserUnitRelation(IUserDataBase userData)
	{
	}

	private void OnStatusChanged(ControlFlow.statusType status)
	{
	}

	private void OnBattleEnd()
	{
	}

	public void AddRelationEffect(UnitData unit, EnumData.UnitRelationPassiveTriggerType passiveType, bool isBuff)
	{
	}

	private void Update()
	{
	}

	private void PlayRelationEffect(UnitData unit, EnumData.UnitRelationPassiveTriggerType passiveType, bool isBuff)
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitEndEffect(EffectInfo effectInfo, GameObject fxObj)
	{
		return null;
	}

	private void OnDestroy()
	{
	}
}
