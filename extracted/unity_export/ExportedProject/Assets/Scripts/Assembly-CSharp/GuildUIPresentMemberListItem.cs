using System;

public class GuildUIPresentMemberListItem : GuildUIRecruitMemberListItem
{
	public class CellData : GuildUIMemberListObject.GuildMemberCellData
	{
		public GuildUIPresentMemberListItem listItem;

		public Action<CellData, UISlider, UILabelLocalized> onSlider;

		public Action<CellData, UISlider, UILabelLocalized, int> onSubButton;

		public Action<CellData, UISlider, UILabelLocalized, int> onAddButton;

		public int amount { get; set; }

		public int max { get; set; }

		public int userMax { get; set; }

		public int stepAmount { get; set; }

		public int maxValue
		{
			get
			{
				return 0;
			}
		}
	}

	public UISlider slider;

	public PressableButton subPressableBtn;

	public PressableButton addPressableBtn;

	public UILabelLocalized labelAmount;

	private bool buttonSliding;

	public override void Initiate()
	{
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	public void ForcedUpdateUI()
	{
	}

	protected override void UpdateUI()
	{
	}

	public void OnSlider()
	{
	}

	public void OnSubButton()
	{
	}

	public void OnAddButton()
	{
	}

	public void OnSubButtonImpl(int count)
	{
	}

	public void OnAddButtonImpl(int count)
	{
	}

	protected override void OnDestroy()
	{
	}
}
