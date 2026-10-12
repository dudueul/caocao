using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Worldmap : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_edge;

	[SerializeField]
	private List<ObscuredInt> m_link;

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

	public int edge
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public List<ObscuredInt> link
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
