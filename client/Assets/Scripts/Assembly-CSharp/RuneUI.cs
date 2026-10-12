using System;
using UnityEngine;

[ExecuteInEditMode]
public class RuneUI : MonoBehaviour
{
	private readonly Color32[] m_starColors;

	private readonly Color32[] m_starColorsJpn;

	private UserRuneItem m_userRuneItem;

	private UserRuneSlot m_userRuneSlot;

	private RuneItemSlot m_runeItemSlot;

	private Rune m_runePd;

	private RunePassive m_runePassivePd;

	private bool m_showAvailable;

	private bool m_wearing;

	private GameObject m_equipped;

	private EnumData.RuneType m_runeType;

	private EnumData.RuneMainStat m_runeMainStat;

	private bool m_isSelected;

	private bool m_useIconSelect;

	private bool m_isScrollable;

	private bool m_isEquipped;

	private bool m_useUnequippedDimming;

	private bool m_showDesc;

	private bool m_isHidden;

	private UIGrid m_starGrid;

	private bool m_isDim;

	private bool m_isNone;

	private bool m_showStar;

	public Action<RuneUI> OnClickHandler;

	public Action<RuneUI> OnDoubleClickHandler;

	public Action<RuneUI> OnSelectHandler;

	public Action<RuneUI> OnPressHandler;

	public Action<RuneUI> OnReleaseHandler;

	public Action<RuneUI> OnClickNoCheckValidHandler;

	public Action uiUpdatedCallback { get; set; }

	public UserRuneItem userRuneItem
	{
		get
		{
			return null;
		}
		private set
		{
		}
	}

	public UserRuneSlot userRuneSlot
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public RuneItemSlot runeItemSlot
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public Rune runePd
	{
		get
		{
			return null;
		}
	}

	public RunePassive runePassivePd
	{
		get
		{
			return null;
		}
	}

	public bool available
	{
		get
		{
			return false;
		}
	}

	public bool showAvailable
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool wearing
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private GameObject equipped
	{
		get
		{
			return null;
		}
	}

	public EnumData.RuneType runeType
	{
		get
		{
			return EnumData.RuneType.BLUE;
		}
		private set
		{
		}
	}

	public EnumData.RuneMainStat runeMainStat
	{
		get
		{
			return EnumData.RuneMainStat.ATK;
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

	public bool useIconSelect
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

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

	public bool isEquipped
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool useUnequippedDimming
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool showDesc
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isHidden
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public UIGrid starGrid
	{
		get
		{
			return null;
		}
	}

	public bool isDim
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isNone
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool showHave { get; set; }

	private void Awake()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	public void SetUserRuneWithFlicker(UserRuneSlot userRuneSlot, RuneItemSlot runeItemSlot)
	{
	}

	public void SetUserRune(UserRuneSlot userRuneSlot, RuneItemSlot runeItemSlot)
	{
	}

	public void SetUserRune(UserRuneItem userRuneItem)
	{
	}

	public void SetRune(Rune rune, bool showStar = true)
	{
	}

	public void SetReward(RewardInfo rewardInfo, bool showStar = true)
	{
	}

	public void SetRuneItemSlot(RuneItemSlot runeItemSlot)
	{
	}

	private void UpdateUI()
	{
	}

	private string GetIconFrameName()
	{
		return null;
	}

	private void UpdateIcon()
	{
	}

	private void UpdateIconFrame()
	{
	}

	private void UpdateRuneNotice()
	{
	}

	private void UpdateDesc()
	{
	}

	private void UpdateDescExp()
	{
	}

	public void OnClick()
	{
	}

	public void OnDoubleClick()
	{
	}

	public void OnSelect()
	{
	}

	public void OnPress()
	{
	}

	public void OnRelease()
	{
	}

	public void OnClickNoCheckValid()
	{
	}

	private void ClearEquipEffect()
	{
	}

	private void UpdateStar()
	{
	}

	private void UpdateAvailable()
	{
	}
}
