using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class StickerSet : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_thumbnail;

	[SerializeField]
	private List<ObscuredInt> m_stickerId;

	public string name
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

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

	public string thumbnail
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> stickerId
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
