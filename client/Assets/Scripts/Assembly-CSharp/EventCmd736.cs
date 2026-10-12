using System;
using System.Xml;

[Serializable]
public class EventCmd736 : EventCmdBase
{
	public static readonly string ms_code;

	public bool checkBattleMode;

	public EnumData.BattleMode battleMode;

	public bool useUserSpell;

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
