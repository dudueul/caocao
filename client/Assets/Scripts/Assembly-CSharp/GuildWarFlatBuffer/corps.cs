using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct corps : IFlatbufferObject
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

		public ulong UserId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 8);
			}
		}

		public ulong GuildId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 16);
			}
		}

		public int Atk
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 24);
			}
		}

		public int Inte
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 28);
			}
		}

		public int Def
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 32);
			}
		}

		public int Agi
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 36);
			}
		}

		public int Mor
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 40);
			}
		}

		public short PosX
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 44);
			}
		}

		public short PosY
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 46);
			}
		}

		public int Exp
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 48);
			}
		}

		public short ForwardDirection
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 52);
			}
		}

		public int MilitaryPower
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 56);
			}
		}

		public int TotalDamage
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 60);
			}
		}

		public int DestoryCorpsCount
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 64);
			}
		}

		public int LastestPassiveIndex
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 68);
			}
		}

		public int SiegeWeaponUseCount
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 72);
			}
		}

		public short ActionPoint
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 76);
			}
		}

		public int LastestActionDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 80);
			}
		}

		public int UpdateDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 84);
			}
		}

		public int CreateDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 88);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public corps __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<corps> Createcorps(FlatBufferBuilder builder, int CorpsId, ulong UserId, ulong GuildId, int Atk, int Inte, int Def, int Agi, int Mor, short PosX, short PosY, int Exp, short ForwardDirection, int MilitaryPower, int TotalDamage, int DestoryCorpsCount, int LastestPassiveIndex, int SiegeWeaponUseCount, short ActionPoint, int LastestActionDate, int UpdateDate, int CreateDate)
		{
			builder.Prep(8, 96);
			builder.PutInt(CreateDate);
			builder.PutInt(UpdateDate);
			builder.PutInt(LastestActionDate);
			builder.PutShort(ActionPoint);
			builder.PutInt(SiegeWeaponUseCount);
			builder.PutInt(LastestPassiveIndex);
			builder.PutInt(DestoryCorpsCount);
			builder.PutInt(TotalDamage);
			builder.PutInt(MilitaryPower);
			builder.PutShort(ForwardDirection);
			builder.PutInt(Exp);
			builder.PutShort(PosY);
			builder.PutShort(PosX);
			builder.PutInt(Mor);
			builder.PutInt(Agi);
			builder.PutInt(Def);
			builder.PutInt(Inte);
			builder.PutInt(Atk);
			builder.PutUlong(GuildId);
			builder.PutUlong(UserId);
			builder.PutInt(CorpsId);
			return new Offset<corps>(builder.Offset);
		}
	}
}
