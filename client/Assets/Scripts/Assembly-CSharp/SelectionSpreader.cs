using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class SelectionSpreader : SceneSingleton<SelectionSpreader>
{
	public enum SpreadType
	{
		AtOnce = 0,
		Spread = 1
	}

	public enum Selection
	{
		Move = 0,
		Attack = 1,
		Artifice = 2,
		Item = 3,
		AttackRange = 4,
		MultiTarget = 5
	}

	private class Dist2GridIdx
	{
		public Selection selection;

		public List<IntVector2> grids;

		public List<bool> artificeAvailables;

		public List<IntVector2> multiTargetBasePos;

		public Dictionary<int, List<int>> m_dist2gridIdx;

		public int maxDist { get; private set; }

		public Dist2GridIdx(int dist, int gridIdx)
		{
		}

		public void ClearSelectionData()
		{
		}

		public void AddSelectionData(int dist, int gridIdx)
		{
		}
	}

	private class SelectionComparer : IEqualityComparer<Selection>
	{
		bool IEqualityComparer<Selection>.Equals(Selection x, Selection y)
		{
			return false;
		}

		int IEqualityComparer<Selection>.GetHashCode(Selection obj)
		{
			return 0;
		}
	}

	private SpreadType m_spreadType;

	private GameObject m_unitObj;

	private Dictionary<Selection, Dist2GridIdx> dist2GridBySelection;

	private Coroutine animateCoroutine;

	public SpreadType spreadType
	{
		get
		{
			return SpreadType.AtOnce;
		}
		set
		{
		}
	}

	public void SetGrids(Selection selection, List<IntVector2> grids)
	{
	}

	public void SetGrids(Selection selection, List<IntVector2> grids, List<bool> artificeAvailables)
	{
	}

	public void SetGrids(Selection selection, List<IntVector2> grids, List<IntVector2> multiTargetBasePos)
	{
	}

	private void AddSelectionData(Selection selection, int dist, int gridIdx)
	{
	}

	public void AddSelectionData(Selection selection, int dist, int gridIdx, List<IntVector2> grids)
	{
	}

	public void AddSelectionData(Selection selection, int dist, int gridIdx, List<IntVector2> grids, List<bool> artificeAvailables)
	{
	}

	public void AddSelectionData(Selection selection, int dist, int gridIdx, List<IntVector2> grids, List<IntVector2> multiTargetBasePos)
	{
	}

	private void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void ClearSelectionData()
	{
	}

	public void Spread(GameObject unitObj)
	{
	}

	public void Spread(GameObject unitObj, SpreadType spreadType)
	{
	}

	[DebuggerHidden]
	private IEnumerator SpreadImpl()
	{
		return null;
	}

	public void Stop()
	{
	}

	public void Hide()
	{
	}

	[DebuggerHidden]
	private IEnumerator Animate()
	{
		return null;
	}

	private void MarkTileAs(Selection selection, IntVector2 gridPos, bool boolArg)
	{
	}

	private SpreadType GetProperSpreadType()
	{
		return SpreadType.AtOnce;
	}

	public void OnStatusChanged(ControlFlow.statusType type)
	{
	}
}
