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
				return __p.bb;
			}
		}

		public int CorpsId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 0);
			}
		}

		public short PosX
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 4);
			}
		}

		public short PosY
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 6);
			}
		}

		public ulong GuildId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 8);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public vision_corps __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<vision_corps> Createvision_corps(FlatBufferBuilder builder, int CorpsId, short PosX, short PosY, ulong GuildId)
		{
			builder.Prep(8, 16);
			builder.PutUlong(GuildId);
			builder.PutShort(PosY);
			builder.PutShort(PosX);
			builder.PutInt(CorpsId);
			return new Offset<vision_corps>(builder.Offset);
		}
	}
}
