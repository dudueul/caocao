using CodeStage.AntiCheat.ObscuredTypes;

public class TimeController : SceneSingleton<TimeController>
{
	public static readonly ObscuredFloat[] ms_fastTimeScale;

	private bool m_paused;

	private float m_holdedTimeScale;

	private bool m_holdTimeScale;

	public ObscuredFloat m_timeScale;

	public ObscuredInt fastStep { get; set; }

	public bool paused
	{
		get
		{
			return false;
		}
		private set
		{
		}
	}

	public bool holdTimeScale
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public float timeScale
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public void ToggleSpeed()
	{
	}

	public void SetSpeed(int idx)
	{
	}

	public void Pause()
	{
	}

	public void Resume()
	{
	}

	protected override void OnDestroy()
	{
	}

	public void UpdateFastBtnClickedCount(int count)
	{
	}
}
