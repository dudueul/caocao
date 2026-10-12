using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class PopupRelationDetail : CaocaoUIModalPopup
{
	private RelationList relationList;

	private bool needUpdateEffectPassiveGrid;

	private Transform m_tier;

	private Transform m_state;

	private UILabelLocalized m_relationName;

	private Transform m_faces;

	private RelationFavoriteUI m_favoriteUI;

	private UILabelLocalized m_scenarioDesc;

	private UITable m_effectGrid;

	private UIGrid m_effectStatGrid;

	private UITable m_effectPassiveGrid;

	private GameObject m_payObj;

	private UILabelLocalized m_costLabel;

	private Transform[] m_detailList;

	private GameObject m_dimObj;

	private GameObject m_goScenarioObj;

	private GameObject m_restartObj;

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

	private RelationFavoriteUI favoriteUI
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized scenarioDesc
	{
		get
		{
			return null;
		}
	}

	private UITable effectGrid
	{
		get
		{
			return null;
		}
	}

	private UIGrid effectStatGrid
	{
		get
		{
			return null;
		}
	}

	private UITable effectPassiveGrid
	{
		get
		{
			return null;
		}
	}

	private GameObject payObj
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

	private Transform[] detailList
	{
		get
		{
			return null;
		}
	}

	private GameObject dimObj
	{
		get
		{
			return null;
		}
	}

	private GameObject goScenarioObj
	{
		get
		{
			return null;
		}
	}

	private GameObject restartObj
	{
		get
		{
			return null;
		}
	}

	public static void Show(RelationList relationList)
	{
	}

	private void InitUI()
	{
	}

	private void FixedUpdate()
	{
	}

	[DebuggerHidden]
	private IEnumerator LateUpdateGrid()
	{
		return null;
	}

	private void UpdateUI()
	{
	}

	private void UpdateMissionUI()
	{
	}

	public void OnPurchaseButton()
	{
	}

	public void OnGoBattleButton()
	{
	}

	public void OnFaceButton()
	{
	}

	private void GoBattle()
	{
	}

	public void OnShortcutButton()
	{
	}

	public void OnPayButton()
	{
	}

	protected override void OnDestroy()
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

	protected override void ShowPanelImpl()
	{
	}

	private void OnDeserializeUnitRelations()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
