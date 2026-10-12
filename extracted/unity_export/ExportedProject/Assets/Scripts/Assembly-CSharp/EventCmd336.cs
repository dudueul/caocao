using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd336 : EventCmdBase
{
	public static readonly string ms_code;

	public string dialog;

	public string relationTitle;

	public int saveFlagId;

	[NonSerialized]
	private PopupLikeAbility m_popupLikeAbility;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	private void ShowLikeAbility()
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitForClosingAmbition(GameObject popupLikeAbility)
	{
		return null;
	}

	private void AutoClose()
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitForAutoClose()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
