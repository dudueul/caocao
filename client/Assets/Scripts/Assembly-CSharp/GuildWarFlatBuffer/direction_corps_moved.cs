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
				return null;
			}
		}

		public ulong MovedUserId
		{
			get
			{
				return 0uL;
			}
		}

		public int MovedCorpsId
		{
			get
			{
				return 0;
			}
		}

		public short BeforePosX
		{
			get
			{
				return 0;
			}
		}

		public short BeforePosY
		{
			get
			{
				return 0;
			}
		}

		public short AfterPosX
		{
			get
			{
				return 0;
			}
		}

		public short AfterPosY
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public direction_corps_moved __assign(int _i, ByteBuffer _bb)
		{
			return default(direction_corps_moved);
		}

		public static Offset<direction_corps_moved> Createdirection_corps_moved(FlatBufferBuilder builder, ulong MovedUserId, int MovedCorpsId, short BeforePosX, short BeforePosY, short AfterPosX, short AfterPosY)
		{
			return default(Offset<direction_corps_moved>);
		}
	}
}
