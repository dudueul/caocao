using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct corps_siege_weapon : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return __p.bb;
			}
		}

		public int CorpsId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 0);
			}
		}

		public int PSiegeWeapon
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 4);
			}
		}

		public int OldCorpsInfoJobId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public int OldCorpsInfoJobTypeId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 12);
			}
		}

		public short OldCorpsInfoJobMaxLevel
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 16);
			}
		}

		public int OldCorpsInfoMaxMilitaryPower
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 20);
			}
		}

		public int OldCorpsInfoActionPointRecoveryAmount
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 24);
			}
		}

		public int OldCorpsInfoActionPointRecoveryTime
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 28);
			}
		}

		public short OldCorpsInfoMaxActionPoint
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 32);
			}
		}

		public int OldCorpsExp
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 36);
			}
		}

		public int OldCorpsMilitaryPower
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 40);
			}
		}

		public short OldCorpsActionPoint
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 44);
			}
		}

		public int OldCorpsLastestActionDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 48);
			}
		}

		public int MountCompleteDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 52);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public corps_siege_weapon __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<corps_siege_weapon> Createcorps_siege_weapon(FlatBufferBuilder builder, int CorpsId, int PSiegeWeapon, int OldCorpsInfoJobId, int OldCorpsInfoJobTypeId, short OldCorpsInfoJobMaxLevel, int OldCorpsInfoMaxMilitaryPower, int OldCorpsInfoActionPointRecoveryAmount, int OldCorpsInfoActionPointRecoveryTime, short OldCorpsInfoMaxActionPoint, int OldCorpsExp, int OldCorpsMilitaryPower, short OldCorpsActionPoint, int OldCorpsLastestActionDate, int MountCompleteDate)
		{
			builder.Prep(4, 56);
			builder.PutInt(MountCompleteDate);
			builder.PutInt(OldCorpsLastestActionDate);
			builder.PutShort(OldCorpsActionPoint);
			builder.PutInt(OldCorpsMilitaryPower);
			builder.PutInt(OldCorpsExp);
			builder.PutShort(OldCorpsInfoMaxActionPoint);
			builder.PutInt(OldCorpsInfoActionPointRecoveryTime);
			builder.PutInt(OldCorpsInfoActionPointRecoveryAmount);
			builder.PutInt(OldCorpsInfoMaxMilitaryPower);
			builder.PutShort(OldCorpsInfoJobMaxLevel);
			builder.PutInt(OldCorpsInfoJobTypeId);
			builder.PutInt(OldCorpsInfoJobId);
			builder.PutInt(PSiegeWeapon);
			builder.PutInt(CorpsId);
			return new Offset<corps_siege_weapon>(builder.Offset);
		}
	}
}
