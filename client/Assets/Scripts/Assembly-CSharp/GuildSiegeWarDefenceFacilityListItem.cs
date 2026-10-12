using System;
using UnityEngine;

public class GuildSiegeWarDefenceFacilityListItem : MonoBehaviour
{
	public UISprite sprite;

	public UILabelLocalized labelNumber;

	public UILabelLocalized labelLv;

	public UILabelLocalized labelName;

	public UILabelLocalized labelUserNum;

	public GuildSiegeWarDefenceUserListItem.SetFlag selectFlag;

	public GameObject checkIconObj;

	public GameObject descObj;

	private int facilityId;

	public Action<EnumData.GuildSiegeFacilitySlot, GuildSiegeWarDefenceFacilityListItem> onClick;

	public EnumData.GuildSiegeFacilitySlot facilitySlot { get; protected set; }

	public int loadedUserNum { get; protected set; }

	public int loadedNpcNum { get; protected set; }

	public int loadedMemberNum
	{
		get
		{
			return 0;
		}
	}

	public void SetData(int id, EnumData.GuildSiegeFacilitySlot slot, int userNum, int npcNum, int level, Action<EnumData.GuildSiegeFacilitySlot, GuildSiegeWarDefenceFacilityListItem> onClick)
	{
	}

	private void UpdateUserNumLabel()
	{
	}

	private void OnClick()
	{
	}

	public void Select(bool select)
	{
	}

	public void AddUserNum(int num, bool isNpc)
	{
	}

	private void OnDestroy()
	{
	}
}
