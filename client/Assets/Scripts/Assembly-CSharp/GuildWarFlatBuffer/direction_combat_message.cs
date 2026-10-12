using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct direction_combat_message : IFlatbufferObject
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

		public int PCombatMessage
		{
			get
			{
				return 0;
			}
		}

		public int CustomKey
		{
			get
			{
				return 0;
			}
		}

		public int MessageParamsGuild
		{
			get
			{
				return 0;
			}
		}

		public int MessageParamsOther
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public direction_combat_message __assign(int _i, ByteBuffer _bb)
		{
			return default(direction_combat_message);
		}

		public static Offset<direction_combat_message> Createdirection_combat_message(FlatBufferBuilder builder, ulong GuildId, int PCombatMessage, int CustomKey, int MessageParamsGuild, int MessageParamsOther)
		{
			return default(Offset<direction_combat_message>);
		}
	}
}
