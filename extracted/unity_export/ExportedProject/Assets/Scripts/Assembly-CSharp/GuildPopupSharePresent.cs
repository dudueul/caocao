using System;

public class GuildPopupSharePresent : CaocaoUIModalPopup
{
	[Serializable]
	public class ListObject : GuildUIMemberListObject
	{
		private const float SLIDER_SOUND_GAP = 0.05f;

		private float lastSliderSoundTime;

		public int maxAmount { get; set; }

		public int stepAmount { get; set; }

		public int totalAmount
		{
			get
			{
				return 0;
			}
		}

		public void SetFairAmount(int amount)
		{
		}

		public object[] GetPresentData()
		{
			return null;
		}

		protected override GuildMemberCellData GenerateCellData(int index, GuildMember member)
		{
			return null;
		}

		protected override Func<GuildUIMemberCategory, GuildMemberCellData, GuildMemberCellData, int> GenerateCustomSortFunc()
		{
			return null;
		}

		private void OnSlider(GuildUIPresentMemberListItem.CellData cellData, UISlider slider, UILabelLocalized label)
		{
		}

		private void OnSubButton(GuildUIPresentMemberListItem.CellData cellData, UISlider slider, UILabelLocalized label, int count)
		{
		}

		private void OnAddButton(GuildUIPresentMemberListItem.CellData cellData, UISlider slider, UILabelLocalized label, int count)
		{
		}

		private void AddAmount(int amount, GuildUIPresentMemberListItem.CellData cellData, UISlider slider, UILabelLocalized label)
		{
		}

		private void UpdateSliders()
		{
		}

		public void UpdateUserMax(bool isGoods, int itemId)
		{
		}
	}

	public ListObject listObject;

	public ItemUI itemUI;

	public UILabelLocalized labelItemName;

	private int itemId;

	private int maxAmount;

	private int goodsStepAmount;

	private bool goodsPresent;

	private Action<bool> onClose;

	private bool tryPresent;

	private bool presented;

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public static void Show(bool isGood, int itemId, int maxAmount, int goodsStepAmount, Action<bool> onClose)
	{
	}

	public void OnPresentClicked()
	{
	}

	public void OnFairPresentClicked()
	{
	}

	public void OnFairPresentCallback(CaocaoUIModalPopup popup, int amount)
	{
	}

	private void presentByPoint(Func<GuildUIPresentMemberListItem.CellData, int> getPointFunc)
	{
	}

	public void OnWeeklyRagePointPresentClicked()
	{
	}

	public void OnWeeklyContributionPresentClicked()
	{
	}

	public void OnWeeklyActivityPointPresentClicked()
	{
	}

	private void OnUpdateStorage()
	{
	}

	private void OnGuildError(string err, ref bool handled)
	{
	}

	protected override void OnDestroy()
	{
	}
}
