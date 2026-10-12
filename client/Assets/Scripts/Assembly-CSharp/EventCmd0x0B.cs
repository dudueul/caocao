using System;
using System.Xml;
using LitJson;

[Serializable]
public class EventCmd0x0B : EventCmdBase
{
	public static readonly string ms_code;

	public bool bEnable;

	public int Variable;

	public bool useIntValue;

	public bool useIntVariation;

	public int intValue;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	private void OnFlagSaveResult(JsonData response)
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
