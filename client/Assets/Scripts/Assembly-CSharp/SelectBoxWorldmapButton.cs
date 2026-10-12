using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class SelectBoxWorldmapButton : MonoBehaviour
{
	public UILabelLocalized timeLabel;

	private float m_additinalSecs;

	private List<CashShop> m_cashShopPds;

	private List<SelectBox> m_selectBoxPds;

	private List<DateTime> m_startDates;

	private List<DateTime> m_endDates;

	private int currentAvailableIdx;

	private CashShop currentCashShop;

	private SelectBox currentSelectBox;

	private DateTime currentStartDate;

	private DateTime currentEndDate;

	private UserSelectBox currentUserSelectBox;

	private void Start()
	{
	}

	private void OnDestory()
	{
	}

	public void GatherData()
	{
	}

	private void Refine()
	{
	}

	public void OnClick()
	{
	}

	private void OnRefreshBox(JsonData response)
	{
	}

	private void OnTimer()
	{
	}
}
