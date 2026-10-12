using System.Collections.Generic;
using LitJson;

public class PopupItemLevelPlus : PopupLevelPlus
{
	private ulong m_unitSrl;

	private UserItem m_userItem;

	private ItemUI m_itemUI;

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

	public int expItemPassiveId
	{
		get
		{
			return 0;
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

	public static PopupItemLevelPlus Show(ulong unitSrl, UserItem item)
	{
		return null;
	}

	protected override void InitUI()
	{
	}

	protected override void UpdateMinMaxLv()
	{
	}

	protected override void OnDestroy()
	{
	}

	protected override void UpdateUI()
	{
	}

	private void RequestItemLevPlus(ulong itemSrl, int targetLevel, int cost)
	{
	}

	protected override void OnResponse(JsonData response)
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

	public override void OnPopupLevelPlusMethod()
	{
	}
}
