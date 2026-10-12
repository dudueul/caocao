using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd0x2A : EventCmdBase
{
	public static readonly string ms_code;

	public IntVector2 vPos;

	public int unitID;

	public float delay;

	public string editorUnitName;

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
	private IEnumerator _MoveObject(GameObject faceObj)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
