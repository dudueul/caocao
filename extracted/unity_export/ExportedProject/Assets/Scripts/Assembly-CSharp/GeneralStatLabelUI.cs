using UnityEngine;

public class GeneralStatLabelUI : MonoBehaviour
{
	private UILabelLocalized m_title;

	private UILabelLocalized m_valueLabel;

	private GameObject m_bg;

	private EnumData.GeneralStatType m_statType;

	private int m_statValue;

	private UILabelLocalized title
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized valueLabel
	{
		get
		{
			return null;
		}
	}

	private GameObject bg
	{
		get
		{
			return null;
		}
	}

	private EnumData.GeneralStatType statType
	{
		get
		{
			return EnumData.GeneralStatType.None;
		}
		set
		{
		}
	}

	private int statValue
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	private void OnDestroy()
	{
	}

	public void SetStat(EnumData.GeneralStatType type, int val, bool withBg)
	{
	}

	private string GetStatString(EnumData.GeneralStatType statType)
	{
		return null;
	}
}
