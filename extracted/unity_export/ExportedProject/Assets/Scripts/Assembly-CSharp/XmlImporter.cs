using System;
using System.Collections.Generic;
using System.Xml;
using Caocao;
using CodeStage.AntiCheat.ObscuredTypes;
using JetBrains.Annotations;

public class XmlImporter
{
	public class Cache
	{
		public static void Clear()
		{
		}

		public static void Remove(string key)
		{
		}

		public static void Set(string key, EventCmdBase root)
		{
		}

		public static bool Get(string key, ref EventCmdBase rootCmd)
		{
			return false;
		}
	}

	private static int totalCmdCnt;

	private static int importedCmdCnt;

	private static string progressPopupTitle;

	private static bool showProgressbar;

	public static bool VerboseImportLog;

	private static Dictionary<UnitInfo, UnitSide> unitInfo2Sides;

	private static List<int> ms_emptyIntList;

	private XmlImporter()
	{
	}

	public static void NotifyImporingCmd(EventCmdBase cmd)
	{
	}

	public static bool Import(string fileName)
	{
		return false;
	}

	public static bool ImportTutorial(string fileName)
	{
		return false;
	}

	public static bool ImportHelp(string fileName)
	{
		return false;
	}

	public static List<EventCmdBase> GetEventBases(string fileName, EnumData.EventCellTypes cellType)
	{
		return null;
	}

	public static bool Import(string fileName, List<EventScene> scenes, out string eventFileName)
	{
		eventFileName = null;
		return false;
	}

	public static bool ImportRaw(string fileName, ref EventCmdBase outRootCmd, out string eventFileName, bool showProgress = false, bool callFromEventEditor = false)
	{
		eventFileName = null;
		return false;
	}

	public static bool CheckEventScipt(string fileName)
	{
		return false;
	}

	public static void AddJoiningUnit(UnitInfo unitInfo, UnitSide side)
	{
	}

	public static ForceFlag GetJoiningUnitSide(UnitInfo unitInfo)
	{
		return (ForceFlag)0;
	}

	private static XmlNode SelectNode(XmlNode node, string tagName)
	{
		return null;
	}

	private static XmlNodeList SelectNodes(XmlNode node, string tagName)
	{
		return null;
	}

	private static XmlNode SelectAttribute(XmlNode node, string attrNmae)
	{
		return null;
	}

	public static bool ParseAttr([NotNull] XmlNode node, string attrName, ref int dest, int defVal = 0)
	{
		return false;
	}

	public static bool ParseAttr([NotNull] XmlNode node, string attrName, ref string dest, string defVal = null)
	{
		return false;
	}

	public static bool ParseAttr([NotNull] XmlNode node, string attrName, ref float dest, float defVal = 0f)
	{
		return false;
	}

	public static bool ParseAttr([NotNull] XmlNode node, string attrName, ref bool dest, bool defVal = false)
	{
		return false;
	}

	public static bool ParseAttrEnum<T>([NotNull] XmlNode node, string attrName, ref T dest) where T : struct, IConvertible, IFormattable, IComparable
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref byte dest, byte defVal = 0)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref int dest, int defVal = 0)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref ObscuredInt dest, int defVal = 0)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref string dest, string defVal = null)
	{
		return false;
	}

	public static bool ParseEnum<T>([NotNull] XmlNode node, ref T dest) where T : struct, IConvertible, IFormattable, IComparable
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref float dest, float defVal = 0f)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref bool dest, bool defVal = false)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref IntVector2 dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref IntRect dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref EventCmdBase.ImportantArea dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref UnitInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref SlotInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref TargetInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref EventCmdBase.SaveFlagInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref EventCmdBase.GameObjectActivationInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref EventCmdBase.LabelChangeInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref EventCmdBase.LabelColorChangeInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref SpawnInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref MapObjectInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref GuildSiegeDefenceSlotInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref SpawnUnitInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, ref GuildSiegeDefenceObjInfo dest)
	{
		return false;
	}

	public static bool ParseTemplate<T>([NotNull] XmlNode node, ref T dest) where T : IEventXmlSerializable
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref byte dest, byte defVal = 0)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref int dest, int defVal = 0)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref ObscuredInt dest, int defVal = 0)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref string dest, string defVal = null)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref float dest, float defVal = 0f)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref bool dest, bool defVal = false)
	{
		return false;
	}

	public static bool ParseEnum<T>([NotNull] XmlNode node, string tagName, ref T dest) where T : struct, IConvertible, IFormattable, IComparable
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref IntVector2 dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref IntRect dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref UnitInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref SlotInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref TargetInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref EventCmdBase.SaveFlagInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref EventCmdBase.GameObjectActivationInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref SpawnInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref MapObjectInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref GuildSiegeDefenceSlotInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref SpawnUnitInfo dest)
	{
		return false;
	}

	public static bool Parse([NotNull] XmlNode node, string tagName, ref GuildSiegeDefenceObjInfo dest)
	{
		return false;
	}

	public static bool ParseIntList(XmlNode node, string tagName, List<int> res)
	{
		return false;
	}
}
