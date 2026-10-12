using System;

public class TreasureBookCellData : IReuseCellData
{
	public int m_Index;

	public ulong itemSrl;

	public int itemID;

	public ItemUI itemUI;

	public bool isFocused;

	public bool checkIsOwned;

	public bool showEquippedInfo;

	public bool showReinforceInfo;

	public bool isHasItemCheck;

	public Action<TreasureBookCellData> OnClickHandler;

	public int Index
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}
}
