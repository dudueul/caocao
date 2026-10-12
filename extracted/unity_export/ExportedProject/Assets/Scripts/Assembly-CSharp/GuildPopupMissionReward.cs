public class GuildPopupMissionReward : CaocaoUIModalPopup
{
	public UILabelLocalized labelCompleteNum;

	public GoodUI personGoodUI;

	public UIGrid gridGuildRewards;

	private int completeNum;

	private EnumData.GoodsType personGoodsType;

	private int personGoodsAmount;

	private EnumData.GoodsType[] guildGoodsType;

	private int[] guildGoodsAmount;

	private int[] guildItems;

	private int[] guildItemsAmount;

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

	public static void Show(int completeNum, EnumData.GoodsType personGoodsType, int personGoodsAmount, EnumData.GoodsType[] guildGoodsType, int[] guildGoodsAmount, int[] guildItems, int[] guildItemsAmount)
	{
	}

	private void UpdateUI()
	{
	}
}
