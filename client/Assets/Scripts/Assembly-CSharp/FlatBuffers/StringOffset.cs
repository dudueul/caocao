using System.Runtime.InteropServices;

namespace FlatBuffers
{
	[StructLayout((LayoutKind)0, Size = 4)]
	public struct StringOffset
	{
		public int Value;

		public StringOffset(int value)
		{
			Value = 0;
		}
	}
}
