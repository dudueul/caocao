using UnityEngine;

public class PopupNoticeUserSpell : CaocaoUIModalPopup
{
	[SerializeField]
	private UILabelLocalized labelName;

	[SerializeField]
	private UILabelLocalized labelDesc;

	public static PopupNoticeUserSpell Show()
	{
		return null;
	}

	public void SetData(UserSpell pdUserSpell)
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
}
