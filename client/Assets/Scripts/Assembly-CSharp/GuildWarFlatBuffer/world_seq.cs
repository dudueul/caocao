using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct world_seq : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return __p.bb;
			}
		}

		public short ClanDbShard
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 0);
			}
		}

		public int WorldId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 4);
			}
		}

		public int InstanceIndex
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public int Seq
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 12);
			}
		}

		public int Tick
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

		public world_seq __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<world_seq> Createworld_seq(FlatBufferBuilder builder, short ClanDbShard, int WorldId, int InstanceIndex, int Seq, int Tick)
		{
			builder.Prep(4, 20);
			builder.PutInt(Tick);
			builder.PutInt(Seq);
			builder.PutInt(InstanceIndex);
			builder.PutInt(WorldId);
			builder.PutShort(ClanDbShard);
			return new Offset<world_seq>(builder.Offset);
		}
	}
}
