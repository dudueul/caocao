using System.Collections.Generic;

public class MapCommonFunc
{
	public static List<IntVector2> GetClockwiseNearestPositions(IntVector2 baseGridPos, int loopCount, bool includeBasePos = false)
	{
		return null;
	}

	public static IntVector2 GetClosestMovableGridPos(IntVector2 from, IntVector2 targetPos, QuickPathFinderContext qpfContext, bool getParentData = false, bool includeDaed = false)
	{
		return default(IntVector2);
	}

	public static IntVector2 GetSettableGridPos(IntVector2 targetPos, bool getParentData = false, bool includeDaed = false)
	{
		return default(IntVector2);
	}

	public static IntVector2 GetSettableGridPosAsJobType(IntVector2 targetPos, int jobTypeId, bool getParentData = false, bool includeDaed = false)
	{
		return default(IntVector2);
	}

	private static IntVector2 GetSettableGridPosOnArea(IntVector2 targetPos, int offset, int jobTypeId = 0)
	{
		return default(IntVector2);
	}

	public static IntVector2 GetClosestMovableGridPosOnArea(IntVector2 from, IntVector2 targetPos, QuickPathFinderContext qpfContext, int offset)
	{
		return default(IntVector2);
	}

	public static int GetGridDistance(IntVector2 from, IntVector2 to)
	{
		return 0;
	}
}
