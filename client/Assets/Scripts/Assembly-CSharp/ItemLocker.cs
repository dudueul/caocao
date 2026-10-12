using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ItemLocker : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_itemId;

	[SerializeField]
	private ObscuredInt m_itemtype;

	[SerializeField]
	private ObscuredInt m_quickFlag;

	[SerializeField]
	private ObscuredInt m_quick1;

	[SerializeField]
	private ObscuredInt m_quickDetail1;

	[SerializeField]
	private ObscuredString m_quickText1;

	[SerializeField]
	private ObscuredInt m_quick2;

	[SerializeField]
	private ObscuredInt m_quickDetail2;

	[SerializeField]
	private ObscuredString m_quickText2;

	[SerializeField]
	private ObscuredInt m_goodsType;

	[SerializeField]
	private ObscuredInt m_showInLocker;

	[SerializeField]
	private ObscuredInt m_shortcutId1;

	[SerializeField]
	private ObscuredInt m_shortcutId2;

	[SerializeField]
	private ObscuredInt m_popupButton;

	[SerializeField]
	private ObscuredString m_popupDesc;

	public int id
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int itemId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int itemtype
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int quickFlag
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int quick1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int quickDetail1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string quickText1
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int quick2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int quickDetail2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string quickText2
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int goodsType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int showInLocker
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int shortcutId1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int shortcutId2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int popupButton
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string popupDesc
	{
		get
		{
			return null;
		}
		set
		{
		}
	}
}
