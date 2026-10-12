using System;

public class PanelTooltipItem : SelfRegister2UIComponentsMonobehaviour
{
	public Action OnClose;

	protected override void Awake()
	{
	}

	private void OnClick()
	{
	}

	public void UpdateUI(UserUnit userUnit, EnumData.ItemSlotType itemSlotType)
	{
	}

	public void UpdateUI(RewardInfo rewardInfo)
	{
	}

	public void UpdateInfo(int itemID, UserItem userItem = null)
	{
	}

	private string GetItemEffectStr(int itemEffectID)
	{
		return null;
	}
}
