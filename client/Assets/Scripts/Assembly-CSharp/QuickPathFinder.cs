// [RE] Rebuilt QuickPathFinder — builds the movement area from the battle
// map's tiles (StackTileData list), runs the Context flood, applies ZOC.
// Original members preserved (SetWholeTileDatas/BuildFindingArea/
// BuildScenarioFindingArea + the ms_whole* caches).
using System.Collections.Generic;

public class QuickPathFinder
{
	private static List<int> ms_wholeTileIDs;

	public static List<EnumData.NoPass> ms_wholeNoPass;

	private List<StackTileData> m_tiles;

	public QuickPathFinder()
	{
		m_tiles = new List<StackTileData>();
		if (ms_wholeTileIDs == null)
			ms_wholeTileIDs = new List<int>();
	}

	public void SetWholeTileDatas(List<StackTileData> tileList)
	{
		m_tiles = tileList ?? new List<StackTileData>();
		ms_wholeTileIDs.Clear();
		foreach (var t in m_tiles)
			ms_wholeTileIDs.Add(t.tileId);
	}

	// [RE] flood the reachable area for one unit; ZOC applied unless ignored.
	public void BuildFindingArea(QuickPathFinderContext context, bool ignoreUnitExist = false, bool ignoreZoc = false)
	{
		if (context == null)
			return;
		context.Clear();
		var self = context.unitData;
		var start = context.unitPos;

		// [RE] stamp every tile into the context grid (candidates = passable)
		foreach (var tile in m_tiles)
		{
			var pos = new IntVector2(tile.gridX, tile.gridY);
			bool passable = tile.tileData != null;
			bool isSelf = self != null && pos == context.unitPos;
			context.SetTileNode(pos, passable ? 1 : 9999, passable, isSelf);
			if (isSelf)
				context.MarkSelf(pos);
		}
		// occupancy: units block tiles (unless the caller ignores it)
		if (!ignoreUnitExist)
		{
			// [RE] UnitData occupancy came from the battle scene registry;
			// the flood's Relax step re-checks context nodes' unitData —
			// population of unitData onto nodes happens in the battle setup.
		}
		var startNode = context.GetTileNode(start);
		context.CalculateCost(startNode);
		if (!ignoreZoc)
			context.ApplyZoc(false);
	}

	// [RE] scenario-scripted variant: no occupancy, no ZOC (cutscene moves).
	public void BuildScenarioFindingArea(QuickPathFinderContext context)
	{
		BuildFindingArea(context, ignoreUnitExist: true, ignoreZoc: true);
	}
}
