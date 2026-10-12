using System;
using System.Xml;
using LitJson;

[Serializable]
public class EventCmd632 : EventCmdBase
{
	public static readonly string ms_code;

	public bool bEnable;

	public int Variable;

	public bool useIntValue;

	public EnumData.ArithmeticOperations calcuType;

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

	private int GetCalculationValue()
	{
		return 0;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
