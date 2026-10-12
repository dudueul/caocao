using BattleRecorder;

public static class BinaryReaderWriterExtensions
{
	public static IntRect ReadIntRect(this OptimizedBinaryReader r)
	{
		return default(IntRect);
	}

	public static void Write(this OptimizedBinaryWriter w, IntRect intRect)
	{
	}

	public static IntVector2 ReadIntVector2(this OptimizedBinaryReader r)
	{
		return default(IntVector2);
	}

	public static void Write(this OptimizedBinaryWriter w, IntVector2 intVec2)
	{
	}

	public static T Read<T>(this OptimizedBinaryReader r) where T : class, IWritable
	{
		return null;
	}

	public static void Write(this OptimizedBinaryWriter w, IWritable writable)
	{
	}
}
