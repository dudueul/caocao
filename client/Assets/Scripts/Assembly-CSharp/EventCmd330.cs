using System;
using System.Xml;
using LitJson;

[Serializable]
public class EventCmd330 : EventCmdBase
{
	public static readonly string ms_code;

	public int saveFlagId;

	public int saveFlagValue;

	public int mailId;

	public int senderUnitId;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	private void RequestSavenFlagChanged()
	{
	}

	private void OnFlagSaveResult(JsonData response)
	{
	}

	private void RequestSendEventMail()
	{
	}

	private void OnSendEventMail(JsonData response)
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
