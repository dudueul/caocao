using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct direction_change_hp : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return null;
			}
		}

		public int TargetPlaceId
		{
			get
			{
				return 0;
			}
		}

		public int TargetCorpsId
		{
			get
			{
				return 0;
			}
		}

		public short TargetPosX
		{
			get
			{
				return 0;
			}
		}

		public short TargetPosY
		{
			get
			{
				return 0;
			}
		}

		public ulong TargetUserId
		{
			get
			{
				return 0uL;
			}
		}

		public short SourcePosX
		{
			get
			{
				return 0;
			}
		}

		public short SourcePosY
		{
			get
			{
				return 0;
			}
		}

		public ulong SourceUserId
		{
			get
			{
				return 0uL;
			}
		}

		public int BeforeHp
		{
			get
			{
				return 0;
			}
		}

		public int AfterHp
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public direction_change_hp __assign(int _i, ByteBuffer _bb)
		{
			return default(direction_change_hp);
		}

		public static Offset<direction_change_hp> Createdirection_change_hp(FlatBufferBuilder builder, int TargetPlaceId, int TargetCorpsId, short TargetPosX, short TargetPosY, ulong TargetUserId, short SourcePosX, short SourcePosY, ulong SourceUserId, int BeforeHp, int AfterHp)
		{
			return default(Offset<direction_change_hp>);
		}
	}
}
