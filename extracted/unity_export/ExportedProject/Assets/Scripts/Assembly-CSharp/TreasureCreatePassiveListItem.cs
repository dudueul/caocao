using UnityEngine;

public class TreasureCreatePassiveListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public int Index { get; set; }

		public CraftTreasurePassive data { get; set; }
	}

	[SerializeField]
	private UILabelLocalized labelName;

	[SerializeField]
	private UILabelLocalized labelDesc;

	[SerializeField]
	private PrefabInstance passiveInst;

	[SerializeField]
	private GameObject objSelect;

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
}
