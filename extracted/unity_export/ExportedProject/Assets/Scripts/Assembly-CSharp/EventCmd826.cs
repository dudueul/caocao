using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd826 : EventCmdBase
{
	public static readonly string ms_code;

	public string movieName;

	private GameObject moviePlayer;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator Delay()
	{
		return null;
	}

	private void OnMovieEnd()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
