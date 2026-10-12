using System;
using System.Xml;

[Serializable]
public class EventCmd827 : EventCmdBase
{
	public enum SwitchTarget
	{
		Lobby = 0,
		WorldMap = 1
	}

	public static readonly string ms_code;

	public SwitchTarget target;

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
