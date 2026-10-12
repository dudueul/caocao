public class ShortCutFocusUi : ShortCutDefault
{
	public ShortCutFocusUi(EnumData.ShortCutType shortCutTypeId, int detail)
	{
	}

	public override bool ShortCut()
	{
		return false;
	}

	public override bool CheckCondition()
	{
		return false;
	}

	private void ShowFailedShortCutPopup()
	{
	}

	private void FocusBattleField(int detail)
	{
	}

	private bool FocusStorybookPopup(int detail)
	{
		return false;
	}

	private bool FocusUnitTreeNode(int detail)
	{
		return false;
	}

	private bool FocusUnitTreePage(int detail)
	{
		return false;
	}

	private void FocusCloud(int quickDetail)
	{
	}

	private void FocusTech(int detail)
	{
	}

	private void FocusUnitInfo(int detail)
	{
	}

	private bool FocusInstanceNormal(int detail)
	{
		return false;
	}

	private bool FocusInstanceSpecial(int detail)
	{
		return false;
	}

	private void OnInstanceSpecialSceneLoaded()
	{
	}

	private bool FocusInstanceLimit(int detail)
	{
		return false;
	}

	private bool FocusTown(int detail)
	{
		return false;
	}
}
