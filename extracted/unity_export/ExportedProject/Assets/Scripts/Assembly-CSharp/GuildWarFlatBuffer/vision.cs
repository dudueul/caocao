using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct vision : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return null;
			}
		}

		public ulong GuildId
		{
			get
			{
				return 0uL;
			}
		}

		public short PosX
		{
			get
			{
				return 0;
			}
		}

		public short PosY
		{
			get
			{
				return 0;
			}
		}

		public int VisionCount
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public vision __assign(int _i, ByteBuffer _bb)
		{
			return default(vision);
		}

		public static Offset<vision> Createvision(FlatBufferBuilder builder, ulong GuildId, short PosX, short PosY, int VisionCount)
		{
			return default(Offset<vision>);
		}
	}
}
