using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd0x2B : EventCmdBase
{
	public static readonly string ms_code;

	public UnitInfo unitInfo;

	public string editorUnitName;

	public float delay;

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

	private GameObject FindPortraitObject()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator _DisappearPortrait(GameObject faceObj)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
