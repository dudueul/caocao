using System;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd823 : EventCmdBase
{
	public static readonly string ms_code;

	public Color color;

	public int seconds;

	public bool wait;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	private void OnCompleteFadeOut()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
