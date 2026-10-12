using System;
using UnityEngine;

public class HistoryEventStorybookInfoListItem : MonoBehaviour
{
	[SerializeField]
	private UILabelLocalized mainText;

	[SerializeField]
	private Transform normalTransf;

	[SerializeField]
	private GameObject normalSelectObj;

	[SerializeField]
	private Transform hardTransf;

	[SerializeField]
	private GameObject hardSelectObj;

	[SerializeField]
	private Transform classicTransf;

	[SerializeField]
	private GameObject classicSelectObj;

	public Action DeselectAction;

	private UserStorybook userStorybook;

	private Vector3[] transformPositions;

	private const string scenarioTextFormat = "{0} ({1})";

	private void Awake()
	{
	}

	public void UpdateUI(UserStorybook storybook)
	{
	}

	public void OnDeSelect()
	{
	}
}
