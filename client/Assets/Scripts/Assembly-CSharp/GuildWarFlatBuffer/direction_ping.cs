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

		public ulong UserId
		{
			get
			{
				return 0uL;
			}
		}

		public int PingId
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

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public direction_ping __assign(int _i, ByteBuffer _bb)
		{
			return default(direction_ping);
		}

		public static Offset<direction_ping> Createdirection_ping(FlatBufferBuilder builder, ulong GuildId, ulong UserId, int PingId, short PosX, short PosY)
		{
			return default(Offset<direction_ping>);
		}
	}
}
