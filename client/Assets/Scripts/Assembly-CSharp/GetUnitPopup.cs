using System.Collections;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class GetUnitPopup : CaocaoUIModalPopup
{
	public GameObject m_GridIUnitPrefab;

	private UIGrid m_Grid;

	private UILabelLocalized m_Label;

	private UIButton m_BtnConfirm;

	protected override void Awake()
	{
	}

	protected override void OnDestroy()
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

	public void Reset()
	{
	}

	public void AddUnit(int unitID)
	{
	}

	public void AddUnitFromMine(int unitID)
	{
	}

	public void AddSoldier(int unitID, int level)
	{
	}

	public void Show()
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitAndClose(float waitingDuration)
	{
		return null;
	}

	public int GetGridCount()
	{
		return 0;
	}

	public void HidePanel()
	{
	}

	private void OnAddNewUnit(JsonData response)
	{
	}
}
