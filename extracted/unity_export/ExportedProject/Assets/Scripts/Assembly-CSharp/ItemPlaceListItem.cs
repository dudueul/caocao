using System;
using UnityEngine;

public class ItemPlaceListItem : UIReuseScrollViewCell
{
	public class ItemPlaceCellData : IReuseCellData
	{
		public int Index { get; set; }

		public bool selected { get; set; }

		public BattleMapInfo battleMapInfo { get; set; }

		public Action<ItemPlaceCellData> onSelected { get; set; }
	}

	public UILabelLocalized m_name;

	public GameObject m_dim;

	private GameObject m_focusSprite;

	public ItemPlaceCellData itemPlaceCellData { get; private set; }

	public GameObject focusSprite
	{
		get
		{
			return null;
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void OnDestroy()
	{
	}

	private void OnClick()
	{
	}

	public void UpdateUI()
	{
	}

	private string GetBattleMapTypeString()
	{
		return null;
	}

	private string GetBattleNameString()
	{
		return null;
	}
}
