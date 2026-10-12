using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class ShowPanelAnimationSimple : ShowPanelAnimation
{
	private static Color ownColor;

	private static Color allyColor;

	private static Color enemyColor;

	public override void SetResult(List<EffectBase> effects, UnitData triggerUnitObj, UnitData targerUnitObj)
	{
	}

	private void InitializeField(ElementType type, int from, int max)
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

	public override void InitPanel()
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate()
	{
		return null;
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
}
