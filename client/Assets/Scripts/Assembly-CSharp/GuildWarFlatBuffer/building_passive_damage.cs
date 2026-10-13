using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct building_passive_damage : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return __p.bb;
			}
		}

		public int PPlaceId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 0);
			}
		}

		public int PassiveIndex
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 4);
			}
		}

		public int PassiveId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public int CreateDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 12);
			}
		}

		public int FirstTickDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public int LastTickDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 20);
			}
		}

		public int NextTickDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 24);
			}
		}

		public int EndDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 28);
			}
		}

		public short DamageType
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 32);
			}
		}

		public int DamagePower
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 36);
			}
		}

		public int DamageInterval
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 40);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public building_passive_damage __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<building_passive_damage> Createbuilding_passive_damage(FlatBufferBuilder builder, int PPlaceId, int PassiveIndex, int PassiveId, int CreateDate, int FirstTickDate, int LastTickDate, int NextTickDate, int EndDate, short DamageType, int DamagePower, int DamageInterval)
		{
			builder.Prep(4, 44);
			builder.PutInt(DamageInterval);
			builder.PutInt(DamagePower);
			builder.PutShort(DamageType);
			builder.PutInt(EndDate);
			builder.PutInt(NextTickDate);
			builder.PutInt(LastTickDate);
			builder.PutInt(FirstTickDate);
			builder.PutInt(CreateDate);
			builder.PutInt(PassiveId);
			builder.PutInt(PassiveIndex);
			builder.PutInt(PPlaceId);
			return new Offset<building_passive_damage>(builder.Offset);
		}
	}
}
