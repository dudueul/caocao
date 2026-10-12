using System;
using System.Xml;

[Serializable]
public class EventCmd0x39 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public int nParam;

	public string editorUnitName;

	public override string ToString()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
