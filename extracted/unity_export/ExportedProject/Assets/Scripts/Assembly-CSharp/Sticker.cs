using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Sticker : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_displayName;

	[SerializeField]
	private ObscuredString m_icon;

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

	public string displayName
	{
		get
		{
			return null;
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
}
