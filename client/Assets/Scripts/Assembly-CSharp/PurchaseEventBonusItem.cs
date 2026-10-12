using UnityEngine;

public class PurchaseEventBonusItem : MonoBehaviour
{
	public UILabelLocalized m_name;

	public PrefabInstance m_itemUIInstance;

	public UILabelLocalized m_mission;

	public Transform m_get;

	private ItemUI itemUI;

	private PurchaseEvent m_eventPd;

	private int m_index;

	public void UpdateUI(PurchaseEvent eventPd, UserCumulativePurchaseAmount userCpa, int index)
	{
	}

	private void ShowItemTooltip(ItemUI itemUI)
	{
	}
}
