using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using Caocao;
using UnityEngine;

[Serializable]
public class EventCmd0x1F : EventCmdBase
{
	public enum TargetType
	{
		Position = 0,
		BfIndex = 1,
		CharIndex = 2
	}

	public static readonly string ms_code;

	public int param1;

	public int param2;

	public float delay;

	public bool wait;

	public iTween.EaseType wave;

	public TargetType targetType;

	public UnitSide side;

	public int bfIndex;

	public int charIndex;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator MoveBattleCameraView(float interval)
	{
		return null;
	}

	private Vector2 GetTargetPos()
	{
		return default(Vector2);
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
