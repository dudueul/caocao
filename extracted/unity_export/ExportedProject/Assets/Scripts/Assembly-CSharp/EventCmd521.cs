using System;
using System.Collections.Generic;
using System.Xml;

[Serializable]
public class EventCmd521 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public EnumData.DialogType emoticonType;

	public float waitingSeconds;

	public bool isWaitDestroy;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	public override void _Apply()
	{
	}

	public override void _Revert()
	{
	}

	private void ShowEmoticon(List<UnitData> unitDatas, EnumData.DialogType dialogType)
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
