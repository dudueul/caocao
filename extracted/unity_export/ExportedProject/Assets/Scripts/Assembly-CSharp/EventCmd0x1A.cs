using System;
using System.Xml;

[Serializable]
public class EventCmd0x1A : EventCmdBase
{
	public static readonly string ms_code;

	public string m_sStringTemp;

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
