using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

internal class SkillAttackFlow : MonoBehaviour
{
	private SkillAttackFlow m_parent;

	private bool m_isPincer;

	private UnitData m_realAttackUnitData;

	private UnitData m_realTargetUnitData;

	private UnitData m_attackUnitData;

	private UnitData m_targetUnitData;

	private UnitData m_protectedUnitData;

	private bool m_isGuarded;

	private EnumData.AttackType m_startAttackType;

	private EnumData.Skills m_skillId;

	private List<SkillProcess> m_cachedResults;

	private List<ResultPerUnit> m_currentResults;

	private static List<UnitData> ms_assultedUnitObjs;

	private static List<SkillProcess> ms_allCachedResults;

	private static List<UnitStateGaugeBar> ms_stateGaugeBars;

	private GameObject m_left;

	private GameObject m_right;

	private int m_attackTimingCount;

	private bool isPanelAnimationCompleted;

	private SkillAttackFlow()
	{
	}

	public static SkillAttackFlow CreateSkillAttackFlow(UnitData triggerUnitData, UnitData targetUnitData, EnumData.AttackType attackType, EnumData.Skills skillID, bool isPincer, SkillAttackFlow parent)
	{
		return null;
	}

	private void OnDestroy()
	{
	}

	private void SetAttackFlowData(UnitData triggerUnitData, UnitData targetUnitData, EnumData.AttackType attackType, EnumData.Skills skillID, bool isPincer)
	{
	}

	private void SetTargetOfDamage(UnitData targetUnit, EnumData.Skills useSkillId)
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

	private void EndFlow(bool mainCall = false)
	{
	}

	[DebuggerHidden]
	private IEnumerator Attack(UnitData triggerUnitData, UnitData targetUnitData, EnumData.Skills skillId)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowSkillEffect(List<ResultPerUnit> listResult, EnumData.SkillEffect skillEffect)
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
	private IEnumerator StartPanelAnimation(bool doubleArtificed, List<List<Component>> list2OrderedUnitNeighbors, List<int> unitIdx2ListIdx)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator Pincer(UnitData pincerTriggerUnitData, UnitData targetUnitData)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator PostAttack(UnitData attackUnitData, UnitData targetUnitData)
	{
		return null;
	}

	public List<UnitData> GetComaUnitData()
	{
		return null;
	}

	public List<UnitData> GetComaUnitData(List<SkillProcess> results)
	{
		return null;
	}

	private bool GetComaUnitData(SkillProcess result, List<UnitData> comaUnitData, bool clearList, bool needSort)
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

	[DebuggerHidden]
	private IEnumerator CastFailFlow(UnitData castUnitData)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator NonTargetSkillEffect(Skill skillData)
	{
		return null;
	}

	private void PlaySkillSpeechSound(Skill skillData)
	{
	}
}
