using System;
using System.Xml;

[Serializable]
public class EventCmd0x08 : EventCmdBase
{
	public static readonly string ms_code;

	public bool m_bFlagTemp;

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
