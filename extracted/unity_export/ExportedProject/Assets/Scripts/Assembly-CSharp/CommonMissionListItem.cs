using System;
using UnityEngine;

public class CommonMissionListItem : MonoBehaviour
{
	private CommonMissiontitle m_missionTitle;

	private Action<int> onClick__BackingField;

	public CommonMissiontitle missionTitle
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private event Action<int> onClick
	{
		add
		{
		}
		remove
		{
		}
	}

	public void SetData(CommonMissiontitle data, bool isSelect, Action<int> callback)
	{
	}

	public void OnClick()
	{
	}

	public void OnSelectBtn(int id)
	{
	}

	public void OnShowNewTag()
	{
	}

	private void UpdateUI()
	{
	}
}
