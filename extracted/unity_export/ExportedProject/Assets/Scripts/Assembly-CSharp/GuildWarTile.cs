using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarTile : PermanentBase
{
	[SerializeField]
	private ObscuredString m_name;

	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_tileType;

	[SerializeField]
	private ObscuredInt m_height;

	[SerializeField]
	private ObscuredInt m_Texture;

	[SerializeField]
	private ObscuredInt m_textureWithoutFloor;

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

	public int tileType
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int height
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int Texture
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int textureWithoutFloor
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}
}
