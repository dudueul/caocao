using LitJson;
using Multiplay;

public class PopupWatchWaiting : CaocaoUIModalPopup
{
	private static PopupWatchWaiting self;

	private UILabelLocalized m_hostInfoLabel;

	private UILabelLocalized m_stateLabel;

	private ulong hostUid;

	private string nationName;

	private EnumData.MultiplayUserState state;

	private int retryRequestStep;

	private UILabelLocalized hostInfoLabel
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

	public static void Show(IDepthManagerblePopup parentPopup = null)
	{
	}

	private void UpdateUI()
	{
	}

	private void OnMultiplayUserStateChangedCallback(ulong uid, EnumData.MultiplayUserState state, int memberCnt)
	{
	}

	private void OnMatchedCallback()
	{
	}

	private void OnMatchmakingCanceledCallback()
	{
	}

	private void OnUnitsPickedCallback()
	{
	}

	private void OnStepResponsedCallback(Step step, JsonData data)
	{
	}

	public void LeaveWatch()
	{
	}

	private void OnWatchFinishedCallback()
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

	protected override void OnDestroy()
	{
	}
}
