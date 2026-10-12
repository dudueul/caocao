using System;
using System.Xml;
using LitJson;

[Serializable]
public class EventCmd0x11 : EventCmdBase
{
	public static readonly string ms_code;

	public int scenarioID;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	private void OnAddScenario(JsonData response)
	{
	}

	private void ApplyCmd()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
