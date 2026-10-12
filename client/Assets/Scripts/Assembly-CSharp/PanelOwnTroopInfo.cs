using UnityEngine;

public class PanelOwnTroopInfo : CaocaoUIPanel
{
	private enum Direction
	{
		LEFT = 0,
		RIGHT = 1
	}

	private Color m_color_yellow;

	private const float m_unitFlickerTime = 1f;

	public UILabelLocalized m_name;

	public UILabelLocalized m_lev;

	public UILabelLocalized m_job;

	public UILabelLocalized m_turn;

	public UILabelLocalized[] m_labelRes;

	public UILabelLocalized m_exp;

	public UILabelLocalized m_weaponExp;

	public UILabelLocalized m_armorExp;

	public UISprite[] m_gaugeRes;

	public UISprite m_expGauge;

	private Transform m_unitSlotView;

	private Transform m_scrollView;

	private UIScrollView m_scrollUI;

	private Transform m_grid;

	private int m_initPosX;

	private int m_unitCnt;

	private GameObject m_firstPosUnitObj;

	private GameObject m_edgeMarkerObj;

	private bool m_isFinishMakeGrid;

	private bool m_isMarkered;

	private Direction m_direction;

	private GameObject m_selectedUnitObj;

	private bool m_started;

	private static string[] unitGaugeName;

	private UnitDataDevolved m_selectedUnitObjDevolvedData
	{
		get
		{
			return null;
		}
	}

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

	private void Update()
	{
	}

	private void RemoveUnitList()
	{
	}

	private void OnDisable()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	private void SetEdgeMarkerImage(int unitCnt)
	{
	}

	public void SelectUnit(GameObject unitObj)
	{
	}

	private void StartFlickerSelectedUnit()
	{
	}

	private void StopFlickerSelectedUnit()
	{
	}

	private void UpdateStatUI(GameObject unitObj)
	{
	}
}
