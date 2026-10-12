using LitJson;

public class ThousandBattleManager
{
	private ThousandBattleInfo m_currentInfo;

	private int[] m_passSectionCost;

	private int m_lastPassStage;

	public static ThousandBattleManager instance
	{
		get
		{
			return null;
		}
	}

	public ThousandBattleInfo currentInfo
	{
		get
		{
			return null;
		}
	}

	public bool needAnimate { get; set; }

	public JsonData jsonThousandBattleInfo { get; set; }

	public int[] PassSectionCost
	{
		get
		{
			return null;
		}
	}

	public int LastPassStage
	{
		get
		{
			return 0;
		}
	}

	public void Deserialize(JsonData jsonData)
	{
	}

	public ThousandBattle GetStageInfo(int stage)
	{
		return null;
	}

	public int GetTotalRewardCount(int stage, EnumData.ItemType itemType)
	{
		return 0;
	}
}
