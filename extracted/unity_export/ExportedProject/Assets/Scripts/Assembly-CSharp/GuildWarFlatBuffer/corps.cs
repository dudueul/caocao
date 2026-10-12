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

		public ulong UserId
		{
			get
			{
				return 0uL;
			}
		}

		public ulong GuildId
		{
			get
			{
				return 0uL;
			}
		}

		public int Atk
		{
			get
			{
				return 0;
			}
		}

		public int Inte
		{
			get
			{
				return 0;
			}
		}

		public int Def
		{
			get
			{
				return 0;
			}
		}

		public int Agi
		{
			get
			{
				return 0;
			}
		}

		public int Mor
		{
			get
			{
				return 0;
			}
		}

		public short PosX
		{
			get
			{
				return 0;
			}
		}

		public short PosY
		{
			get
			{
				return 0;
			}
		}

		public int Exp
		{
			get
			{
				return 0;
			}
		}

		public short ForwardDirection
		{
			get
			{
				return 0;
			}
		}

		public int MilitaryPower
		{
			get
			{
				return 0;
			}
		}

		public int TotalDamage
		{
			get
			{
				return 0;
			}
		}

		public int DestoryCorpsCount
		{
			get
			{
				return 0;
			}
		}

		public int LastestPassiveIndex
		{
			get
			{
				return 0;
			}
		}

		public int SiegeWeaponUseCount
		{
			get
			{
				return 0;
			}
		}

		public short ActionPoint
		{
			get
			{
				return 0;
			}
		}

		public int LastestActionDate
		{
			get
			{
				return 0;
			}
		}

		public int UpdateDate
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

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public corps __assign(int _i, ByteBuffer _bb)
		{
			return default(corps);
		}

		public static Offset<corps> Createcorps(FlatBufferBuilder builder, int CorpsId, ulong UserId, ulong GuildId, int Atk, int Inte, int Def, int Agi, int Mor, short PosX, short PosY, int Exp, short ForwardDirection, int MilitaryPower, int TotalDamage, int DestoryCorpsCount, int LastestPassiveIndex, int SiegeWeaponUseCount, short ActionPoint, int LastestActionDate, int UpdateDate, int CreateDate)
		{
			return default(Offset<corps>);
		}
	}
}
