using System;
using System.Collections.Generic;
using UnityEngine;

public class PopupUnitLevelPlus : PopupLevelPlus
{
	private UserUnit m_userUnit;

	private UnitUI m_unitUI;

	private GameObject m_lvUpObj;

	private GameObject m_tickLvUpObj;

	private UILabelLocalized m_curLevelLabel;

	private UILabelLocalized m_nextLevelLabel;

	private UILabelLocalized m_curJobLabel;

	private UILabelLocalized m_nextJobLabel;

	private UILabelLocalized m_curReinLabel;

	private UILabelLocalized m_nextReinLabel;

	private Item m_ticketItem;

	public const string popupName = "PopupUnitLevelPlus";

	protected override string targetName
	{
		get
		{
			return null;
		}
	}

	protected override int curLv
	{
		get
		{
			return 0;
		}
	}

	protected override int curExp
	{
		get
		{
			return 0;
		}
	}

	protected override int expOnCurrentLevToShow
	{
		get
		{
			return 0;
		}
	}

	protected override bool isMaxLevel
	{
		get
		{
			return false;
		}
	}

	public UserUnit userUnit
	{
		get
		{
			return null;
		}
	}

	public override int currTargetLevel
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	protected override int expSum
	{
		get
		{
			return 0;
		}
	}

	protected override int needExp
	{
		get
		{
			return 0;
		}
	}

	protected override int tartgetExpSum(int lv)
	{
		return 0;
	}

	protected override int ExpCost(int lv)
	{
		return 0;
	}

	public static PopupUnitLevelPlus Show(UserUnit unit)
	{
		return null;
	}

	protected override void InitUI()
	{
	}

	protected override void UpdateMinMaxLv()
	{
	}

	private void UpdateMaxLv(int itemId)
	{
	}

	protected override void OnDestroy()
	{
	}

	public override void SetItemData(PopupLevelPlusMethod.ItemData itemData)
	{
	}

	protected override void UpdateUI()
	{
	}

	private void RequestUnitLevPlus(int unitId, int targetLevel, int itemId, int cost)
	{
	}

	protected override void StartConsuming(EnumData.GoodsType goodsType, int itemId, int cost)
	{
	}

	public override List<PopupLevelPlusMethod.ItemData> GetItemDatas(out PopupLevelPlusMethod.ItemData selected)
	{
		selected = null;
		return null;
	}

	public static void UnitLevelUpByItem(UserUnit destUnit, int useItemId, Action successCallback)
	{
	}

	private static void UnitLevelUpEffect(UserUnit userUnit, int prevReinforceLv, int prevJobLv)
	{
	}
}
