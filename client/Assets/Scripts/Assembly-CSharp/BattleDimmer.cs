using System.Collections.Generic;
using UnityEngine;

public class BattleDimmer : SceneSingleton<BattleDimmer>
{
	public enum DimmingState
	{
		Original = 0,
		Light = 1,
		Middle = 2,
		Dark = 3
	}

	private Dictionary<GameObject, DimmingState> m_unit2DimmingState;

	protected BattleDimmer()
	{
	}

	private void Start()
	{
	}

	public void UpdateExemptedUnitsFromDimming(List<UnitData> exemptedUnits)
	{
	}

	public void StartUnitDimming()
	{
	}

	private void StopUnitDimming()
	{
	}

	private void StopUnitDimmingByStatusType(ControlFlow.statusType statusType)
	{
	}
}
