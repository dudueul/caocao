using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using UnityEngine;

[Serializable]
public class EventCmd328 : EventCmdBase
{
	public static readonly string ms_code;

	public string targetScrollViewPath;

	public string targetObjectPath;

	public int x;

	public int y;

	public int aniTime;

	public bool waitAnimFinish;

	private Transform target;

	private ScrollViewMoveTween tweenAnim;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitTweenAnimComplete()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
