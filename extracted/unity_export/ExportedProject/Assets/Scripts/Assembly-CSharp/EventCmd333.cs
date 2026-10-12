using System;
using System.Xml;

[Serializable]
public class EventCmd333 : EventCmdBase
{
	public static readonly string ms_code;

	public string m_sStringTemp;

	public bool m_UICameraEventOn;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	private void OnCompleted()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
