using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_guild_corps_passive : IFlatbufferObject
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

		public int PassiveIndex
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public key_guild_corps_passive __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<key_guild_corps_passive> Createkey_guild_corps_passive(FlatBufferBuilder builder, ulong GuildId, int PassiveIndex)
		{
			builder.Prep(8, 16);
			builder.PutInt(PassiveIndex);
			builder.PutUlong(GuildId);
			return new Offset<key_guild_corps_passive>(builder.Offset);
		}
	}
}
