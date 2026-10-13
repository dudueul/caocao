using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct direction_corps_moved : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return __p.bb;
			}
		}

		public ulong MovedUserId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 0);
			}
		}

		public int MovedCorpsId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public short BeforePosX
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 12);
			}
		}

		public short BeforePosY
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 14);
			}
		}

		public short AfterPosX
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 16);
			}
		}

		public short AfterPosY
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 18);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public direction_corps_moved __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<direction_corps_moved> Createdirection_corps_moved(FlatBufferBuilder builder, ulong MovedUserId, int MovedCorpsId, short BeforePosX, short BeforePosY, short AfterPosX, short AfterPosY)
		{
			builder.Prep(8, 24);
			builder.PutShort(AfterPosY);
			builder.PutShort(AfterPosX);
			builder.PutShort(BeforePosY);
			builder.PutShort(BeforePosX);
			builder.PutInt(MovedCorpsId);
			builder.PutUlong(MovedUserId);
			return new Offset<direction_corps_moved>(builder.Offset);
		}
	}
}
