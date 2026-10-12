using System;
using LitJson;
using UnityEngine;

internal class PanelCustomEventUnitListItem : MonoBehaviour
{
	private Action<JsonData> onRequestReward__BackingField;

	public CustomEventUnit eventUnitData { get; private set; }

	private event Action<JsonData> onRequestReward
	{
		add
		{
		}
		remove
		{
		}
	}

	public void UpdateEventUnit(CustomEventUnit unitData)
	{
	}

	public void UpdateRecordValue(CustomEventUser userData)
	{
	}

	public void AddOnClickHandler(Action<JsonData> callback)
	{
	}

	public void OnClickRequestReward()
	{
	}

	private void OnGetReward(JsonData response)
	{
	}
}
