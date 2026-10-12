using System.Collections.Generic;
using UnityEngine;

public class RiotReceiveListItem : UIReuseScrollViewCell
{
	public class RiotReceiveListItemCellmData : IReuseCellData
	{
		public int Index { get; set; }

		public int stateId { get; set; }

		public int townId { get; set; }
	}

	private State statePd;

	private Town townPd;

	private UserTown userTown;

	public UILabelLocalized stateName;

	public UILabelLocalized townName;

	public UILabelLocalized itemName;

	public GameObject itemUIParent;

	public UILabelLocalized cooltime;

	private bool m_canReceive;

	private List<RewardInfo> rewardInfos;

	private Vector3 instItemUIScale;

	private Color canReceiveTextColor;

	private Color cannotReceiveTextColor;

	public RiotReceiveListItemCellmData m_riotReceiveListItemCellData { get; private set; }

	public bool canReceive
	{
		get
		{
			return false;
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	public void SetData(int stateId, int townId)
	{
	}

	public void UpdateUI()
	{
	}
}
