using System.Collections.Generic;
using UnityEngine;

public class StorybookGradeRewardsList : CaocaoUIModalPopup
{
	[SerializeField]
	private Transform finalRewardUIParent;

	[SerializeField]
	private UIGrid grid;

	[SerializeField]
	private UILabelLocalized finalRewardName;

	[SerializeField]
	private UILabelLocalized gradesState;

	[SerializeField]
	private UIButton closeBtn;

	private static int m_currentGrades;

	private static int m_finalGrades;

	private static Dictionary<int, RewardInfo> m_grade2Rewards;

	protected override void OnDestroy()
	{
	}

	public static void Show(int currentGrades, int finalGrades, Dictionary<int, RewardInfo> grade2Rewards)
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
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
}
