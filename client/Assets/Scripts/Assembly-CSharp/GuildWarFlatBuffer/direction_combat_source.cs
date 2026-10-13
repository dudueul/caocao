using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct direction_combat_source : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return __p.bb;
			}
		}

		public int PCombatAction
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 0);
			}
		}

		public int SourcePlaceId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 4);
			}
		}

		public int SourceCorpsId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public short SourcePosX
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 12);
			}
		}

		public short SourcePosY
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 14);
			}
		}

		public ulong SourceUserId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 16);
			}
		}

		public short TargetPosX
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 24);
			}
		}

		public short TargetPosY
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 26);
			}
		}

		public ulong TargetUserId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 32);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public direction_combat_source __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<direction_combat_source> Createdirection_combat_source(FlatBufferBuilder builder, int PCombatAction, int SourcePlaceId, int SourceCorpsId, short SourcePosX, short SourcePosY, ulong SourceUserId, short TargetPosX, short TargetPosY, ulong TargetUserId)
		{
			builder.Prep(8, 40);
			builder.PutUlong(TargetUserId);
			builder.PutShort(TargetPosY);
			builder.PutShort(TargetPosX);
			builder.PutUlong(SourceUserId);
			builder.PutShort(SourcePosY);
			builder.PutShort(SourcePosX);
			builder.PutInt(SourceCorpsId);
			builder.PutInt(SourcePlaceId);
			builder.PutInt(PCombatAction);
			return new Offset<direction_combat_source>(builder.Offset);
		}
	}
}
