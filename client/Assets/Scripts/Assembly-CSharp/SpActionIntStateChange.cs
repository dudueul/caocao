using BattleRecorder;

public sealed class SpActionIntStateChange : Action
{
	private UnitObject m_unitObject;

	private EnumData.spAction m_spActionId;

	private string m_fieldName;

	private int m_oldValue;

	private int m_newValue;

	public override byte key
	{
		get
		{
			return 0;
		}
	}

	private SpActionIntStateChange()
	{
	}

	public SpActionIntStateChange(SpActionBase spAction, string fieldName, int oldValue, int newValue)
	{
	}

	public override void _Apply()
	{
	}

	public override void _Revert()
	{
	}

	public override bool NeedToRecord()
	{
		return false;
	}

	private SpActionBase GetSpAction()
	{
		return null;
	}

	public static SpActionIntStateChange Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public override void Write(OptimizedBinaryWriter w)
	{
	}
}
