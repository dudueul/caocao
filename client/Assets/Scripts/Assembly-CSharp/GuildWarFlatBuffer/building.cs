using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct building : IFlatbufferObject
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

		public short PosX
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 4);
			}
		}

		public short PosY
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 6);
			}
		}

		public ulong GuildId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 8);
			}
		}

		public ulong VisionGuildId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 16);
			}
		}

		public int BuildingId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 24);
			}
		}

		public int BuildingHp
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 28);
			}
		}

		public int UpdateDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 32);
			}
		}

		public int LastestPassiveIndex
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 36);
			}
		}

		public int OwnerChangeDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 40);
			}
		}

		public int AutoAttackTick
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 44);
			}
		}

		public int AutoAttackIntervalTick
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 48);
			}
		}

		public int RepairStartDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 52);
			}
		}

		public int RepairEndDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 56);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public building __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<building> Createbuilding(FlatBufferBuilder builder, int PPlaceId, short PosX, short PosY, ulong GuildId, ulong VisionGuildId, int BuildingId, int BuildingHp, int UpdateDate, int LastestPassiveIndex, int OwnerChangeDate, int AutoAttackTick, int AutoAttackIntervalTick, int RepairStartDate, int RepairEndDate)
		{
			builder.Prep(8, 64);
			builder.PutInt(RepairEndDate);
			builder.PutInt(RepairStartDate);
			builder.PutInt(AutoAttackIntervalTick);
			builder.PutInt(AutoAttackTick);
			builder.PutInt(OwnerChangeDate);
			builder.PutInt(LastestPassiveIndex);
			builder.PutInt(UpdateDate);
			builder.PutInt(BuildingHp);
			builder.PutInt(BuildingId);
			builder.PutUlong(VisionGuildId);
			builder.PutUlong(GuildId);
			builder.PutShort(PosY);
			builder.PutShort(PosX);
			builder.PutInt(PPlaceId);
			return new Offset<building>(builder.Offset);
		}
	}
}
