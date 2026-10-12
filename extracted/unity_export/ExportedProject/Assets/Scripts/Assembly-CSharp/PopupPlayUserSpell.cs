using UnityEngine;

public class PopupPlayUserSpell : CaocaoUIModalPopup
{
	[SerializeField]
	private UITexture m_imageTexture;

	[SerializeField]
	private UILabelLocalized m_nameLabel;

	[SerializeField]
	private UILabelLocalized m_descLabel;

	private int userSpellId;

	public static PopupPlayUserSpell Show(int id)
	{
		return null;
	}

	private void UpdateUI()
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
