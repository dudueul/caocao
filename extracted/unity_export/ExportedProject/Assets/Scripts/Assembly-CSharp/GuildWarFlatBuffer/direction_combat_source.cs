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

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public direction_combat_source __assign(int _i, ByteBuffer _bb)
		{
			return default(direction_combat_source);
		}

		public static Offset<direction_combat_source> Createdirection_combat_source(FlatBufferBuilder builder, int PCombatAction, int SourcePlaceId, int SourceCorpsId, short SourcePosX, short SourcePosY, ulong SourceUserId, short TargetPosX, short TargetPosY, ulong TargetUserId)
		{
			return default(Offset<direction_combat_source>);
		}
	}
}
