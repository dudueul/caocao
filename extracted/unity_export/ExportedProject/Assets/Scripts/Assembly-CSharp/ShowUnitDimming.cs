using UnityEngine;

public class ShowUnitDimming : MonoBehaviour
{
	private float m_time;

	private static Color ms_originColor;

	private Color befColor;

	private Color destColor;

	private UnitAnimation unitAnimation;

	public void StartAnimation(float time, BattleDimmer.DimmingState dimmingState)
	{
	}

	public void StopDimming()
	{
	}

	private void OnStopDimmingEffect()
	{
	}

	protected void OnDimmingEffectUpdated(Color value)
	{
	}
}
