using LitJson;
using UnityEngine;

public class GuildPopupSiegeWarFacilityReinforce : PopupLevelPlus
{
	public enum Mode
	{
		Construct = 0,
		Reinforce = 1
	}

	private Mode m_mode;

	private UILabelLocalized m_labelTitle;

	private UISprite m_spriteFacility;

	private UILabelLocalized m_labelTargetLv;

	private GameObject m_objPurchaseDesc;

	private UILabelLocalized m_labelPurchaseName;

	private UILabelLocalized m_labelPurchaseDesc;

	private GameObject m_objLevelUp;

	private GameObject m_objPurchase;

	private UILabelLocalized m_labelPurchaseCost;

	private int facilityId;

	private GuildSiegeWarFacilityListItem facilityItem;

	private GuildSiegeTownFacility facilityData;

	private GuildSiegeTownFacilityByLV lvData;

	private GuildSiegeWarFacility facility;

	private int m_availableMaxLv;

	public bool isReinforceMode
	{
		get
		{
			return false;
		}
	}

	public bool isConstructMode
	{
		get
		{
			return false;
		}
	}

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

	protected override EnumData.GoodsType goods
	{
		get
		{
			return EnumData.GoodsType.None;
		}
	}

	protected override Transform trsUnitInfo
	{
		get
		{
			return null;
		}
	}

	protected override int ExpCost(int lv)
	{
		return 0;
	}

	protected override bool CanUseGoods(int cost)
	{
		return false;
	}

	protected override void ShowPanelImpl()
	{
	}

	public static void Show(GuildSiegeWarFacilityListItem facilityItem, Mode mode)
	{
	}

	protected override void InitUI()
	{
	}

	protected override void UpdateMinMaxLv()
	{
	}

	protected override void UpdateUI()
	{
	}

	protected override void UpdateLevelUI(int targetLevel)
	{
	}

	protected override int GetLevelUpCost(int toLevel)
	{
		return 0;
	}

	protected override void StartConsuming(int cost)
	{
	}

	private void OnReinforceResponse(JsonData response)
	{
	}

	private void OnConstructResponse(JsonData response)
	{
	}
}
