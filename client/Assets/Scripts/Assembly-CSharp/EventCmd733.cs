using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using System.Xml;

[Serializable]
public class EventCmd733 : EventCmdBase
{
	[Serializable]
	[StructLayout((LayoutKind)0, Size = 8)]
	public struct BattleOptionInfo : IEventXmlSerializable
	{
		public EnumData.BattleOption battleOption;

		public bool onOff;

		public BattleOptionInfo(EnumData.BattleOption battleOption, bool onOff)
		{
			this.battleOption = EnumData.BattleOption.None;
			this.onOff = false;
		}

		public void ImportFromXml(XmlNode xmlNode)
		{
		}
	}

	public static readonly string ms_code;

	public List<BattleOptionInfo> battleOptionInfos;

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
