using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class Shortcut : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private ObscuredInt m_popupButton;

	[SerializeField]
	private ObscuredString m_quickText;

	[SerializeField]
	private List<ObscuredInt> m_quick;

	[SerializeField]
	private List<ObscuredInt> m_quickDetail;

	[SerializeField]
	private ObscuredInt m_checkTutorId1;

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

	public string quickText
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> quick
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public List<ObscuredInt> quickDetail
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int checkTutorId1
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
