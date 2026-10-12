using System;
using UnityEngine;

public abstract class SpecialStateUI : MonoBehaviour
{
	public enum EquipStateType
	{
		None = 0,
		Equipped = 1,
		EnableEquip = 2
	}

	private bool m_isDim;

	private bool m_isLocked;

	private bool m_canClick;

	private EquipStateType m_equipStateType;

	private bool m_isNone;

	private bool m_buttonEnable;

	private UISprite m_icon;

	private UITexture m_uiTexture;

	public Action<SpecialStateUI> OnClickCallback;

	protected abstract bool hasData { get; }

	protected abstract UIAtlas iconAtlas { get; }

	protected abstract string iconName { get; }

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

	public bool canClick
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public EquipStateType equipStateType
	{
		get
		{
			return EquipStateType.None;
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

	protected GameObject m_lock
	{
		get
		{
			return null;
		}
	}

	protected BoxCollider m_boxCollider
	{
		get
		{
			return null;
		}
	}

	protected UISprite m_frame
	{
		get
		{
			return null;
		}
	}

	protected UILabelLocalized m_equip
	{
		get
		{
			return null;
		}
	}

	protected GameObject m_noItem
	{
		get
		{
			return null;
		}
	}

	private UISprite icon
	{
		get
		{
			return null;
		}
	}

	private UITexture uiTexture
	{
		get
		{
			return null;
		}
	}

	public void SetButtonEnable(bool enable)
	{
	}

	protected virtual void OnDestroy()
	{
	}

	protected void UpdateIcon()
	{
	}

	public virtual void SetItemDefaultIcon(EnumData.ItemType itemType)
	{
	}

	public virtual void SetDefaultIcon(UIAtlas atlas, string iconName)
	{
	}

	public void SetFocusSize(Vector2 size)
	{
	}

	private void OnClick()
	{
	}

	protected virtual void UpdateFrame()
	{
	}

	public void SetWeaknessIcon(EnumData.WeaknessType weakness, bool isRevealed)
	{
	}
}
