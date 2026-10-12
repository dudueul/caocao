using LitJson;
using Multiplay;
using UnityEngine;

public class PopupWatchPreBattle : CaocaoUIModalPopup
{
	private static PopupWatchPreBattle self;

	[SerializeField]
	private LabelWatchMemeberCount memberCntLabel;

	[SerializeField]
	private GameObject formationBlockObj;

	private ulong hostUid;

	private bool retryRequestStep;

	public static void Show(ulong uid, IDepthManagerblePopup parentPopup = null)
	{
	}

	public static void ShowBlock(ulong uid, IDepthManagerblePopup parentPopup = null)
	{
	}

	private void ShowBlock()
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

	private void OnFormationSetCallback()
	{
	}

	private void OnOrderSetCallback()
	{
	}

	private void OnReadySetCallback()
	{
	}

	private void OnBattleStartCallback()
	{
	}

	public void LeaveWatch()
	{
	}

	private void OnWatchFinishedCallback()
	{
	}

	private void OnStepResponsedCallback(Step step, JsonData data)
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
