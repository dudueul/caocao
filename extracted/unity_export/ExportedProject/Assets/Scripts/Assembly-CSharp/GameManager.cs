public class GameManager : Singleton<GameManager>
{
	private UserDataSet m_userDataSet;

	private GuildDataSet m_guildDataSet;

	private AnnihilationWarManager m_annihilationWar;

	private ThousandBattleManager m_thousandBattleManager;

	private FilterManager m_filterManager;

	private GuildSiegeWarManager m_guildSiegeWar;

	private CompetitionManager m_competition;

	private EventScheduleManager m_eventSchedule;

	private WorldbossDataSet m_worldbossDataSet;

	public UserDataSet userDataSet
	{
		get
		{
			return null;
		}
	}

	public GuildDataSet guildDataSet
	{
		get
		{
			return null;
		}
	}

	public AnnihilationWarManager annihilationWar
	{
		get
		{
			return null;
		}
	}

	public ThousandBattleManager thousandBattleManager
	{
		get
		{
			return null;
		}
	}

	public FilterManager filterManager
	{
		get
		{
			return null;
		}
	}

	public GuildSiegeWarManager guildSiegeWar
	{
		get
		{
			return null;
		}
	}

	public CompetitionManager competition
	{
		get
		{
			return null;
		}
	}

	public EventScheduleManager eventSchedule
	{
		get
		{
			return null;
		}
	}

	public WorldbossDataSet worldbossDataSet
	{
		get
		{
			return null;
		}
	}

	public void ReceivedMemoryWarning(string message)
	{
	}
}
