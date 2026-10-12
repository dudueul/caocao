namespace BattleRecorder
{
	public interface IWritable
	{
		byte key { get; }

		void Write(OptimizedBinaryWriter writer);
	}
}
