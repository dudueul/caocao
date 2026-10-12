using LitJson;
using UnityEngine;

public class PopupCompetitionFriendlyJoin : CaocaoUIModalPopup
{
	private static PopupCompetitionFriendlyJoin self;

	[SerializeField]
	private UIInput codeInput;

	public static void Show()
	{
	}

	public void SendFriendlyMatchingCode()
	{
	}

	public void OnMatched()
	{
	}

	public void OnErrorOnFriendlyMatching(EnumData.FriendlyMatchingError error, JsonData jsonData)
	{
	}

	private void OnJoinedFriendlyMatchingRoom(FriendlyMatchingRoom roomInfo)
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
