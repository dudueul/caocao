using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd529 : EventCmdBase
{
	public enum SpawnType
	{
		Area = 0,
		Unit = 1
	}

	public static readonly string ms_code;

	private const int diffusingFireMapObjectId = 4300383;

	public SpawnType spawnType;

	public TargetInfo targetUnitInfo;

	public IntVector2 pos;

	public IntVector2 range;

	public IntVector2 count;

	public int ignitionRate;

	public bool moveCamera;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator RunCoroutine()
	{
		return null;
	}

	private int GetNumIgnitions()
	{
		return 0;
	}

	private void MoveCamera(IntVector2 gridPos)
	{
	}

	[DebuggerHidden]
	private IEnumerator Waiting()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
