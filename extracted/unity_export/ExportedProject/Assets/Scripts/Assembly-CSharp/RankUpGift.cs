using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class RankUpGift : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_needComplete;

	[SerializeField]
	private ObscuredString m_icon;

	[SerializeField]
	private ObscuredString m_iconText;

	[SerializeField]
	private ObscuredString m_faceTexture;

	[SerializeField]
	private ObscuredString m_dialog;

	[SerializeField]
	private ObscuredInt m_maxLordRank;

	[SerializeField]
	private ObscuredString m_aproxPrice;

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

	public int needComplete
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string icon
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string iconText
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string faceTexture
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string dialog
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int maxLordRank
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string aproxPrice
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
