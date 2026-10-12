using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_guild : IFlatbufferObject
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

		public void __init(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
		}

		public key_guild __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<key_guild> Createkey_guild(FlatBufferBuilder builder, ulong GuildId)
		{
			builder.Prep(8, 8);
			builder.PutUlong(GuildId);
			return new Offset<key_guild>(builder.Offset);
		}
	}
}
