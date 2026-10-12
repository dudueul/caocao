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
				return __p.bb;
			}
		}

		public int TargetPlaceId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 0);
			}
		}

		public int TargetCorpsId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 4);
			}
		}

		public short TargetPosX
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 8);
			}
		}

		public short TargetPosY
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 10);
			}
		}

		public ulong TargetUserId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 16);
			}
		}

		public short SourcePosX
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 24);
			}
		}

		public short SourcePosY
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 26);
			}
		}

		public ulong SourceUserId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 32);
			}
		}

		public int BeforeHp
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 40);
			}
		}

		public int AfterHp
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 44);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
		}

		public direction_change_hp __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<direction_change_hp> Createdirection_change_hp(FlatBufferBuilder builder, int TargetPlaceId, int TargetCorpsId, short TargetPosX, short TargetPosY, ulong TargetUserId, short SourcePosX, short SourcePosY, ulong SourceUserId, int BeforeHp, int AfterHp)
		{
			builder.Prep(8, 48);
			builder.PutInt(AfterHp);
			builder.PutInt(BeforeHp);
			builder.PutUlong(SourceUserId);
			builder.PutShort(SourcePosY);
			builder.PutShort(SourcePosX);
			builder.PutUlong(TargetUserId);
			builder.PutShort(TargetPosY);
			builder.PutShort(TargetPosX);
			builder.PutInt(TargetCorpsId);
			builder.PutInt(TargetPlaceId);
			return new Offset<direction_change_hp>(builder.Offset);
		}
	}
}
