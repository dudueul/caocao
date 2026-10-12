public class ShortCutToMarket : ShortCutDefault
{
	private EnumData.ShopMode shopMode;

	public ShortCutToMarket(EnumData.ShopMode shopMode)
	{
	}

	public override bool ShortCut()
	{
		return false;
	}

	protected void DeactivateGOForFullScreenPanelUI()
	{
	}

	protected void ActivateGOForFullScreenPanelUI()
	{
	}
}
