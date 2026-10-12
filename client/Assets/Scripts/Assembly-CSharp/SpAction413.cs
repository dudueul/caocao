public class SpAction413 : SpActionBase
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

	public override float OnGetCalculatedFloat(float atkRate)
	{
		return 0f;
	}
}
