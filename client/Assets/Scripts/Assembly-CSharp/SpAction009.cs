public class SpAction009 : SpActionBase
{
	private SpActionIntState m_moveStepCnt;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	private int moveStepCnt
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public override void ResetIntState()
	{
	}

	protected override void OnActive()
	{
	}

	protected override void OnDeactive()
	{
	}

	private void OnStatusChanged(ControlFlow.statusType statusType)
	{
	}

	public void OnMoveCompleted(MoveParam[] moveParams)
	{
	}

	public override int OnGetCalculatedInt(int addSpActionValue, bool isSimulate)
	{
		return 0;
	}
}
