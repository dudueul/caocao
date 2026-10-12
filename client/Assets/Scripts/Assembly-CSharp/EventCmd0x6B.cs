using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd0x6B : EventCmdBase
{
	public static readonly string ms_code;

	public bool bOption;

	public IntVector2 vPos;

	public int nMagicIndex;

	public int nSkillId;

	public bool bSkillsfxPrefab;

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

	[DebuggerHidden]
	private IEnumerator WaitingForDestroying(GameObject fxObj)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
