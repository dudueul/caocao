using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd0x1C : EventCmdBase
{
	public static readonly string ms_code;

	private GameObject battleTitleObj;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	private string GetBattleTitlePath(EnumData.BattleMode battleMode)
	{
		return null;
	}

	private void SetBattleTitle(EnumData.BattleMode battleMode)
	{
	}

	[DebuggerHidden]
	private IEnumerator Wait()
	{
		return null;
	}

	private void ActivateBattleStart()
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitBattleTitleEnd()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
