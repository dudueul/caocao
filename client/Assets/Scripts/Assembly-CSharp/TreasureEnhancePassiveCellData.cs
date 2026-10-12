using System;

public class TreasureEnhancePassiveCellData : IReuseCellData
{
	public int m_Index;

	public int passiveId;

	public TreasureEnhancePassiveCombinedData combinedData;

	public Action<TreasureEnhancePassiveCellData> OnClickHandler;

	public bool selected;

	public bool useMaxOnly;

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
