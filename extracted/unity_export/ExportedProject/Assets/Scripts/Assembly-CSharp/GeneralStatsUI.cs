using UnityEngine;

public class GeneralStatsUI : MonoBehaviour
{
	private GeneralStat m_generalStat;

	public UserUnit userUnit { get; private set; }

	public Unit unitData { get; private set; }

	public void SetUserUnit(UserUnit unit)
	{
	}

	private void UpdateUI()
	{
	}

	private int GetStatValue(EnumData.GeneralStatType statType)
	{
		return 0;
	}
}
