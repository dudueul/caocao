using System;
using UnityEngine;

public class RuneFilterListitem : MonoBehaviour
{
	private EnumData.RuneFilterType m_filterType;

	public int data;

	private bool m_selected;

	private UIButton m_btn;

	private Action<EnumData.RuneFilterType, int, bool> onClickBtn__BackingField;

	private UIButton btn
	{
		get
		{
			return null;
		}
	}

	private event Action<EnumData.RuneFilterType, int, bool> onClickBtn
	{
		add
		{
		}
		remove
		{
		}
	}

	public void SetInfo(EnumData.RuneFilterType filterType, int itemData, bool isSelected, Action<EnumData.RuneFilterType, int, bool> callback)
	{
	}

	public void OnClickBtn()
	{
	}

	public void UpdateButton(bool isSelected)
	{
	}

	private void Selected(bool selected)
	{
	}
}
