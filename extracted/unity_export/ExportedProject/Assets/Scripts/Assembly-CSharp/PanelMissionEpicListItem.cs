using System;
using UnityEngine;

public class PanelMissionEpicListItem : MonoBehaviour
{
	private CommonMission m_mission;

	private UserCommonMission m_userMission;

	private Action<int> onClick__BackingField;

	public CommonMission mission
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

	public void SetData(CommonMission commonMission, bool isSelect, bool isShowArrow, Action<int> callback)
	{
	}

	public void OnClick()
	{
	}

	public void OnSelectBtn(int id)
	{
	}

	private void UpdateUI()
	{
	}
}
