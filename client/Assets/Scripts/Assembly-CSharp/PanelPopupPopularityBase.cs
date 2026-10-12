using UnityEngine;

public abstract class PanelPopupPopularityBase : CaocaoUIModalPopup
{
	[SerializeField]
	protected UILabelLocalized m_grade;

	public abstract void Receive();

	protected abstract void UpdateUI();

	private void Start()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
