using System;
using System.Xml;

[Serializable]
public class EventCmd820 : EventCmdBase
{
	public enum EventPopupAction
	{
		Show = 0,
		Close = 1
	}

	public static readonly string ms_code;

	public string path;

	public EventPopupAction popupAction;

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
