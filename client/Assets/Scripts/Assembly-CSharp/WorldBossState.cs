using UnityEngine;

public class WorldBossState : MonoBehaviour
{
	[SerializeField]
	private Color[] m_colors;

	[SerializeField]
	private int m_divider;

	private bool m_initialized;

	private UISlider m_slider;

	private UILabelLocalized m_valueLabel;

	private UILabelLocalized m_depthLabel;

	private UITexture m_thumbTexture;

	private int m_max;

	private int m_cur;

	private int m_start;

	private int m_end;

	private int m_depth;

	public void Initialize()
	{
	}

	private void SetMaxRes(int value, bool needUpdate)
	{
	}

	private void SetRes(int value, bool needUpdate)
	{
	}

	public void OnAnimate(int value)
	{
	}

	private void UpdateUI()
	{
	}

	private void SetSliderState(bool state)
	{
	}

	public void InitRes(int max, int cur)
	{
	}

	public void UpdateRes(int value, bool immediate)
	{
	}

	public void SetResSliderState(bool flag)
	{
	}

	public void Animate(int delta, float time = 1f)
	{
	}

	private void OnUpdate(float value)
	{
	}

	private void OnUpate(int value)
	{
	}

	private void OnDestroy()
	{
	}
}
