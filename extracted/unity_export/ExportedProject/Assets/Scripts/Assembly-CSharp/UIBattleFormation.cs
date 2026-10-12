using System.Collections.Generic;
using UnityEngine;

public class UIBattleFormation : MonoBehaviour
{
	public PrefabInstance[] tilesParents;

	public Transform grid;

	public UILabelLocalized nameLabel;

	public UILabelLocalized levelLabel;

	public UILabelLocalized combatPowerLabel;

	private UIBattleFormationPreset m_selectedPreset;

	private List<UIBattleFormationTile> tiles;

	private UserBattleFormation m_userBattleFormation;

	private UserUnitGroup m_userUnitGroup;

	private bool m_useBigNumber;

	private bool m_hideLevel;

	public void Init(UserBattleFormation userBattleFormation, UserUnitGroup userUnitGroup, bool useBigNumber = true, bool hideLevel = false)
	{
	}

	private void OnEnable()
	{
	}

	private void OnDestroy()
	{
	}

	public void UpdateBattleFormationPresets()
	{
	}

	public void UpdateUI()
	{
	}

	public int CalculateCombatPower()
	{
		return 0;
	}

	public void UpdateUnits()
	{
	}

	private void OnSelectPreset(UIBattleFormationPreset selectedPreset)
	{
	}
}
