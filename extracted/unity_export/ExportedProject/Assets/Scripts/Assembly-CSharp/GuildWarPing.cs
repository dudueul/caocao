using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GuildWarPing : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredString m_pingPrefab;

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

	public string pingPrefab
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
