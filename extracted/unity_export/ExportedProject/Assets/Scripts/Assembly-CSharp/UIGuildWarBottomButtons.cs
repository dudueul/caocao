using System;
using System.Collections.Generic;
using GuildWar;
using UnityEngine;

public class UIGuildWarBottomButtons : MonoBehaviour
{
	public UIGrid Artifice;

	public List<UIGuildWarBottomButton> Artifices;

	public GameObject Controll;

	public GameObject BtnSiegeWeapon;

	public GameObject BtnSiegeWeaponCancel;

	public GameObject BtnAttack;

	public GameObject BtnMove;

	public GameObject SkillCancel;

	public GameObject ArtificeDesc;

	public GameObject Spawn;

	public GameObject SelectBuilding;

	public GameObject SpawnBtnCancel;

	private CorpsData selectedCorpsData;

	private bool prevIsSiegeWeapon;

	private bool _isInit;

	private bool _isHideAll;

	private void Start()
	{
	}

	public void HideAll()
	{
	}

	public void ShowCancelBtn()
	{
	}

	public void ShowBuildingCameraMoveBtn(Action SelectFunc)
	{
	}

	public void ShowCancelBtn(GuildWarCombatActiveFunction activeFunction)
	{
	}

	private void UpdateMainButton(bool checkArtificeDescObject = true)
	{
	}

	public void SelectCorp(bool IsMyCorps)
	{
	}

	private void Update()
	{
	}

	private void UpdateUI(bool checkArtificeDescObject)
	{
	}

	private long GetPassiveEndTimeAndLockEndTimeWithCustomActionType(ECorpsCustomActionType actionType, out long lockEndTime)
	{
		lockEndTime = default(long);
		return 0L;
	}

	private long GetPassiveEndTimeAndLockEndTimeWithFunction(EFunctionCategory functionCategory, out long lockEndTime)
	{
		lockEndTime = default(long);
		return 0L;
	}

	private void ChangeFunctionButtons()
	{
	}

	private void ChangeCustomFunctionButtons()
	{
	}
}
