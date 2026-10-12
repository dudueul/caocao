using System.Collections;
using System.Diagnostics;

public class PanelAmbition : SelfRegister2UIComponentsMonobehaviour
{
	public static PanelAmbition instance;

	public UISprite redGauge;

	public UISprite yellowGauge;

	private int m_currentAmbition;

	private int m_value;

	private float m_prevSliderValue;

	private bool m_isLeftSign;

	public static PanelAmbition CreateInstance()
	{
		return null;
	}

	private void Start()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void UpdateGuageWithScenarioID(ScenarioUI scenarioUI)
	{
	}

	private void OnGuageChangeUpdatedSimple(float value)
	{
	}

	public void UpdateGuage(int curFlagId)
	{
	}

	public void UpdateGuage(int value, int curFlagId)
	{
	}

	[DebuggerHidden]
	private IEnumerator Hide()
	{
		return null;
	}

	private void OnGuageChangeStarted()
	{
	}

	private void OnGuageChangeUpdated(float value)
	{
	}

	private void OnGuageChangeCompleted()
	{
	}

	private void SetValues(UISprite ambientGaugeUI, float value)
	{
	}

	public void ForcedComplete()
	{
	}

	private void Destroy()
	{
	}

	private void UpdateEffect(int currentAmbition)
	{
	}

	public void SetCollider(bool isSet)
	{
	}

	public void ShowEffects()
	{
	}

	public void HideEffects()
	{
	}
}
