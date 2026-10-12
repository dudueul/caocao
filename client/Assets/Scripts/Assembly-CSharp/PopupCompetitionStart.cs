using UnityEngine;

public class PopupCompetitionStart : CaocaoUIModalPopup
{
	public static bool isAlive;

	[SerializeField]
	private UITexture m_tierIconTexture;

	[SerializeField]
	private UILabelLocalized m_tierName;

	[SerializeField]
	private UILabelLocalized m_pointLabel;

	[SerializeField]
	private GameObject m_degradeGo;

	[SerializeField]
	private UILabelLocalized m_modeName;

	[SerializeField]
	private UILabelLocalized m_mapName;

	[SerializeField]
	private UITexture m_mapImageTexture;

	public static void Show()
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
