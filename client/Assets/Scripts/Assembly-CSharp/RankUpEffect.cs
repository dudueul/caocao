using UnityEngine;

public class RankUpEffect : CaocaoUIModalPopup
{
	private enum RankUpType
	{
		LordRankUp = 0,
		SeasonPassLevelUp = 1
	}

	public static bool isCompleted;

	private static RankUpType rankUpType;

	private static int levelUpCount;

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

	public static void ShowRankUpEffect()
	{
	}

	public static void ShowSeasonPassLevelUpEffect(int levelUpCount)
	{
	}

	public static GameObject RankUpEffectCall()
	{
		return null;
	}

	public static void RegistRankUpEffect()
	{
	}

	private void ShowEffect()
	{
	}

	public void Closed()
	{
	}
}
