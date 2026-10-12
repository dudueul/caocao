using System;
using System.Xml;
using BattleRecorder;
using Caocao;
using Thingsoft.Pool;

[Serializable]
public sealed class TargetInfo : IEventXmlSerializable, IEventEEXSerializable, IWritable
{
	public EnumData.TargetRange targetRange;

	public ForceFlag forceFlags;

	public ForceFlag targetSide;

	public int unitID;

	public string nick;

	public int bfIndex;

	public int bfIndexFrom;

	public int bfIndexTo;

	public int bfGroup;

	public IntVector2 startPos;

	public IntVector2 endPos;

	public int slotNumber;

	public int coordIndex;

	public int jobType;

	public byte key
	{
		get
		{
			return 0;
		}
	}

	public TargetInfo()
	{
	}

	public TargetInfo(TargetInfo src)
	{
	}

	public TargetInfo(int unitID, string nick)
	{
	}

	public TargetInfo(UnitInfo src)
	{
	}

	public void ImportFromXml(XmlNode xmlNode)
	{
	}

	public override string ToString()
	{
		return null;
	}

	public void GetUnits(Thingsoft.Pool.UnitData res)
	{
	}

	public UnitData GetUnit()
	{
		return null;
	}

	public UserUnit GetUserUnitForcely()
	{
		return null;
	}

	public Unit GetUnitForcely()
	{
		return null;
	}

	public bool IsTalkableTargetByCtx(UnitData data)
	{
		return false;
	}

	public int GetTalkableUnitIdByCtx(UnitData data)
	{
		return 0;
	}

	public string GetTalkableUnitNickByCtx(UnitData data)
	{
		return null;
	}

	public int GetFaceIdByCtx(int unitId, UnitData unitData)
	{
		return 0;
	}

	public static TargetInfo Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public void Write(OptimizedBinaryWriter w)
	{
	}

	private void Convert(int unitID, string nick)
	{
	}

	private bool IsBFIndex(int unitID)
	{
		return false;
	}

	private bool IsGroupID(int unitID)
	{
		return false;
	}

	private bool IsUnitID(int unitID)
	{
		return false;
	}

	public void SetBFIndex(int unitID, UnitSide side = UnitSide.OWN)
	{
	}

	public void SetGroup(int groupId)
	{
	}

	private void SetArea(IntVector2 startPos, IntVector2 endPos)
	{
	}

	public void SetAreaWithGroupId(int groupId, IntVector2 startPos, IntVector2 endPos)
	{
	}

	public void SetAreaWithForceType(EnumData.ForceType forceType, IntVector2 startPos, IntVector2 endPos)
	{
	}

	public void SetAreaWithForceFlgBit(int bitwise, IntVector2 startPos, IntVector2 endPos)
	{
	}

	public void SetAreaWithUnitSide(UnitSide side, IntVector2 startPos, IntVector2 endPos)
	{
	}

	public void SetCharID(int unitID, string nick)
	{
	}

	public void SetForceFlag(UnitSide side)
	{
	}
}
