using System;
using UnityEngine;

public class TreasureRecipeListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public int Index { get; set; }

		public CraftTreasure data { get; set; }

		public bool selected { get; set; }

		public Action<CellData> onClick { get; set; }
	}

	[SerializeField]
	private UILabelLocalized labelName;

	[SerializeField]
	private PrefabInstance itemUIInst;

	[SerializeField]
	private GameObject objSelect;

	private static readonly Color GRAY_COLOR;

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

	private void OnClick()
	{
	}
}
