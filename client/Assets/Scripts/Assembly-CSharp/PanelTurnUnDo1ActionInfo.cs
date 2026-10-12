using UnityEngine;

public class PanelTurnUnDo1ActionInfo : CaocaoUIPanel
{
	private Color m_color_yellow;

	public UILabelLocalized m_nameLev;

	public UILabelLocalized m_job;

	public UILabelLocalized m_level;

	public UILabelLocalized m_turn;

	public UILabelLocalized[] m_labelRes;

	public UILabelLocalized m_exp;

	public UISprite[] m_gaugeRes;

	public UISprite m_expGauge;

	public UISprite m_resourceIcon;

	public UILabelLocalized m_resource;

	private Transform m_unitSlotView;

	private Transform m_scrollView;

	private Transform m_grid;

	private UndoActionClickHandler m_selected;

	private bool m_started;

	private void Start()
	{
	}

	public override void OnInit()
	{
	}

	protected override void OnDestroy()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	private void RemoveUnitList()
	{
	}

	public void CancelUndo()
	{
	}

	public void UnDoPanel()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	private void SelectHand(UndoActionClickHandler selection)
	{
	}

	private void OnSelectionChange(UndoActionClickHandler selection)
	{
	}
}
