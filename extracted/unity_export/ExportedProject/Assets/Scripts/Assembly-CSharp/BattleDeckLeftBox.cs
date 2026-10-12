using System.Collections.Generic;
using UnityEngine;

public class BattleDeckLeftBox : MonoBehaviour
{
	public delegate void OnSelectedUnitChangedDelegate(UserUnit userUnit);

	private readonly EnumData.SortType[] m_excludesSortTypes;

	public UIButton m_btnSortType;

	public UIButton m_btnFilterType;

	public UILabelLocalized m_labelSortType;

	private EnumData.SortType m_curSortType;

	private EnumData.SortType prevSortType;

	private bool reverse;

	private bool initiatedSortType;

	private HeroListItem m_selectedHeroListItem;

	private UISlider m_slider;

	private GameObject m_grid;

	private string m_sortingLabel;

	private HeroListFilter m_filter;

	private bool m_turnOnFilter;

	private bool m_turnOnFilterBackup;

	private UserUnit m_selectedUserUnit;

	public OnSelectedUnitChangedDelegate OnSelectedUnitChanged;

	private AudioSource m_playingAudioSource;

	private AudioClip m_lastAudioClip;

	private BattleDeck battleDeck;

	private HashSet<int> m_duelUnits;

	private int scrollIndex;

	public EnumData.SortType curSortType
	{
		get
		{
			return EnumData.SortType.START;
		}
		set
		{
		}
	}

	public UserUnit selectedUserUnit
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private void Awake()
	{
	}

	private void InitiateSortType()
	{
	}

	private void OnDestroy()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	public void OnButtonPress()
	{
	}

	private void SetSortType(EnumData.SortType sortType)
	{
	}

	private void UpdateDuelUnits()
	{
	}

	public void UpdateUI()
	{
	}

	public UserUnit GetFirstUnit()
	{
		return null;
	}

	public void UpdateTroopInfo()
	{
	}

	public void UpdateTotalCost()
	{
	}

	private void UpdateUnitListTitle()
	{
	}

	private void OnSliderChangeUpdated(float value)
	{
	}

	public void OnClose()
	{
	}

	private List<UserUnit> SortUnits(List<UserUnit> userUnits)
	{
		return null;
	}

	private bool IsDefenseDeck(int unitId)
	{
		return false;
	}

	public void SelectUnit(ulong srl, HeroListItem heroListItem, bool scrollToUnit = false, int idx = -1)
	{
	}

	private void SelectHeroListItem(HeroListItem heroListItem)
	{
	}

	private void UpdateArenaSynergy()
	{
	}

	private void Update()
	{
	}

	private void UpdateUnitDGriDim()
	{
	}

	public void OnFilterPress()
	{
	}

	public void SetFilterOff()
	{
	}

	private void RecoverFilterStatus()
	{
	}

	private void OnApplyFilter(HeroListFilter filter)
	{
	}

	public void OnFilterCancel()
	{
	}

	private void ShowFilter()
	{
	}

	private void ToggleFilter()
	{
	}

	private void GetFilter()
	{
	}

	private bool IsCheckedFilter(UserUnit userUnit)
	{
		return false;
	}

	private void SetFilterBtn()
	{
	}

	private void ApplyFilter()
	{
	}

	private void RecoverHeroListFromFilter()
	{
	}

	public void AutoSelectUnit(List<ulong> pickedUnits)
	{
	}

	public HeroListItem FindHeroListItem(UserUnit userUnit)
	{
		return null;
	}

	public void UnpickAll()
	{
	}
}
