using Caocao;

public class ControlEventFlow : Singleton<ControlEventFlow>
{
	private enum Phase
	{
		eventPhase = 0,
		controlPhase = 1
	}

	private bool m_IsFinishedBattle;

	public bool m_IsEventSkipping;

	public bool IsEventControl
	{
		get
		{
			return false;
		}
	}

	public bool IsFinishedBattle
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool IsEventSkipping
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public void Init()
	{
	}

	public void DoStartEventPhrase()
	{
	}

	public void DoFinishEventPhrase()
	{
	}

	public bool IsExistPreEvent(int CurrentTurn, UnitSide CurrentSide)
	{
		return false;
	}
}
