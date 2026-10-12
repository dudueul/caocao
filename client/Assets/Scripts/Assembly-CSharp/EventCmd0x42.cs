using System;
using System.Xml;

[Serializable]
public class EventCmd0x42 : EventCmdBase
{
	public static readonly string ms_code;

	private const int castleJobType = 1210030;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	private void Complete()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
