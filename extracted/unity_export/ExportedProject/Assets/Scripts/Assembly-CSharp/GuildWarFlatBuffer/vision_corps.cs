using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct vision_corps : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return null;
			}
		}

		public int CorpsId
		{
			get
			{
				return 0;
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

		public ulong GuildId
		{
			get
			{
				return 0uL;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public vision_corps __assign(int _i, ByteBuffer _bb)
		{
			return default(vision_corps);
		}

		public static Offset<vision_corps> Createvision_corps(FlatBufferBuilder builder, int CorpsId, short PosX, short PosY, ulong GuildId)
		{
			return default(Offset<vision_corps>);
		}
	}
}
