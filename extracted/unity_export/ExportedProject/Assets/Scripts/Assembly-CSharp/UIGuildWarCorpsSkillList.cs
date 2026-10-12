using System.Collections.Generic;
using UnityEngine;

public class UIGuildWarCorpsSkillList : CaocaoUIModalPopup
{
	[SerializeField]
	private UIReuseGrid skillReuseGrid;

	[SerializeField]
	private UILabelLocalized skillName;

	[SerializeField]
	private UILabelLocalized skillType;

	[SerializeField]
	private UILabelLocalized skillConsumeAction;

	[SerializeField]
	private UITexture HitRange;

	[SerializeField]
	private UITexture EffectRange;

	[SerializeField]
	private UILabelLocalized skillDesc;

	private GuildWarCorpsCombatActiveFunc.CorpsCombatActionFuncCellData SelectedCellData;

	private static UIGuildWarCorpsSkillList _self;

	private static void checkSelf()
	{
	}

	public static void ShowInfo(List<int> activeFunctionList)
	{
	}

	private void initData(List<int> activeFunctionList)
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	private void Start()
	{
	}

	private void Update()
	{
	}
}
