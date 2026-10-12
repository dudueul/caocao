using System;

public class PopupInviteFriendlyMatchWaiting : CaocaoUIModalPopup
{
	private static PopupInviteFriendlyMatchWaiting self;

	private UILabelLocalized m_infoLabel;

	private UILabelLocalized m_stateLabel;

	private ulong targetId;

	private string nationName;

	private EnumData.MultiplayUserState state;

	private string roomId;

	private Action<bool> resultCallback;

	private UILabelLocalized infoLabel
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized stateLabel
	{
		get
		{
			return null;
		}
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

	protected override void OnDestroy()
	{
	}
}
