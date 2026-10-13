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
				return __p.bb;
			}
		}

		public ulong GuildId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 0);
			}
		}

		public short PosX
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 8);
			}
		}

		public short PosY
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 10);
			}
		}

		public int VisionCount
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 12);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public vision __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<vision> Createvision(FlatBufferBuilder builder, ulong GuildId, short PosX, short PosY, int VisionCount)
		{
			builder.Prep(8, 16);
			builder.PutInt(VisionCount);
			builder.PutShort(PosY);
			builder.PutShort(PosX);
			builder.PutUlong(GuildId);
			return new Offset<vision>(builder.Offset);
		}
	}
}
