using System;
using UnityEngine;

public class TownInfoListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public Action updateFunc;

		public int Index { get; set; }

		public UserTown userTown { get; set; }

		public Town pTownData { get; set; }
	}

	[SerializeField]
	private UILabelLocalized m_name;

	[SerializeField]
	private UILabelLocalized m_mayorLv;

	[SerializeField]
	private UILabelLocalized m_mayorName;

	[SerializeField]
	private UILabelLocalized m_farmTitle;

	[SerializeField]
	private UILabelLocalized m_farm;

	[SerializeField]
	private UILabelLocalized m_marketTitle;

	[SerializeField]
	private UILabelLocalized m_market;

	[SerializeField]
	private UILabelLocalized m_numBgValue;

	[SerializeField]
	private GameObject m_selectObj;

	private CellData m_townInfoCellData;

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void SetBuildingInfo(UserTown userTown, UILabelLocalized title, UILabelLocalized desc, EnumData.TownBuildingType buildingType)
	{
	}

	public void OnSelectItem()
	{
	}

	public void SetFocus()
	{
	}

	private void SetClickEvent()
	{
	}
}
