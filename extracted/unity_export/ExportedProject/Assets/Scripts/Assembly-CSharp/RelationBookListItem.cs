using UnityEngine;

public class RelationBookListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public int Index { get; set; }

		public RelationList data { get; set; }
	}

	private const int RelationItemID = 3100207;

	private static Color CompleteContentLabelColor;

	private Transform m_tier;

	private Transform m_state;

	private UILabelLocalized m_relationName;

	private Transform m_faces;

	private GameObject m_purchaseButton;

	private UILabelLocalized m_costLabel;

	private RelationFavoriteUI m_favoriteUI;

	private Transform m_detail;

	private Transform[] m_detailList;

	private Transform m_reward;

	private UIGrid m_stats;

	private UIGrid m_passives;

	private CellData cellData
	{
		get
		{
			return null;
		}
	}

	private Transform tier
	{
		get
		{
			return null;
		}
	}

	private Transform state
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized relationName
	{
		get
		{
			return null;
		}
	}

	private Transform faces
	{
		get
		{
			return null;
		}
	}

	private GameObject purchaseButton
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized costLabel
	{
		get
		{
			return null;
		}
	}

	private RelationFavoriteUI favoriteUI
	{
		get
		{
			return null;
		}
	}

	private Transform detail
	{
		get
		{
			return null;
		}
	}

	private Transform[] detailList
	{
		get
		{
			return null;
		}
	}

	private Transform reward
	{
		get
		{
			return null;
		}
	}

	private UIGrid stats
	{
		get
		{
			return null;
		}
	}

	private UIGrid passives
	{
		get
		{
			return null;
		}
	}

	public override void UpdateData(IReuseCellData _cellData)
	{
	}

	private void OnDeserializeUnitRelation()
	{
	}

	private void OnDeserializeUnitRelations()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdatePurchasedDetailUI()
	{
	}

	private void UpdateCompleteDetailUI()
	{
	}

	public void OnPurchaseButton()
	{
	}

	public void OnDetailButton()
	{
	}

	public void OnFaceButton()
	{
	}

	private void OnDestroy()
	{
	}
}
