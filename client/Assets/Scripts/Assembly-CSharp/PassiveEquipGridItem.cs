using System;
using UnityEngine;

public class PassiveEquipGridItem : MonoBehaviour
{
	private UserUnit m_userUnit;

	public PassiveList passiveList;

	private UserArenaUnit m_userArenaUnit;

	public UserArenaPassive userArenaPassive;

	private bool m_isUnitPassive;

	private bool m_isSelected;

	public bool m_isLocked;

	public bool m_isEquipped;

	public Action<PassiveEquipGridItem> OnClickHandler;

	private PassiveUI m_passiveUI
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized m_name
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized m_cost
	{
		get
		{
			return null;
		}
	}

	private UISprite m_costIcon
	{
		get
		{
			return null;
		}
	}

	private Transform m_state
	{
		get
		{
			return null;
		}
	}

	private GameObject m_unitPassiveBG
	{
		get
		{
			return null;
		}
	}

	private GameObject m_jobPassiveBG
	{
		get
		{
			return null;
		}
	}

	public bool isUnitPassive
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private GameObject m_selected
	{
		get
		{
			return null;
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

	private GameObject m_dim
	{
		get
		{
			return null;
		}
	}

	public bool isLocked
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

	public void SetPassive(UserUnit userUnit, PassiveList passiveList, bool isUnitPasive, int index, Action<PassiveEquipGridItem> clickHandler)
	{
	}

	public void SetPassive(UserArenaUnit userArenaUnit, UserArenaPassive userArenaPassive, int index, Action<PassiveEquipGridItem> clickHandler)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateArenaUI()
	{
	}

	private void OnClickPassiveUI(SpecialStateUI passiveUI)
	{
	}

	private void OnClick()
	{
	}
}
