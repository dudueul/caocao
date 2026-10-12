using System;

public class RuneListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public UserRuneItem userRuneItem;

		public ulong runeItemSrl;

		public bool showRuneDesc;

		public bool showRunePassiveDesc;

		public bool isDim;

		public bool wearing;

		public Action<CellData> OnClickHandler;

		public Action<CellData> OnDoubleClickHandler;

		private bool m_isSelected;

		public int Index { get; set; }

		public RuneSlotUI runeSlotUI { get; set; }

		public bool isSelected
		{
			get
			{
				return false;
			}
			set
			{
			}
		}
	}

	private RuneSlotUI runeSlotUI;

	private CellData cellData
	{
		get
		{
			return null;
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void UpdateUI()
	{
	}

	private void FindRuneSlotUI()
	{
	}

	private void OnClick()
	{
	}

	private void OnSelectItem(RuneSlotUI runeSlotUI)
	{
	}

	private void OnDoubleClickItem(RuneSlotUI runeSlotUI)
	{
	}

	private void OnDestroy()
	{
	}
}
