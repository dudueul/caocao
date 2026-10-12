public class RunePassiveUI : PassiveUI
{
	private RunePassive m_runePassive;

	private int m_tier;

	private int m_dbLevel;

	private int m_level;

	private int m_exp;

	public bool showDesc;

	private bool m_available;

	private bool m_showAvailable;

	public RunePassive runePassive
	{
		get
		{
			return null;
		}
	}

	public int tier
	{
		get
		{
			return 0;
		}
	}

	public int exp
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool available
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool showAvailable
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public void SetRunePassive(RunePassive runePassive, int tier, int exp)
	{
	}

	public void SetRunePassive(RunePassive runePassive, int tier, int dbLevel, int appliedLevel)
	{
	}

	private void Awake()
	{
	}

	private void UpdateDesc()
	{
	}

	private void UpdateAvailable()
	{
	}
}
