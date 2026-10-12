public class ShortCutShowPanel : ShortCutDefault
{
	public ShortCutShowPanel(EnumData.ShortCutType shortCutTypeId, int quickDetail = 0)
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

	private bool ShowUnitTreeNode()
	{
		return false;
	}
}
