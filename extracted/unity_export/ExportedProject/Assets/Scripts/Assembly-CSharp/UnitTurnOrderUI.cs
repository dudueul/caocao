using System;
using UnityEngine;

public class UnitTurnOrderUI : MonoBehaviour
{
	private static readonly Color DEATH_COLOR;

	private static readonly Color SEL_DESC_COL;

	private static readonly Color OWN_COL;

	private static readonly Color ENEMY_COL;

	public Action<UnitData> onClickCallback;

	private UnitData m_unit;

	private bool isCompact;

	private EnumData.StatType descType;

	private bool everOnceActivated;

	private UILabelLocalized m_labelNumber;

	private UITexture m_unitTexture;

	private UILabelLocalized m_labelDesc;

	private UISprite m_spriteColor;

	private GameObject m_focus;

	private GameObject m_retreate;

	private GameObject m_leaderOwn;

	private GameObject m_leaderEnemy;

	private GameObject m_select;

	public bool lockChangeOrder { get; set; }

	private UILabelLocalized labelNumber
	{
		get
		{
			return null;
		}
	}

	private UITexture unitTexture
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelDesc
	{
		get
		{
			return null;
		}
	}

	private UISprite spriteColor
	{
		get
		{
			return null;
		}
	}

	private GameObject focus
	{
		get
		{
			return null;
		}
	}

	private GameObject retreate
	{
		get
		{
			return null;
		}
	}

	private GameObject leaderOwn
	{
		get
		{
			return null;
		}
	}

	private GameObject leaderEnemy
	{
		get
		{
			return null;
		}
	}

	private GameObject select
	{
		get
		{
			return null;
		}
	}

	public void Init(bool compact)
	{
	}

	public void SetUnit(UnitData unit, EnumData.StatType descType)
	{
	}

	private void OnEnable()
	{
	}

	public void SetDesc(EnumData.StatType descType)
	{
	}

	private void OnUnitChangedStat(UserUnit unit)
	{
	}

	public void UpdateDesc(EnumData.StatType descType)
	{
	}

	public void UpdateDeadUI()
	{
	}

	public void FocusOn()
	{
	}

	public void FocusOut(bool turnOffFocus = false)
	{
	}

	public bool TryFocusOn(UnitData unit)
	{
		return false;
	}

	public void Select()
	{
	}

	public void DeSelect()
	{
	}

	public bool TrySelect(UnitData selectedUnit)
	{
		return false;
	}

	public void ChangeOrder(Vector3 pos)
	{
	}

	private void OnClick()
	{
	}

	private void OnDestroy()
	{
	}
}
