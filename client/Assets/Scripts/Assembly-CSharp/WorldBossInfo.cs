using CodeStage.AntiCheat.ObscuredTypes;

public class WorldBossInfo
{
	private ObscuredInt m_worldBossId;

	private ObscuredInt m_bossUnitId;

	private ObscuredString m_yyyymmdd;

	private ObscuredInt m_phase;

	private ObscuredInt[] m_randomPassives;

	public int WorldBossId
	{
		get
		{
			return 0;
		}
	}

	public int BossUnitId
	{
		get
		{
			return 0;
		}
	}

	public string yyyymmdd
	{
		get
		{
			return null;
		}
	}

	public int phase
	{
		get
		{
			return 0;
		}
	}

	public void Set(WorldBoss permWorldBoss, string yyyymmdd, int phase)
	{
	}

	public void Set(WorldBoss pdWorldboss, WorldBossInstance instanceData)
	{
	}

	public int GetRandomPassive(int index)
	{
		return 0;
	}

	public void Clear()
	{
	}
}
