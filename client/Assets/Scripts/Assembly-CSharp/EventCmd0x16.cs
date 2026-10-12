using System;
using System.Xml;

[Serializable]
public class EventCmd0x16 : EventCmdBase
{
	public static readonly string ms_code;

	public string m_sStringTemp;

	public bool m_bFlagTemp;

	public bool m_UICameraEventOn;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	public override void Skip()
	{
	}

	private void OnCompleted()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
