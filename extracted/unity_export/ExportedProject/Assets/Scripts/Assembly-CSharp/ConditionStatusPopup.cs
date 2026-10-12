using UnityEngine;

public class ConditionStatusPopup : MonoBehaviour
{
	public UIScrollView conditionsScrollView;

	public UIGrid conditionsGrid;

	public UIScrollView passivesScrollView;

	public UIGrid passivesGrid;

	public UIThousandPassiveGridItem conditionTooltip;

	public GameObject passiveTooltips;

	public UIGrid passiveTooltipsGrid;

	public UIScrollView passiveTooltipsScrollView;

	public GameObject passiveTooltipBg;

	private const int m_columnSizeStep = 168;

	private const int m_initialGridX = -47;

	private const string conditionIconPoolName = "conditionIcon";

	private const string passiveIconPoolName = "passiveIcon";

	private const string passiveTooltipPoolName = "passiveTooltip";

	private UIPassiveIcon m_selectedPassiveIcon;

	private UIPassiveIcon m_selectedConditionIcon;

	private int m_passiveTooltipScrollBgThreshold;

	private void Awake()
	{
	}

	public void UpdateUI()
	{
	}

	private void UpdatePassives()
	{
	}

	private void SelectPassiveIcon(UIPassiveIcon passiveIcon)
	{
	}

	private void UpdateConditions()
	{
	}

	private void SelectConditionIcon(UIPassiveIcon conditionIcon)
	{
	}

	private void OnDisable()
	{
	}

	private void RemovePassiveTooltips()
	{
	}

	private void RemoveAllItem()
	{
	}
}
