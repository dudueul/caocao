using System;
using System.Xml;

[Serializable]
public class EventCmd0x23 : EventCmdBase
{
	public static readonly string ms_code;

	public string m_sStringTemp;

	public int PositiveVariable1;

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
