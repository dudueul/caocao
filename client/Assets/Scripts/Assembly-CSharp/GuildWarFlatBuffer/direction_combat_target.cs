using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct direction_combat_target : IFlatbufferObject
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

		public int TargetPlaceId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 4);
			}
		}

		public int TargetCorpsId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public short TargetTileX
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 12);
			}
		}

		public short TargetTileY
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 14);
			}
		}

		public short TargetPosX
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 16);
			}
		}

		public short TargetPosY
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 18);
			}
		}

		public ulong TargetUserId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 24);
			}
		}

		public int SourcePlaceId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 32);
			}
		}

		public int SourceCorpsId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 36);
			}
		}

		public short SourcePosX
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 40);
			}
		}

		public short SourcePosY
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 42);
			}
		}

		public ulong SourceUserId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 48);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public direction_combat_target __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<direction_combat_target> Createdirection_combat_target(FlatBufferBuilder builder, int PCombatAction, int TargetPlaceId, int TargetCorpsId, short TargetTileX, short TargetTileY, short TargetPosX, short TargetPosY, ulong TargetUserId, int SourcePlaceId, int SourceCorpsId, short SourcePosX, short SourcePosY, ulong SourceUserId)
		{
			builder.Prep(8, 56);
			builder.PutUlong(SourceUserId);
			builder.PutShort(SourcePosY);
			builder.PutShort(SourcePosX);
			builder.PutInt(SourceCorpsId);
			builder.PutInt(SourcePlaceId);
			builder.PutUlong(TargetUserId);
			builder.PutShort(TargetPosY);
			builder.PutShort(TargetPosX);
			builder.PutShort(TargetTileY);
			builder.PutShort(TargetTileX);
			builder.PutInt(TargetCorpsId);
			builder.PutInt(TargetPlaceId);
			builder.PutInt(PCombatAction);
			return new Offset<direction_combat_target>(builder.Offset);
		}
	}
}
