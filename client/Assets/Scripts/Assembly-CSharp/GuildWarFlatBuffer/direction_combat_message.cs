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

		public int PCombatMessage
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public int CustomKey
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 12);
			}
		}

		public int MessageParamsGuild
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public int MessageParamsOther
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 20);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public direction_combat_message __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<direction_combat_message> Createdirection_combat_message(FlatBufferBuilder builder, ulong GuildId, int PCombatMessage, int CustomKey, int MessageParamsGuild, int MessageParamsOther)
		{
			builder.Prep(8, 24);
			builder.PutInt(MessageParamsOther);
			builder.PutInt(MessageParamsGuild);
			builder.PutInt(CustomKey);
			builder.PutInt(PCombatMessage);
			builder.PutUlong(GuildId);
			return new Offset<direction_combat_message>(builder.Offset);
		}
	}
}
