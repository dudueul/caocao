using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct corps_passive_damage : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return null;
			}
		}

		public int CorpsId
		{
			get
			{
				return 0;
			}
		}

		public int PassiveIndex
		{
			get
			{
				return 0;
			}
		}

		public int PassiveId
		{
			get
			{
				return 0;
			}
		}

		public int CreateDate
		{
			get
			{
				return 0;
			}
		}

		public int FirstTickDate
		{
			get
			{
				return 0;
			}
		}

		public int LastTickDate
		{
			get
			{
				return 0;
			}
		}

		public int NextTickDate
		{
			get
			{
				return 0;
			}
		}

		public int EndDate
		{
			get
			{
				return 0;
			}
		}

		public short DamageType
		{
			get
			{
				return 0;
			}
		}

		public int DamagePower
		{
			get
			{
				return 0;
			}
		}

		public int DamageInterval
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public corps_passive_damage __assign(int _i, ByteBuffer _bb)
		{
			return default(corps_passive_damage);
		}

		public static Offset<corps_passive_damage> Createcorps_passive_damage(FlatBufferBuilder builder, int CorpsId, int PassiveIndex, int PassiveId, int CreateDate, int FirstTickDate, int LastTickDate, int NextTickDate, int EndDate, short DamageType, int DamagePower, int DamageInterval)
		{
			return default(Offset<corps_passive_damage>);
		}
	}
}
