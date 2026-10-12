using System;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class ArenaDeckSetting : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_progress;

	[SerializeField]
	private ObscuredInt m_getPresetMin;

	[SerializeField]
	private ObscuredInt m_getPresetMax;

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

	public int progress
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int getPresetMin
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int getPresetMax
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
