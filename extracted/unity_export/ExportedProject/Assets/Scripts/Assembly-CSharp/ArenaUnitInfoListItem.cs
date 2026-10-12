using System;
using UnityEngine;

public class ArenaUnitInfoListItem : MonoBehaviour
{
	private Action<int> onSelect;

	public UserArenaUnit arenaUnit { get; private set; }

	public int index { get; private set; }

	public void Initiate(UserArenaUnit arenaUnit, int idx, Action<int> selectCallback)
	{
	}

	public void UpdateUI()
	{
	}

	public void SetSelect(bool select)
	{
	}

	private void OnDeserializeArenaUnit()
	{
	}

	public void ShowUnitInfo()
	{
	}

	private void OnDestroy()
	{
	}
}
