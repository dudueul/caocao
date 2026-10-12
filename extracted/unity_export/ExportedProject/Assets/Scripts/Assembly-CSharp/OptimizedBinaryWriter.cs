using System.IO;

public sealed class OptimizedBinaryWriter : BinaryWriter
{
	private byte[] buffer;

	public OptimizedBinaryWriter(Stream output)
	{
	}

	public override void Write(string value)
	{
	}

	public override void Write(uint value)
	{
	}

	public override void Write(int value)
	{
	}
}
