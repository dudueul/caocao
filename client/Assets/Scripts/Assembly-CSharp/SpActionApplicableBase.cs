public class SpActionApplicableBase : SpActionBase
{
	protected SpActionIntState m_appliedCount;

	public override EnumData.spAction id
	{
		get
		{
			return (EnumData.spAction)0;
		}
	}

	protected int appliedCount
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public virtual bool canApply
	{
		get
		{
			return false;
		}
	}

	public void Apply()
	{
	}

	public override void ResetIntState()
	{
	}
}
