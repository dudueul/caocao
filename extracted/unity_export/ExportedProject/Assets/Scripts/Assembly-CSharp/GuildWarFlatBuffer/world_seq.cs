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
				return null;
			}
		}

		public short ClanDbShard
		{
			get
			{
				return 0;
			}
		}

		public int WorldId
		{
			get
			{
				return 0;
			}
		}

		public int InstanceIndex
		{
			get
			{
				return 0;
			}
		}

		public int Seq
		{
			get
			{
				return 0;
			}
		}

		public int Tick
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public world_seq __assign(int _i, ByteBuffer _bb)
		{
			return default(world_seq);
		}

		public static Offset<world_seq> Createworld_seq(FlatBufferBuilder builder, short ClanDbShard, int WorldId, int InstanceIndex, int Seq, int Tick)
		{
			return default(Offset<world_seq>);
		}
	}
}
