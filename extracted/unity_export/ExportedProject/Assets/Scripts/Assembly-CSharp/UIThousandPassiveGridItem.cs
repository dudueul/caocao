using UnityEngine;

public class UIThousandPassiveGridItem : MonoBehaviour
{
	public UILabelLocalized passiveName;

	public UILabelLocalized passiveLv;

	public UILabelLocalized passiveDetail;

	public PrefabInstance passiveUiParent;

	public PrefabInstance conditionUIParent;

	public UILabelLocalized passiveCategory;

	public void UpdateUI(SpActionBase spAction, bool isTreasurePassive, bool showOrigin)
	{
	}

	public void UpdateUI(int spAction, int value, bool isTreasurePassive, string tile = "", int rate = 0, string condition1 = "", string condition2 = "")
	{
	}

	public void UpdateUI(Condition condition, int descValue)
	{
	}
}
