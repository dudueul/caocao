using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using System.Xml;
using Caocao;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;
using UnityEngine;

[Serializable]
public class EventCmdBase : IEventEEXSerializable, IEventXmlSerializable
{
	[Serializable]
	public struct Dialog
	{
		public int faceType;

		public EnumData.DialogType typeParam;

		public TargetInfo targetUnitInfo;

		public string text;

		public bool waiting;

		public bool useSide;

		public UnitSide side;

		public EnumData.DialogVoice voice;

		public bool forbidBattleCamMove;

		public EnumData.DialogPortraitPosition direct;
	}

	[Serializable]
	[StructLayout((LayoutKind)0, Size = 16)]
	public struct ImportantArea : IEventXmlSerializable
	{
		public IntVector2 vStartPos;

		public IntVector2 vEndPos;

		public void ImportFromXml(XmlNode xmlNode)
		{
		}
	}

	[Serializable]
	[StructLayout((LayoutKind)0, Size = 40)]
	public struct MapVariable
	{
		public bool bFlag1;

		public bool bFlag2;

		public int nCount;

		public int nGrade;

		public int nEnymyValor;

		public int nEnemyLeaderID;

		public int nOwnValor;

		public int nOwnLeaderID;

		public int nWeatherIndex;

		public int nWeatherEventIndex;
	}

	[Serializable]
	[StructLayout((LayoutKind)0, Size = 12)]
	public struct SaveFlagInfo : IEventXmlSerializable
	{
		public int id;

		public int value;

		public EnumData.CompareSign sign;

		public SaveFlagInfo(int id, int value, EnumData.CompareSign sign = EnumData.CompareSign.EQUAL)
		{
			this.id = 0;
			this.value = 0;
			this.sign = EnumData.CompareSign.GREATER_THAN_OR_EQUAL_TO;
		}

		public void ImportFromXml(XmlNode xmlNode)
		{
		}
	}

	[Serializable]
	public class ScenarioFlagInfo : IEventXmlSerializable
	{
		public int id;

		public int value;

		public EnumData.CompareSign sign;

		public ScenarioFlagInfo(int id, int value, EnumData.CompareSign sign = EnumData.CompareSign.EQUAL)
		{
		}

		public void ImportFromXml(XmlNode xmlNode)
		{
		}
	}

	[Serializable]
	public class LabelColorChangeInfo : IEventXmlSerializable
	{
		public string path;

		public Color color;

		public LabelColorChangeInfo()
		{
		}

		public LabelColorChangeInfo(string path, Color color)
		{
		}

		public void ImportFromXml(XmlNode xmlNode)
		{
		}
	}

	[Serializable]
	public class LabelChangeInfo : IEventXmlSerializable
	{
		public string path;

		public string text;

		public LabelChangeInfo()
		{
		}

		public LabelChangeInfo(string path, string text)
		{
		}

		public void ImportFromXml(XmlNode xmlNode)
		{
		}
	}

	[Serializable]
	[StructLayout((LayoutKind)0, Size = 16)]
	public struct GameObjectActivationInfo : IEventXmlSerializable
	{
		public string path;

		public bool enable;

		public GameObjectActivationInfo(string path, bool enable)
		{
			this.path = null;
			this.enable = false;
		}

		public void ImportFromXml(XmlNode xmlNode)
		{
		}
	}

	[Serializable]
	[StructLayout((LayoutKind)0, Size = 48)]
	public struct RewardItemInfo
	{
		public ObscuredInt id;

		public ObscuredInt amount;

		public ObscuredInt saveFlag;

		public ObscuredInt setId;

		public RewardItemInfo(int id, int amount, int saveFlag, int setId)
		{
			this.id = default(ObscuredInt);
			this.amount = default(ObscuredInt);
			this.saveFlag = default(ObscuredInt);
			this.setId = default(ObscuredInt);
		}

		public void Serialize(JsonWriter writer)
		{
		}
	}

	[Serializable]
	[StructLayout((LayoutKind)0, Size = 8)]
	public struct UnitSaveFlag : IEventXmlSerializable
	{
		public int unitId;

		public int saveFlag;

		public UnitSaveFlag(int id, int saveFlag)
		{
			unitId = 0;
			this.saveFlag = 0;
		}

		public void ImportFromXml(XmlNode xmlNode)
		{
		}
	}

	public enum EventPrecedence
	{
		아군단계 = 0,
		우군단계 = 1,
		적군단계 = 2
	}

	public enum EventActionAI
	{
		피동출격 = 0,
		주동출격 = 1,
		견수원지 = 2,
		공격무장 = 3,
		도착지점 = 4,
		동행무장 = 5,
		퇴각지정점 = 6,
		최적_VIP = 7,
		전체_목적 = 8,
		전체_타겟 = 9,
		지형지물 = 10,
		도망 = 11,
		TotalNum = 12
	}

	[NonSerialized]
	public EventCmdBase parent;

	[NonSerialized]
	public EventCmdBase lastFailed;

	[NonSerialized]
	public List<EventCmdBase> children;

	private static int lastSrl;

	public bool enable;

	public string summary;

	protected static int lastMapID;

	private static Dictionary<EnumData.EventCellTypes, Func<EventCmdBase>> ms_cellType2InstanceType;

	public static string[] lEventExtentList;

	public static string[] lEventExtentExList;

	public static string[] lEventTargetArea;

	public static string[] lEventAttackArea;

	public static string[] lEventImportantGrade;

	public static string[] lEventGoal;

	public static string[] lEventWeather;

	public static string[] lEventLevel;

	public bool isCompleted { get; set; }

	public bool isFailed { get; set; }

	public bool isRunning
	{
		get
		{
			return false;
		}
		private set
		{
		}
	}

	public bool isFinished { get; set; }

	public int idx { get; private set; }

	public int srl { get; set; }

	public EventCmdBase sibling
	{
		get
		{
			return null;
		}
	}

	protected EventCmdBase sameTypeSibling
	{
		get
		{
			return null;
		}
	}

	protected static bool IsInside(int value, int min, int max)
	{
		return false;
	}

	protected static bool IsOutSide(int value, int min, int max)
	{
		return false;
	}

	protected EventCmdBase Seek(int idxDelta)
	{
		return null;
	}

	protected EventCmdBase GetNextSibling(Type cmdType, int range)
	{
		return null;
	}

	protected EventCmdBase GetPrevSibling(Type cmdType, int range)
	{
		return null;
	}

	public virtual EventCmdBase Next()
	{
		return null;
	}

	public List<EventCmdBase> Find(Type type)
	{
		return null;
	}

	public List<EventCmdBase> Find(Func<EventCmdBase, bool> filter)
	{
		return null;
	}

	public virtual void Run()
	{
	}

	public virtual void Skip()
	{
	}

	public virtual void _Apply()
	{
	}

	public virtual bool _ApplyOnLoading()
	{
		return false;
	}

	public virtual void _Revert()
	{
	}

	protected void Fail()
	{
	}

	public void InitStatus()
	{
	}

	public EventCmdBase AddChild(EventCmdBase eventCmd)
	{
		return null;
	}

	public virtual int GetRouteIndex()
	{
		return 0;
	}

	public static void GetEventBasesFromXml(XmlNode xmlNode, List<EventCmdBase> extractedCmds, EnumData.EventCellTypes cellType)
	{
	}

	public void ImportFromXml(XmlNode xmlNode)
	{
	}

	protected virtual void ImportXmlData(XmlNode xmlNode)
	{
	}

	private static EventCmdBase CreateCmd(EnumData.EventCellTypes type)
	{
		return null;
	}

	public static bool IsMatchedUnitSide(int nGroupIndex, UnitSide eSide)
	{
		return false;
	}

	public static bool IsUnitGroup(int nUnitID)
	{
		return false;
	}

	public static bool IsUnitID(int nUnitID)
	{
		return false;
	}

	public static bool IsMatchedGroupSide(int nUnitID, UnitSide eSide)
	{
		return false;
	}

	public static Vector2 ConvertPositionFromOriginPos(Vector2 vPos)
	{
		return default(Vector2);
	}
}
