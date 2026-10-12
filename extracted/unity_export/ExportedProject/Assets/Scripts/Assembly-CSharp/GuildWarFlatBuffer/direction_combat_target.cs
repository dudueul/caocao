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
				return null;
			}
		}

		public int PCombatAction
		{
			get
			{
				return 0;
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

		public short TargetTileX
		{
			get
			{
				return 0;
			}
		}

		public short TargetTileY
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

		public int SourcePlaceId
		{
			get
			{
				return 0;
			}
		}

		public int SourceCorpsId
		{
			get
			{
				return 0;
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

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public direction_combat_target __assign(int _i, ByteBuffer _bb)
		{
			return default(direction_combat_target);
		}

		public static Offset<direction_combat_target> Createdirection_combat_target(FlatBufferBuilder builder, int PCombatAction, int TargetPlaceId, int TargetCorpsId, short TargetTileX, short TargetTileY, short TargetPosX, short TargetPosY, ulong TargetUserId, int SourcePlaceId, int SourceCorpsId, short SourcePosX, short SourcePosY, ulong SourceUserId)
		{
			return default(Offset<direction_combat_target>);
		}
	}
}
