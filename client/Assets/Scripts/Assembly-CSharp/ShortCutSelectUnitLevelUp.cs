using System;

public class ShortCutSelectUnitLevelUp : ShortCutDefault
{
	private Action _successCallback;

	public ShortCutSelectUnitLevelUp(EnumData.ShortCutType shortCutTypeId, int detail, Action successCallback)
	{
	}

	public override bool ShortCut()
	{
		return false;
	}

	private void FocusUnitSelect(int levelUpUseItemId)
	{
	}

	private void ShowFailedShortCutPopup()
	{
	}
}
