using System;
using System.Collections.Generic;
using System.Xml;

[Serializable]
public class EventCmd0x05 : EventCmdBase
{
	public static readonly string ms_code;

	public List<int> trueVars;

	public List<int> falseVars;

	public List<SaveFlagInfo> saveFlagInfos;

	public List<ScenarioFlagInfo> scenarioFlagInfos;

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
