using UnityEngine;

public class PVPScoreDetailListItem : MonoBehaviour
{
	public UILabelLocalized weekScoreLabel;

	public UILabelLocalized totalScoreLabel;

	public UILabelLocalized battleMapNameLabel;

	public UITexture battleMapImageTexture;

	private AnnihilationWar perWar;

	private UserPvpStatBattleMap map;

	private UserPvpStatSeasonBattleMap m_seasonMap;

	private CompetitionMap perCompetitionMap;

	private int m_seasonWinCount;

	private int m_seasonLoseCount;

	private int m_totalWinCount;

	private int m_totalLoseCount;

	public UserPvpStatSeasonBattleMap data
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public void SetData(int annWarId, int seasonWin, int seasonLose, int totalWin, int totalLose)
	{
	}

	public void SetData(CompetitionMap competitionMap, int seasonWin, int seasonLose, int totalWin, int totalLose)
	{
	}

	private void UpdateUI()
	{
	}

	private void OnDestroy()
	{
	}
}
