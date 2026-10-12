using System;
using System.Xml;

[Serializable]
public class EventCmd0x2C : EventCmdBase
{
	public static readonly string ms_code;

	public string Description;

	public bool bOption1;

	public bool bOption2;

	public bool bOption3;

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
