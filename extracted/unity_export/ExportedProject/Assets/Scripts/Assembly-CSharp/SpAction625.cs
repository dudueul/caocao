public class SpAction625 : SpActionBase
{
	private SpActionIntState m_moveStep;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	private int MoveStep
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

	protected override void OnActiveOnce()
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

	public override int OnGetCalculatedInt(int value, bool isSimulate)
	{
		return 0;
	}
}
