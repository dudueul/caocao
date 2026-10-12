using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class GetSkill : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private List<ObscuredInt> m_skillId;

	[SerializeField]
	private List<ObscuredInt> m_lv;

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

	public List<ObscuredInt> skillId
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> lv
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
