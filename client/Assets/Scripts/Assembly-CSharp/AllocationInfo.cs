using System.Collections.Generic;
using Caocao;

public class AllocationInfo
{
	public UnitInfo unitInfo;

	public UnitSide side;

	public List<CmdIdx> cmdidxes;

	public int count
	{
		get
		{
			return 0;
		}
	}

	public AllocationInfo()
	{
	}

	public AllocationInfo(UnitInfo unitInfo, UnitSide side, CmdIdx idx)
	{
	}

	public AllocationInfo(int unitId, UnitSide side, CmdIdx idx)
	{
	}

	public void AddCmdIdx(AllocationInfo another)
	{
	}

	public override string ToString()
	{
		return null;
	}

	public override int GetHashCode()
	{
		return 0;
	}

	public override bool Equals(object obj)
	{
		return false;
	}
}
