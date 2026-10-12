using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class UserPvpMapInfoUI : PopupUserInfo.InnerUI
{
	private enum PvpType
	{
		Competition = 0,
		Annihilation = 1
	}

	private Transform transform;

	private JsonData jsonData;

	private PvpType curTab;

	private List<PVPScoreDetailListItem> lstDetailItems;

	private CaocaoTabButtons m_tabButton;

	private Transform m_weekScore;

	private Transform m_totalScore;

	private UIGrid m_gridDetail;

	public ulong uid { get; set; }

	private CaocaoTabButtons tabButton
	{
		get
		{
			return null;
		}
	}

	private Transform weekScore
	{
		get
		{
			return null;
		}
	}

	private Transform totalScore
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridDetail
	{
		get
		{
			return null;
		}
	}

	public void Init(Transform trs, ulong uid)
	{
	}

	public void SetActive(bool active)
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	public void UpdateUI(JsonData response)
	{
	}

	private void UpdateDetailUI(JsonData scoreData)
	{
	}

	private PVPScoreDetailListItem GetListItem(int idx)
	{
		return null;
	}

	private void UpdateScoreLabels(Transform trs, JsonData data)
	{
	}

	public void Destroy()
	{
	}
}
