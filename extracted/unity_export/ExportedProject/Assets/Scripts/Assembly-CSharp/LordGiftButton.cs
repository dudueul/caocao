using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class LordGiftButton : MonoBehaviour
{
	public UITexture m_icon;

	public UILabelLocalized m_iconText;

	public int m_rankupGiftId;

	private int cashshopId;

	private CashShopItemInfo m_cashShopItemInfo;

	private RankUpGift m_rankupGift;

	private bool m_cashshopItemPurchased;

	private Dictionary<int, List<int>> m_rankupGiftIndices;

	private bool m_gotAllRewards;

	private UIGrid m_grid;

	private Action<bool> getHistoryCallback;

	private UIGrid grid
	{
		get
		{
			return null;
		}
	}

	public void SetGotAllRewards()
	{
	}

	public void SetGotReward(int rankupGiftIndex)
	{
	}

	public void SetPurchased()
	{
	}

	public void Refresh(Action<bool> callback)
	{
	}

	private void Start()
	{
	}

	private void InitiateRankupGift()
	{
	}

	private bool IncreaseIdx()
	{
		return false;
	}

	private void OnDestroy()
	{
	}

	public void Toggle(bool on)
	{
	}

	private void GetRankupGiftHistory()
	{
	}

	private void OnGetRankupGiftHistories(JsonData response)
	{
	}

	private void CallGetHistoryCallback()
	{
	}

	private void UpdateInfo(JsonData histories)
	{
	}

	private void UpdateUI()
	{
	}

	public void ShowRankupGift()
	{
	}

	private void ToggleIconActive(bool active)
	{
	}
}
