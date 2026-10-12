using UnityEngine;

public class ClickQuickUseItem : CaocaoUIButton
{
	[SerializeField]
	private PrefabInstance m_itemUIPrefabInstance;

	private ItemUI m_itemUI;

	private ItemUI itemUI
	{
		get
		{
			return null;
		}
	}

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	private void OnClick(ItemUI itemUI)
	{
	}

	private void UseItemSkill(EnumData.Skills skillId)
	{
	}

	private void FailToUseItemSkill(string msg, params object[] args)
	{
	}

	protected override void OnClickImpl()
	{
	}

	protected override void UpdateButtonState()
	{
	}
}
