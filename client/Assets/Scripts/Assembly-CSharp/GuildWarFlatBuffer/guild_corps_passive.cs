using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct guild_corps_passive : IFlatbufferObject
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

		public int PPassiveList
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 12);
			}
		}

		public int PassiveEndDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public guild_corps_passive __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<guild_corps_passive> Createguild_corps_passive(FlatBufferBuilder builder, ulong GuildId, int PassiveIndex, int PPassiveList, int PassiveEndDate)
		{
			builder.Prep(8, 24);
			builder.PutInt(PassiveEndDate);
			builder.PutInt(PPassiveList);
			builder.PutInt(PassiveIndex);
			builder.PutUlong(GuildId);
			return new Offset<guild_corps_passive>(builder.Offset);
		}
	}
}
