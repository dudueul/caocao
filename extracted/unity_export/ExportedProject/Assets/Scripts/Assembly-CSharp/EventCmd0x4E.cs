using System;
using System.Xml;

[Serializable]
public class EventCmd0x4E : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public int nAIIndex;

	public TargetInfo aiTargetUnitInfo;

	public IntVector2 vTargetPos;

	public bool originalPos;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
