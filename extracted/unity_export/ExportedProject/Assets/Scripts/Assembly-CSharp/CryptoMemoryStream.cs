using System.IO;

public sealed class CryptoMemoryStream : MemoryStream
{
	private uint m_lastRandomValue;

	private uint m_randomAdd;

	private const uint m_defaultAdd = 18u;

	public CryptoMemoryStream(byte[] buffer, byte key1, byte key2)
	{
	}

	public override int Read(byte[] buffer, int offset, int count)
	{
		return 0;
	}

	public override int ReadByte()
	{
		return 0;
	}

	public override void Write(byte[] buffer, int offset, int count)
	{
	}

	public override void WriteByte(byte value)
	{
	}
}
