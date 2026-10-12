using System;
using System.Collections.Generic;
using UnityEngine;

public class GuildSiegeWarDefenceUserListItem : GuildUIRecruitMemberListItem
{
	[Serializable]
	public class SetFlag
	{
		public GameObject red;

		public GameObject blue;

		public GameObject purple;

		public void SetColor(EnumData.GuildSiegeFacilitySlot facilitySlot)
		{
		}
	}

	public class CellData : GuildUIMemberListObject.GuildMemberCellData
	{
		public List<EnumData.GuildSiegeFacilitySlot> loadedList;

		public List<int> loadedDeckSlot;

		public float defenceRateScore;

		public Action onChangedFacility;
	}

	public UILabelLocalized labelName;

	public SetFlag fortressFlag;

	public SetFlag facilityFlag0;

	public SetFlag facilityFlag1;

	public UILabelLocalized labelScore;

	public CellData cellData
	{
		get
		{
			return null;
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void OnChangedFacility()
	{
	}

	public new void UpdateUI()
	{
	}
}
