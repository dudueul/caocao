using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using UnityEngine;

[Serializable]
public class TutorialUI : PermanentBase
{
	[SerializeField]
	private ObscuredInt m_id;

	[SerializeField]
	private List<ObscuredString> m_scene;

	[SerializeField]
	private ObscuredString m_head;

	[SerializeField]
	private ObscuredString m_mid;

	[SerializeField]
	private ObscuredString m_tail;

	[SerializeField]
	private ObscuredInt m_action;

	[SerializeField]
	private ObscuredInt m_saveflagId;

	[SerializeField]
	private ObscuredInt m_compare;

	[SerializeField]
	private ObscuredInt m_value;

	[SerializeField]
	private ObscuredString m_checkObject;

	[SerializeField]
	private ObscuredInt m_onlyTutorial;

	[SerializeField]
	private ObscuredString m_script;

	[SerializeField]
	private ObscuredInt m_useFlag;

	[SerializeField]
	private ObscuredString m_unableMsg;

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

	public List<ObscuredString> scene
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string head
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string mid
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string tail
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int action
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int saveflagId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int compare
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int value
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string checkObject
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int onlyTutorial
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string script
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int useFlag
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string unableMsg
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
