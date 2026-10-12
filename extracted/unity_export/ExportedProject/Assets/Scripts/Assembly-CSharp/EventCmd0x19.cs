using System;
using System.Collections;
using System.Diagnostics;
using System.Text;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd0x19 : EventCmdBase
{
	public static readonly string ms_code;

	public string winCondition;

	public string loseCondition;

	public bool notUseAutoDefaultWin;

	public bool notUseAutoDefaultLose;

	private GameObject missionInfo;

	private string m_prevLoseCond;

	private bool m_prevNotUseAutoDefaultWin;

	private bool m_prevNotUseAutoDefaultLose;

	private string m_prevWinCond;

	private float realtimeSinceStartUp;

	private StringBuilder sb;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	public override void Skip()
	{
	}

	private void Animate()
	{
	}

	public override void _Apply()
	{
	}

	public override void _Revert()
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitAndClose()
	{
		return null;
	}

	private string BuildWinCondition()
	{
		return null;
	}

	private string BuildLoseCondition()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
