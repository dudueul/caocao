using System.IO;
using System.Text;

public sealed class OptimizedBinaryReader : BinaryReader
{
	private StringBuilder m_sb;

	public OptimizedBinaryReader(Stream input)
		: base(null)
	{
	}

	public override string ReadString()
	{
		return null;
	}

	public override uint ReadUInt32()
	{
		return 0u;
	}

	public override int ReadInt32()
	{
		return 0;
	}
}
