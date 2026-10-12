using System;
using UnityEngine;

public class BaseStatUI : MonoBehaviour
{
	private EnumData.StatType m_statType;

	private int m_value;

	public UITexture m_icon;

	public UILabelLocalized m_desc;

	public Action OnClickCallback;

	public void SetStat(EnumData.StatType statType, int val)
	{
	}

	public void UpdateUI()
	{
	}

	public void OnClick()
	{
	}
}
