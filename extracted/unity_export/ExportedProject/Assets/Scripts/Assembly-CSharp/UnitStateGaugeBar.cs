using Caocao;
using UnitChrStatusUI;
using UnityEngine;

public class UnitStateGaugeBar : MonoBehaviour
{
	private HpBar hpBar;

	private ResourceBar resourceBar;

	private AccuracyRate accuracyRate;

	private UnitChrStatusUI.JobRelation jobRelation;

	private UnitData unitData;

	private static Vector3 ms_mirroredByXFactor;

	private UnitAnimation unitAnimation;

	private Vector3 m_mirroredByXScale;

	private Vector3 m_originalScale;

	private bool hpChanged;

	private EnumData.UnitResType changedResType;

	public bool showCurrentHp { get; private set; }

	private void Start()
	{
	}

	public void ShowCurrentHp()
	{
	}

	public void ShowCurrentHpMp()
	{
	}

	public void HideCurrentHpMp()
	{
	}

	public void ShowExpectDamage(ExpectDmg expectDmg)
	{
	}

	public void HideExpetDamage()
	{
	}

	public void AddEffect(EffectInteger effect)
	{
	}

	public void StartAnimation()
	{
	}

	public void OnAnimCompleted()
	{
	}

	public void OnAllFlowCompleted()
	{
	}

	private void OnUnitDirectionChanged(UnitAnimationBase animBase, UnitDirection unitDirection)
	{
	}
}
