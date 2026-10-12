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

		public int PassiveIndex
		{
			get
			{
				return 0;
			}
		}

		public int PPassiveList
		{
			get
			{
				return 0;
			}
		}

		public int PassiveEndDate
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public guild_corps_passive __assign(int _i, ByteBuffer _bb)
		{
			return default(guild_corps_passive);
		}

		public static Offset<guild_corps_passive> Createguild_corps_passive(FlatBufferBuilder builder, ulong GuildId, int PassiveIndex, int PPassiveList, int PassiveEndDate)
		{
			return default(Offset<guild_corps_passive>);
		}
	}
}
