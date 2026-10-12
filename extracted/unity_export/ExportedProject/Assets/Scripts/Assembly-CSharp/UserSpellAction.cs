using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using BattlePlay;
using Caocao;

public class UserSpellAction : SceneSingleton<UserSpellAction>
{
	public Action applySpellCallBack;

	private bool applyOwn;

	private int castUnitId;

	private UserSpell ctrlUserSpell;

	private List<UnitData> targetUnitDatas;

	private int curSelectTargetIndex;

	public void Action(int pdUserSpellId)
	{
	}

	public void Action(UserSpellControl ctrlInfo)
	{
	}

	public void SelectUnit(UnitData selectUnitData)
	{
	}

	public int GetActionSpellId()
	{
		return 0;
	}

	public UnitSide GetEmitTargetSide()
	{
		return UnitSide.OWN;
	}

	public int GetTargetUnitId()
	{
		return 0;
	}

	public List<UnitData> GetTargetUnits()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator Apply()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator FaceEffect()
	{
		return null;
	}

	private void CallBackApplySpell()
	{
	}

	public void ResetData()
	{
	}

	private bool CheckErrorSelectUnit(UnitData selectUnitData)
	{
		return false;
	}

	private UnitSide GetTagetSide()
	{
		return UnitSide.OWN;
	}

	private void SetTargetList(BattleControl pvpCtrlInfo)
	{
	}

	protected override void OnDestroy()
	{
	}
}
