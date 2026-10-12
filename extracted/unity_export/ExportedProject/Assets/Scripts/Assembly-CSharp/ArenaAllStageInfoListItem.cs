using UnityEngine;

public class ArenaAllStageInfoListItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public int Index { get; set; }

		public UserArenaLevel userArenaLevel { get; set; }
	}

	[SerializeField]
	private UILabelLocalized labelLevelName;

	[SerializeField]
	private GameObject objCurrent;

	[SerializeField]
	private GameObject objBoss;

	[SerializeField]
	private GameObject objVictory;

	[SerializeField]
	private UITexture texFace;

	[SerializeField]
	private GameObject objClear;

	[SerializeField]
	private MissionInfoUI missionDesc;

	[SerializeField]
	private UIGrid gridBattleInfo;

	[SerializeField]
	private Transform objTileInfo;

	[SerializeField]
	private Transform[] weathers;

	[SerializeField]
	private UIGrid gridPassive;

	[SerializeField]
	private GameObject objHideBoss;

	[SerializeField]
	private GameObject objDetailDivideLine;

	private CellData data
	{
		get
		{
			return null;
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private EnumData.MISSION_STATUS GetMissionStatus(UserArenaLevel level, bool mission, int curLevel)
	{
		return EnumData.MISSION_STATUS.None;
	}

	private void UpdateUI()
	{
	}
}
