using System;
using UnityEngine;

public class UIBrawlConditionListItem : MonoBehaviour
{
	[SerializeField]
	private UISprite icon;

	[SerializeField]
	private UILabelLocalized conditionName;

	[SerializeField]
	private GameObject selectObj;

	public Action<UnitCondition> listClickCallBack;

	private UnitCondition unitCondition;

	public void SetInfo(UnitCondition info)
	{
	}

	public void OnListClick()
	{
	}
}
