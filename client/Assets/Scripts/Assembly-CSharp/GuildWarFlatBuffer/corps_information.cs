using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct corps_information : IFlatbufferObject
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

		public int FaceUnitId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 4);
			}
		}

		public int FaceSkinitemId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public int NationName
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 12);
			}
		}

		public int JobId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public int JobTypeId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 20);
			}
		}

		public int BaseAtk
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 24);
			}
		}

		public int BaseInte
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 28);
			}
		}

		public int BaseDef
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 32);
			}
		}

		public int BaseAgi
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 36);
			}
		}

		public int BaseMor
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 40);
			}
		}

		public int AdditiveAtkPercent
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 44);
			}
		}

		public int AdditiveIntePercent
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 48);
			}
		}

		public int AdditiveDefPercent
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 52);
			}
		}

		public int AdditiveAgiPercent
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 56);
			}
		}

		public int AdditiveMorPercent
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 60);
			}
		}

		public short JobMaxLevel
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 64);
			}
		}

		public int MaxMilitaryPower
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 68);
			}
		}

		public short MaxActionPoint
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 72);
			}
		}

		public int ActionPointRecoveryTime
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 76);
			}
		}

		public int ActionPointRecoveryAmount
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 80);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public corps_information __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<corps_information> Createcorps_information(FlatBufferBuilder builder, int CorpsId, int FaceUnitId, int FaceSkinitemId, int NationName, int JobId, int JobTypeId, int BaseAtk, int BaseInte, int BaseDef, int BaseAgi, int BaseMor, int AdditiveAtkPercent, int AdditiveIntePercent, int AdditiveDefPercent, int AdditiveAgiPercent, int AdditiveMorPercent, short JobMaxLevel, int MaxMilitaryPower, short MaxActionPoint, int ActionPointRecoveryTime, int ActionPointRecoveryAmount)
		{
			builder.Prep(4, 84);
			builder.PutInt(ActionPointRecoveryAmount);
			builder.PutInt(ActionPointRecoveryTime);
			builder.PutShort(MaxActionPoint);
			builder.PutInt(MaxMilitaryPower);
			builder.PutShort(JobMaxLevel);
			builder.PutInt(AdditiveMorPercent);
			builder.PutInt(AdditiveAgiPercent);
			builder.PutInt(AdditiveDefPercent);
			builder.PutInt(AdditiveIntePercent);
			builder.PutInt(AdditiveAtkPercent);
			builder.PutInt(BaseMor);
			builder.PutInt(BaseAgi);
			builder.PutInt(BaseDef);
			builder.PutInt(BaseInte);
			builder.PutInt(BaseAtk);
			builder.PutInt(JobTypeId);
			builder.PutInt(JobId);
			builder.PutInt(NationName);
			builder.PutInt(FaceSkinitemId);
			builder.PutInt(FaceUnitId);
			builder.PutInt(CorpsId);
			return new Offset<corps_information>(builder.Offset);
		}
	}
}
