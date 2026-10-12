using System.Runtime.InteropServices;

namespace FlatBuffers
{
	[StructLayout((LayoutKind)0, Size = 4)]
	public struct VectorOffset
	{
		public int Value;

		public VectorOffset(int value)
		{
			Value = 0;
		}
	}
}
