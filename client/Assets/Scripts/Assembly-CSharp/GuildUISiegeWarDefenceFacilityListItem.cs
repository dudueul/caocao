using System;
using UnityEngine;

public class GuildUISiegeWarDefenceFacilityListItem : MonoBehaviour
{
	public EnumData.GuildSiegeObjectType objectType;

	public UILabelLocalized labelUnitNum;

	public UILabelLocalized labelAttr;

	public GameObject objSelect;

	private bool initiated;

	private bool reinforced;

	public Action<GuildUISiegeWarDefenceFacilityListItem> onClick;

	public int unitId { get; protected set; }

	public void Setting()
	{
	}

	private void Select(bool select)
	{
	}

	private void OnClick()
	{
	}

	private void OnDestroy()
	{
	}
}
