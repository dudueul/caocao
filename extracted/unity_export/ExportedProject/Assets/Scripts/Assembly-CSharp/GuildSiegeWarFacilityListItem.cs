using System;
using UnityEngine;

public class GuildSiegeWarFacilityListItem : MonoBehaviour
{
	private const string selectedSpriteName = "cha_frame_bg_grade_b";

	private const string deselectedSpriteName = "cha_frame_bg_grade_d";

	private static Color32 green;

	private static Color32 red;

	public UISprite sprite;

	public UISprite frameSprite;

	public UILabelLocalized labelName;

	public UILabelLocalized labelLv;

	public UILabelLocalized labelCost;

	public GameObject objCost;

	private bool m_selected;

	public int facilityId;

	public GuildSiegeWarFacility facility;

	public GuildSiegeWarFacilityWar facilityWar;

	public GuildSiegeTownFacility facilityData;

	public Action<GuildSiegeWarFacilityListItem> onClick;

	public bool selected
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool canConstruct
	{
		get
		{
			return false;
		}
	}

	public void SetData(int facilityId, GuildSiegeWarFacility facility, GuildSiegeWarFacilityWar facilityWar, Action<GuildSiegeWarFacilityListItem> onClick)
	{
	}

	public void Refresh()
	{
	}

	public void UpdateUI()
	{
	}

	private void OnClick()
	{
	}

	private void OnDestroy()
	{
	}
}
