using UnityEngine;

public class PopupCompetitionTierChange : CaocaoUIModalPopup
{
	[SerializeField]
	private GameObject m_upGo;

	[SerializeField]
	private UITexture m_upPreviousTierIconTexture;

	[SerializeField]
	private UILabelLocalized m_upPreviousTierName;

	[SerializeField]
	private UITexture m_upCurrentTierIconTexture;

	[SerializeField]
	private UILabelLocalized m_upCurrentTierName;

	[SerializeField]
	private GameObject m_downGo;

	[SerializeField]
	private UITexture m_downPreviousTierIconTexture;

	[SerializeField]
	private UILabelLocalized m_downPreviousTierName;

	[SerializeField]
	private UITexture m_downCurrentTierIconTexture;

	[SerializeField]
	private UILabelLocalized m_downCurrentTierName;

	private Tier m_previousTier;

	private Tier m_currentTier;

	public static void Show(Tier previousTier, Tier currentTier)
	{
	}

	private void InitUi()
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

	protected override void ClosePanelImpl()
	{
	}
}
