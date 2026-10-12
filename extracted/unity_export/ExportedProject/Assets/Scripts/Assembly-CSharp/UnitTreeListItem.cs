using System;
using System.Collections.Generic;
using UnityEngine;

public class UnitTreeListItem : UIReuseScrollViewCell
{
	public class UnitTreeCellData : IReuseCellData
	{
		private UnitTree m_unitTree;

		public Action<UnitTreeCellData> OnClickHandler;

		private List<int> m_units;

		public int Index { get; set; }

		public UnitTree unitTree
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public bool selected { get; set; }

		public List<int> units
		{
			get
			{
				return null;
			}
		}
	}

	private UnitTreeCellData m_unitTreeCellData;

	public UnitTreeCellData unitTreeCellData
	{
		get
		{
			return null;
		}
	}

	private GameObject m_selectedObj
	{
		get
		{
			return null;
		}
	}

	private UITexture m_face
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized m_name
	{
		get
		{
			return null;
		}
	}

	private GameObject m_dimObj
	{
		get
		{
			return null;
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	public void UpdateUI()
	{
	}

	public void OnClick()
	{
	}
}
