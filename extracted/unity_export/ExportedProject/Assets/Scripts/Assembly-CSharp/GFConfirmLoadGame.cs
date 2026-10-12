using System.Collections;
using System.Diagnostics;

public class GFConfirmLoadGame : GameFlowNodeBase
{
	protected override void DefineFollowers()
	{
	}

	protected override void _DoLogic()
	{
	}

	[DebuggerHidden]
	private IEnumerator ContinueGame()
	{
		return null;
	}

	protected override int? _GetState()
	{
		return null;
	}

	private PanelPopupBase.PopupState LoadSavedGame()
	{
		return PanelPopupBase.PopupState.KeepOpen;
	}

	private void RequestArenaInfo()
	{
	}

	private PanelPopupBase.PopupState CancelLoadSavedGame()
	{
		return PanelPopupBase.PopupState.KeepOpen;
	}
}
