using UnityEngine;

public class UnitDescOnTree : MonoBehaviour
{
	public delegate void ClickUnlockUnitTree(int unitTreeNodeID);

	public delegate void ClickPurchaseUnitTree(int unitTreeNodeID);

	public delegate void UnlockAndPurchaseUnitTree(int unitTreeNodeID);

	public delegate void UnlockAndPurchaseUnitTreeByTicket(int unitTreeNodeID);

	private int m_nodeID;

	private UnitTreeNode m_nodeData;

	private Unit m_unitData;

	private ClickUnlockUnitTree OnClickUnlockUnitTree__BackingField;

	private ClickPurchaseUnitTree OnClickPurchaseUnitTree__BackingField;

	private UnlockAndPurchaseUnitTree OnClickUnlockAndPurchaseUnitTree__BackingField;

	private UnlockAndPurchaseUnitTreeByTicket OnClickUnlockAndPurchaseUnitTreeByTicket__BackingField;

	public int nodeID
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	private GameObject m_infoBtn
	{
		get
		{
			return null;
		}
	}

	private HeroInfoTag m_myHeroInfoTag
	{
		get
		{
			return null;
		}
	}

	private HeroInfoTag m_heroInfoTag
	{
		get
		{
			return null;
		}
	}

	private bool isPurchasedTree { get; set; }

	public event ClickUnlockUnitTree OnClickUnlockUnitTree
	{
		add
		{
		}
		remove
		{
		}
	}

	public event ClickPurchaseUnitTree OnClickPurchaseUnitTree
	{
		add
		{
		}
		remove
		{
		}
	}

	public event UnlockAndPurchaseUnitTree OnClickUnlockAndPurchaseUnitTree
	{
		add
		{
		}
		remove
		{
		}
	}

	public event UnlockAndPurchaseUnitTreeByTicket OnClickUnlockAndPurchaseUnitTreeByTicket
	{
		add
		{
		}
		remove
		{
		}
	}

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void UpdateUI()
	{
	}

	private void UnlockUnitTreeClicked()
	{
	}

	private void PurchaseUnitTreeClicked()
	{
	}

	private void UnlockAndPurchaseUnitTreeClicked()
	{
	}

	private void UnlockAndPurchaseUnitTreeByTicketClicked()
	{
	}

	private void OnClickManage()
	{
	}

	public void OnClickInfo()
	{
	}

	private void OnUnitDeserialzeCallback()
	{
	}

	private void OnTreeNodeDeserialzeCallback()
	{
	}
}
