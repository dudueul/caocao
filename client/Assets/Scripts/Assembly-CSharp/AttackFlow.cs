using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

internal class AttackFlow : MonoBehaviour
{
	private AttackFlow m_parent;

	private UnitData m_triggerAttackUnitData;

	private bool m_isPincer;

	private UnitData m_realAttackUnitData;

	private UnitData m_realTargetUnitData;

	private UnitData m_attackUnitData;

	private UnitData m_targetUnitData;

	private UnitController m_attackUnitCtrl;

	private UnitController m_targetUnitCtrl;

	private UnitData m_protectedUnitData;

	private bool m_isGuarded;

	private EnumData.AttackType m_startAttackType;

	private AttackProcess m_currentAttackProcess;

	private Attack m_currentAttack;

	private List<AttackProcess> m_cachedResults;

	private static List<UnitData> ms_assultedUnitDatas;

	private static List<UnitStateGaugeBar> stateGaugeBars;

	private bool targetDamageTaken;

	private GameObject m_left;

	private GameObject m_right;

	private bool isDamageAnimationCompleted;

	private bool isPanelAnimationCompleted;

	public UnitData triggerAttackUnitData
	{
		get
		{
			return null;
		}
	}

	private AttackFlow()
	{
	}

	public static AttackFlow CreateAttackFlow(UnitData triggerUnitData, UnitData targetUnitData, EnumData.AttackType attackType, bool isPincer, AttackFlow parent)
	{
		return null;
	}

	[DebuggerHidden]
	private static IEnumerator Flow(float preDelay, UnitData triggerUnitData, UnitData targetUnitData, EnumData.AttackType attackType, bool isPincer, AttackFlow parent)
	{
		return null;
	}

	private void OnDestroy()
	{
	}

	private void SetAttackFlowData(UnitData triggerUnitData, UnitData targetUnitData, EnumData.AttackType attackType, bool isPincer)
	{
	}

	public void Excute()
	{
	}

	private void ExchangeSide()
	{
	}

	[DebuggerHidden]
	private IEnumerator Flow(bool mainCall = false)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator Attack(UnitData triggerUnitData, UnitData targetUnitData, EnumData.AttackType attackType)
	{
		return null;
	}

	private void OnAnimationEventTriggered(tk2dSpriteAnimator animator, tk2dSpriteAnimationClip clip, int currentFrameIdx)
	{
	}

	[DebuggerHidden]
	private IEnumerator StartDamageAnimation()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator StartPanelAnimation()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator Pincer(UnitData triggerUnitData, UnitData targetUnitData, List<UnitData> pincerUnits)
	{
		return null;
	}

	private List<UnitData> GetPincerUnits(UnitData triggerUnitData, UnitData targetUnitData)
	{
		return null;
	}

	private List<UnitData> GetSupportAtkUnits()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator SupportAttack(UnitData attackUnitData, UnitData targetUnitData, List<UnitData> pincerUnits, List<UnitData> supportAtkUnits)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator PostAttack(UnitData attackUnitData, UnitData targetUnitData, UnitData protectedUnitData)
	{
		return null;
	}

	public List<UnitData> GetComaUnitData()
	{
		return null;
	}

	public List<UnitData> GetComaUnitData(List<AttackProcess> results)
	{
		return null;
	}

	private bool GetComaUnitData(AttackProcess result, List<UnitData> comaUnitData, bool clearList, bool needSort)
	{
		return false;
	}

	private void ApplyPreEffect(UnitData triggerUnitData)
	{
	}

	[DebuggerHidden]
	private IEnumerator GenerateRetreatPostEffect(UnitData triggerUnitData, UnitData targetUnitData)
	{
		return null;
	}
}
