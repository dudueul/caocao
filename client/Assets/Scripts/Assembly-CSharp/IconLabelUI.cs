using UnityEngine;

public class IconLabelUI : MonoBehaviour
{
	private static readonly Color positiveColor;

	private static readonly Color negativeZeroColor;

	public UILabelLocalized m_label;

	public GameObject m_effect;

	public bool doLabelTween;

	private int m_count;

	public int count
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	private void UpdateLabel()
	{
	}

	private void UpdateEffect(bool active)
	{
	}
}
