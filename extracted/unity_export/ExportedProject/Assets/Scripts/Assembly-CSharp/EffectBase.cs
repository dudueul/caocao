using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using BattleRecorder;

public abstract class EffectBase : Action
{
	private UnitObject m_triggerUnit;

	private UnitObject m_targetUnit;

	private List<EffectBase> m_effects;

	private List<EffectBase> m_postEffects;

	protected bool m_isApplied;

	protected bool m_isCompleted;

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

	public bool isApplied
	{
		get
		{
			return false;
		}
	}

	public bool isCompleted
	{
		get
		{
			return false;
		}
	}

	public bool hasPostEffect
	{
		get
		{
			return false;
		}
	}

	protected EffectBase(UnitObject triggerUnit, UnitObject targetUnit)
	{
	}

	public virtual void Apply()
	{
	}

	public void Revert()
	{
	}

	[DebuggerHidden]
	public virtual IEnumerator Animate()
	{
		return null;
	}

	public EffectCondition PopEffectCondition()
	{
		return null;
	}

	[DebuggerHidden]
	public virtual IEnumerator ShowPostEffect()
	{
		return null;
	}

	public void AddEffect(EffectBase effect)
	{
	}

	public void AddPostEffect(EffectBase effect)
	{
	}

	public static int GetEffectOf<T>(List<EffectBase> srcEffects, out EffectBase from, out EffectBase to) where T : EffectBase
	{
		from = null;
		to = null;
		return 0;
	}

	public static int GetEffectOf<T>(List<EffectBase> srcEffects, List<EffectBase> outFiltered) where T : EffectBase
	{
		return 0;
	}

	public void GetEffect<T>(List<EffectBase> outFiltered) where T : EffectBase
	{
	}

	public void GetPostEffect<T>(List<EffectBase> outFiltered) where T : EffectBase
	{
	}

	public T GetFirstChild<T>() where T : EffectBase
	{
		return null;
	}

	public T GetFirstPostEffectChild<T>() where T : EffectBase
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerable EnumerateChildren<T>() where T : EffectBase
	{
		return null;
	}

	protected void SwapUnit()
	{
	}

	protected void WriteTriggerAndTargetObjects(OptimizedBinaryWriter w)
	{
	}

	protected IUserDataBase GetTriggerUserData()
	{
		return null;
	}
}
