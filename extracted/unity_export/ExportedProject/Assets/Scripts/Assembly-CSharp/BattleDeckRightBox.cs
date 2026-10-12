using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class BattleDeckRightBox : SelfRegister2UIComponentsMonobehaviour
{
	private enum UNIT_INFO_GAUGE
	{
		baseSprite = 0,
		buffSprite = 1,
		sumSprite = 2
	}

	private GameObject m_tooltipPanel;

	public PrefabInstance m_profileInst;

	public PrefabInstance m_unitstatInst;

	public PrefabInstance m_troopstatInst;

	public PrefabInstance m_equipInst;

	public PrefabInstance m_treasureEquipInst;

	public PrefabInstance m_unitFavoriteInst;

	public UILabelLocalized m_unitFavoriteText;

	public int[] m_beforSelectedSkills;

	public CaocaoTabButtons m_tab;

	public CaocaoTabButtons m_tabTwoBtn;

	public UILabelLocalized m_tab2TileAttrTitle;

	public GameObject m_unitInfo;

	public GameObject m_unitInfoStat;

	public UIBattleFormation battleFormation;

	public UITileAttributeInfo tileAttributeInfo;

	public UITileAttributeInfo tileAttributeInfo4;

	public UIBrawlArmyPlace brawlArmyPlace;

	public CaocaoTabButtons m_equipBtnTab;

	public GameObject m_equipInfo;

	public GameObject m_runeInfo;

	public GameObject m_relationInfo;

	public RuneSlotUI[] m_runeSlotUIs;

	public UIButton m_btnRuneSlot;

	public UIButton m_btnQuickEquip;

	public UIButton m_btnRecentQuickEquip;

	public PrefabInstance m_synergyTypeInst;

	public GameObject m_guildwarInfo;

	public UILabelLocalized m_GuildwarStatType;

	public UILabel m_GuildwarStatValue;

	public UISlider m_GuildwarRuneBonusBar;

	public UILabel m_GuildwarRuneBonusBar_SliderValue;

	public UILabelLocalized m_GuildwarRuneBonusValue;

	public UISlider m_GuildwarPassiveBonusBar;

	public UILabel m_GuildwarPassiveBonusBar_SliderValue;

	public UILabelLocalized m_GuildwarPassiveBonusValue;

	private ulong recentQuickSlotSrl;

	private int relationId;

	private UserUnit m_userUnit;

	public UserUnit userUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<UserUnit> userUnits { get; set; }

	public bool runeEnabled
	{
		get
		{
			return false;
		}
	}

	private void RegistArenaUnitCallback(bool regist)
	{
	}

	private void OnEnable()
	{
	}

	private void DefaultSetting()
	{
	}

	private void UpdateRuneUIEnabled()
	{
	}

	private void OnDisable()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void OnRuneSlotChange(JsonData response)
	{
	}

	private void OnTabChanged(CaocaoButton caocaoButton)
	{
	}

	private void UpdateUI()
	{
	}

	public void UpdateTileAttribute()
	{
	}

	public void UpdateUnitItems()
	{
	}

	private void OnRuneUpdatedHandler(JsonData response)
	{
	}

	public void UpdateUnitRune()
	{
	}

	private void UpdateUnitRelation()
	{
	}

	private void UpdateRelationUnitUI(Transform item, int unitId, bool completedRelation)
	{
	}

	public void UpdatePickedUnit(UserUnit unit)
	{
	}

	public void OnClickRelationName()
	{
	}

	public void OnClickPrevRelation()
	{
	}

	public void OnClickNextRelation()
	{
	}

	public void OnClickRelationUnitFace()
	{
	}

	public void UpdateEquipTab()
	{
	}

	public void UpdateBrawlArmyPlaceTab()
	{
	}

	private void UpdateRecentQuickSlot()
	{
	}

	private void UpdateUnitFavorite(UnitFavoriteUI favoriteUI)
	{
	}

	private void SetBeforSelectedSkills(UserUnit userUnit)
	{
	}

	public void OnClickItem(ItemUI itemUI)
	{
	}

	private void OnTreasureItemClick(ItemUI itemUI)
	{
	}

	public void OnClickUnitProfile()
	{
	}

	public void OnClickBattleFormationInfo()
	{
	}

	public void OnBtnBoard()
	{
	}

	public void OnBtnQuickEquip()
	{
	}

	public void OnBtnRecentQuickEquip()
	{
	}

	public void OnBtnEquipTab(CaocaoButton button)
	{
	}

	private void OnUpdatedItem()
	{
	}

	public void OnClickRunePassiveListBtn()
	{
	}
}
