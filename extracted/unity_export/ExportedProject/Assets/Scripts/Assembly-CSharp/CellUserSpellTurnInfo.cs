using UnityEngine;

public class CellUserSpellTurnInfo : MonoBehaviour
{
	[SerializeField]
	private GameObject own;

	[SerializeField]
	private GameObject enemy;

	[SerializeField]
	private UILabelLocalized labelOwn;

	[SerializeField]
	private UILabelLocalized labelEnemy;

	[SerializeField]
	private GameObject objTooltip;

	[SerializeField]
	private UILabelLocalized labelTurnValue;

	[SerializeField]
	private UILabelLocalized labelMapObjValue;

	[SerializeField]
	private ClickHandler clickHandler;

	private void Start()
	{
	}

	private void UpdateUI()
	{
	}

	private void OnClickEvent()
	{
	}

	private void OnDestroy()
	{
	}
}
