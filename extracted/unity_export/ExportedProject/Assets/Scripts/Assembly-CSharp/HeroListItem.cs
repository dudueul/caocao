using System;
using UnityEngine;

public class HeroListItem : UIReuseScrollViewCell
{
	private enum ObjType
	{
		Defence = 0,
		Governor = 1,
		Protect = 2,
		Off = 3,
		Etc = 4
	}

	public class UnitCellData : IReuseCellData
	{
		public int Index { get; set; }

		public BattleDeck.Type deckType { get; set; }

		public UserUnit userUnit { get; set; }

		public bool useCost { get; set; }

		public Action<ulong, HeroListItem, bool, int> OnSelect { get; set; }

		public bool duelUnit { get; set; }

		public string sortingLabel { get; set; }

		public EnumData.SortType curSortType { get; set; }

		public int tileId { get; set; }
	}

	private UnitCellData m_unitCellData;

	private UserUnit m_userUnit;

	private bool m_isSelected;

	public UILabelLocalized m_name;

	public GameObject m_costObj;

	public UILabelLocalized m_cost;

	public UISprite m_selected;

	public UILabelLocalized m_sort;

	public UISprite m_numObj;

	public UILabelLocalized m_num;

	public UISprite m_groupPattern;

	public GameObject m_lockObj;

	public UILabelLocalized m_lock;

	public GameObject m_defenceObj;

	public UILabelLocalized m_defence;

	public GameObject m_governoreIcon;

	public GameObject m_defenceIcon;

	public GameObject m_protectIcon;

	public UISprite m_defenceBgSprite;

	public GameObject m_defenseLabelOnly;

	public GameObject m_defenseLabelOnlyYellowBg;

	public GameObject m_defenseLabelOnlyGreenBg;

	public UILabelLocalized m_defenseLabelOnlyText;

	public GameObject m_injuryObj;

	public GameObject m_deadObj;

	public GameObject m_black;

	public Transform m_star;

	public UITexture m_unitImage;

	public GameObject m_favoriteIcon;

	public GameObject m_outIcon;

	public GameObject m_duelNotice;

	public UISprite m_tileAttribute;

	public PrefabInstance m_synergyType;

	public Action<ulong> OnClickHandler;

	public UnitCellData unitCellData
	{
		get
		{
			return null;
		}
	}

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

	public bool isSelected
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public Color costColor
	{
		set
		{
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void UpdateBattleDeckUI()
	{
	}

	public void SetActivateSynergy(bool activate)
	{
	}

	private void OnClick()
	{
	}

	public void UpdateUnitGrid()
	{
	}

	private void UpdateSelectedBFIndex()
	{
	}

	private void UpdateDefenceObjType()
	{
	}

	private void UpdateDefenceObj(ObjType type, string format = "", params string[] args)
	{
	}

	private Color GetSquadFrameColor(int squad)
	{
		return default(Color);
	}

	public void OnUpdateFavorite()
	{
	}

	private void UpdateUnitTextureColor(bool canPick, bool isEnoughEnergy)
	{
	}

	private bool CanPick()
	{
		return false;
	}

	private void OnDestroy()
	{
	}

	private void UpdateUnitManagement()
	{
	}

	public void OnSelectItem(bool isSelect)
	{
	}
}
