using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

public class ResultPerUnit
{
	private EnumData.Skills m_skillID;

	private bool m_isDirectResult;

	private UnitData m_triggerUnitData;

	private UnitData m_targetUnitData;

	private bool m_isAttackSucceeded;

	private bool m_isCriticalOccured;

	private int m_attackSuccessRateChangeCount;

	private SpActionBase m_spAction;

	private float m_attackSuccessRate;

	private float m_criticalOccurrenceRate;

	private List<EffectBase> m_targetEffects;

	private List<EffectBase> m_triggerEffects;

	public EnumData.Skills skillID
	{
		get
		{
			return EnumData.Skills.NONE;
		}
	}

	public bool isDirectResult
	{
		get
		{
			return false;
		}
	}

	public UnitData triggerUnitData
	{
		get
		{
			return null;
		}
	}

	public UnitData targetUnitData
	{
		get
		{
			return null;
		}
	}

	public bool isAttackSucceeded
	{
		get
		{
			return false;
		}
	}

	public bool isCriticalOccured
	{
		get
		{
			return false;
		}
	}

	public bool isAttackSuccessRateUpdated
	{
		get
		{
			return false;
		}
	}

	public SpActionBase spAction
	{
		get
		{
			return null;
		}
	}

	public float attackSuccessRate
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float criticalOccurrenceRate
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public int rawDamage { get; set; }

	public List<EffectBase> targetEffects
	{
		get
		{
			return null;
		}
	}

	public List<EffectBase> triggerEffects
	{
		get
		{
			return null;
		}
	}

	public EffectAutoUseItem autoUseitemEffect { get; set; }

	public ResultPerUnit(UnitData triggerUnitData, UnitData targetUnitData, bool _isDriectResult)
	{
	}

	public ResultPerUnit(UnitData triggerUnitData, UnitData targetUnitData, EnumData.Skills _skillID, bool _isDriectResult)
	{
	}

	public void SetSkillID(EnumData.Skills skillID)
	{
	}

	public void SetSpAction(SpActionBase spAction)
	{
	}

	public void AddEffects(ResultPerUnit result)
	{
	}

	[DebuggerHidden]
	public IEnumerator ShowNormalMagicEffect()
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator ShowChainMagicEffect(UnitData nextTargetUnitData, bool isStartChain = false)
	{
		return null;
	}
}
