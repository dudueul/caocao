using System;
using UnityEngine;

[ExecuteInEditMode]
public class GoodUI : MonoBehaviour
{
	private EnumData.GoodsType m_goodType;

	private int m_amount;

	private bool m_isScrollable;

	public RewardInfo rewardInfo;

	public Action<GoodUI> OnClickHandler;

	public bool isScrollable
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public void SetReward(RewardInfo rewardInfo)
	{
	}

	public void SetGoods(EnumData.GoodsType type, int amount)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateIconFrame()
	{
	}

	private string GetIconFrameName()
	{
		return null;
	}

	private void UpdateDescExp()
	{
	}

	public void OnClick()
	{
	}
}
