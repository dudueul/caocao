using BattleRecorder;

public class BattleMissionState : IWritable
{
	public bool[] unit;

	public int count;

	public bool success;

	public bool fail;

	public byte key
	{
		get
		{
			return 0;
		}
	}

	public BattleMissionState()
	{
	}

	public BattleMissionState(BattleMissionState r)
	{
	}

	public static BattleMissionState Read(OptimizedBinaryReader r)
	{
		return null;
	}

	public void Write(OptimizedBinaryWriter w)
	{
	}
}
