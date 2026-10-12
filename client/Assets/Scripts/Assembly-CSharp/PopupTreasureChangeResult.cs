using System.Collections.Generic;
using UnityEngine;

public class PopupTreasureChangeResult : CaocaoUIModalPopup
{
	protected class ResultInfo
	{
		private UserItem m_userItem;

		private int m_amount;

		public UserItem userItem
		{
			get
			{
				return null;
			}
		}

		public int amount
		{
			get
			{
				return 0;
			}
		}

		public ResultInfo(UserItem userItem, int amount)
		{
		}
	}

	private List<ulong> m_srls;

	private List<int> m_amounts;

	private List<ResultInfo> m_results;

	public static void Show(List<ulong> srls, List<int> amounts)
	{
	}

	private void Initialize()
	{
	}

	private int SortFunc(ResultInfo x, ResultInfo y)
	{
		return 0;
	}

	private void UpdateUI()
	{
	}

	private void UpdateCell(Transform cellTrf, ResultInfo info)
	{
	}

	private void OnClickItem(ItemUI itemUI)
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public override void ClosePanel()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}
}
