using System;
using UnityEngine;

public class GuildUIMarkListItem : UIReuseScrollViewCell
{
	public class GuildMarkCellData : IReuseCellData
	{
		private bool _selected;

		public Action<int> onSelect;

		public Func<GuildUIMarkListItem, int, Texture> getTextureFunc;

		public int Index { get; set; }

		public int markTexIndex { get; set; }

		public GuildUIMarkListItem item { get; set; }

		public bool selected
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

	private GuildMarkCellData m_markItemData;

	public UITexture sprMark;

	public GameObject objSelected;

	public GuildMarkCellData markItemData
	{
		get
		{
			return null;
		}
	}

	public override int Index
	{
		get
		{
			return 0;
		}
	}

	public int markTexIndex
	{
		get
		{
			return 0;
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void UpdateUI()
	{
	}

	public void OnLoadedMark(Texture tex)
	{
	}

	private void OnClick()
	{
	}

	public void SetState(bool selected)
	{
	}

	private void OnDestroy()
	{
	}
}
