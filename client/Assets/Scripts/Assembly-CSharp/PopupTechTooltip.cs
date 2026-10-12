using System;
using LitJson;
using UnityEngine;

public class PopupTechTooltip : CaocaoUIModalPopup
{
	private Tech m_tech;

	private int m_level;

	public Action OnUpdate;

	public Tech tech
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	protected override void Awake()
	{
	}

	public void OnClickCancel()
	{
	}

	public void OnClickPurchase()
	{
	}

	private void OnUpdateCallback(JsonData response)
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
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

	private void UpdateUI()
	{
	}

	private void UpdateIcon()
	{
	}

	private void UpdateDesc()
	{
	}

	private void UpdateButton(bool isisAvailable)
	{
	}

	private void UpdateCost(Transform tf)
	{
	}

	private void RepositionGrid()
	{
	}

	private UIGrid GetGrid()
	{
		return null;
	}

	private float GetLabelHeight(Transform tf)
	{
		return 0f;
	}

	private int GetNeedCost()
	{
		return 0;
	}

	private string GetDescription(Tech data, int val)
	{
		return null;
	}

	private void SetUILabel(Transform tf, string message, params Color[] colors)
	{
	}

	private void ClearGrid(UIGrid grid)
	{
	}

	private bool isAvailable()
	{
		return false;
	}

	private int GetCurrentLevelValue(int groupId)
	{
		return 0;
	}

	private int GetType(int index)
	{
		return 0;
	}

	private int GetCheckValue(int index)
	{
		return 0;
	}

	private int GetValue(int index)
	{
		return 0;
	}

	private int GetNeedRank(int index)
	{
		return 0;
	}
}
