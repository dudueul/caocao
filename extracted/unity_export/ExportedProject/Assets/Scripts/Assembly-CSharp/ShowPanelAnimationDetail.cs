using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

public class ShowPanelAnimationDetail : ShowPanelAnimation
{
	private DamageBalloonDetail m_damageBalloonDetail;

	private static string ownLine;

	private static string allyLine;

	private static string enemyLine;

	private static string ownAttribute;

	private static string allyAttribute;

	private static string enemyAttribute;

	public override void SetResult(List<EffectBase> effects, UnitData triggerUnitObj, UnitData targerUnitObj)
	{
	}

	public override void StartAnimation()
	{
	}

	protected override UISlider GetSlider(ElementType type)
	{
		return null;
	}

	protected override UILabelLocalized GetLabel(ElementType type)
	{
		return null;
	}

	protected override UILabelLocalized GetStepLabel(ElementType type)
	{
		return null;
	}

	protected override string GetStepLabelFormat(ElementType type)
	{
		return null;
	}

	private void InitializeField(ElementType type, int from, int max)
	{
	}

	public override void InitPanel()
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate()
	{
		return null;
	}

	private void UpdateHPDeltaSlider()
	{
	}

	public override void ShowFaceDamaged(float delay)
	{
	}

	public override void ShowFaceDefended(float delay)
	{
	}

	public override void ShowFaceAttack(float delay)
	{
	}

	public override void ShowFaceCriticalAttack(float delay)
	{
	}

	private void OnDisable()
	{
	}

	protected override void OnDestroy()
	{
	}
}
