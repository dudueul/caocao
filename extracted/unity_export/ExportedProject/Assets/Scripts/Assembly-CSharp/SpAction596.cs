public class SpAction596 : SpActionBase
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

	public void OnMoveCompleted(MoveParam[] moveParams)
	{
	}

	public override int OnGetCalculatedInt(int value)
	{
		return 0;
	}

	public int GetAdjustDefAmount(int baseDef)
	{
		return 0;
	}
}
