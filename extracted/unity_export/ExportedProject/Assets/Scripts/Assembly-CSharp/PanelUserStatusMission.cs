using LitJson;
using UnityEngine;

public class PanelUserStatusMission : CaocaoUIModalPopup
{
	[SerializeField]
	private UILabelLocalized m_title;

	[SerializeField]
	private UserStatusMissionItem[] m_missionItems;

	private void Start()
	{
	}

	private void UpdateUI(EnumData.UserStatus userStatus)
	{
	}

	public static void Show()
	{
	}

	public void OnResponseAchievement(JsonData response)
	{
	}

	private int GetInitItemCount(EnumData.UserStatus userStatus)
	{
		return 0;
	}

	private int GetAddItemIndex(EnumData.UserStatus userStatus)
	{
		return 0;
	}

	private void CheckFinishMission(Achievement achievement)
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
