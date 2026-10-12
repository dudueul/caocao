using System;
using System.Xml;

[Serializable]
public class EventCmd0x2D : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public string editorUnitName;

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
