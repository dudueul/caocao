using System;
using BattlePlay;
using UnityEngine;

public class UIBrawlChangeUnitListItem : MonoBehaviour
{
	[SerializeField]
	private UITexture faceTexture;

	[SerializeField]
	private UILabelLocalized unitName;

	[SerializeField]
	private UISlider[] unitResSliders;

	[SerializeField]
	private UIGrid conditionGrid;

	[SerializeField]
	private UIGrid infoGrid;

	[SerializeField]
	private UIButton changeBtn;

	[SerializeField]
	private UILabelLocalized descLabel;

	private BattleUnit battleUnitData;

	public Action<BattleUnit> changeCallBackFunc;

	public Action<UnitCondition> clickConditionItem;

	public void UpdateInfoTypeUI(BattleUnit battleUnit)
	{
	}

	public void UpdateTagTypeUI(BattleUnit battleUnit)
	{
	}

	private void UpdateUI(BattleUnit battleUnit)
	{
	}

	public void OnBtnChange()
	{
	}

	private void GaugeSetting(UISlider gauge, bool active, UserUnit unit, EnumData.UnitResType res)
	{
	}
}
