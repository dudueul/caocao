using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct direction_ping : IFlatbufferObject
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

		public ulong UserId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 8);
			}
		}

		public int PingId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public short PosX
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 20);
			}
		}

		public short PosY
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 22);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
		}

		public direction_ping __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<direction_ping> Createdirection_ping(FlatBufferBuilder builder, ulong GuildId, ulong UserId, int PingId, short PosX, short PosY)
		{
			builder.Prep(8, 24);
			builder.PutShort(PosY);
			builder.PutShort(PosX);
			builder.PutInt(PingId);
			builder.PutUlong(UserId);
			builder.PutUlong(GuildId);
			return new Offset<direction_ping>(builder.Offset);
		}
	}
}
