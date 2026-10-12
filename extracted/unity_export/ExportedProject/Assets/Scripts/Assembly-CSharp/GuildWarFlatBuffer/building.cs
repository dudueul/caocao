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
				return null;
			}
		}

		public int PPlaceId
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

		public ulong GuildId
		{
			get
			{
				return 0uL;
			}
		}

		public ulong VisionGuildId
		{
			get
			{
				return 0uL;
			}
		}

		public int BuildingId
		{
			get
			{
				return 0;
			}
		}

		public int BuildingHp
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

		public int LastestPassiveIndex
		{
			get
			{
				return 0;
			}
		}

		public int OwnerChangeDate
		{
			get
			{
				return 0;
			}
		}

		public int AutoAttackTick
		{
			get
			{
				return 0;
			}
		}

		public int AutoAttackIntervalTick
		{
			get
			{
				return 0;
			}
		}

		public int RepairStartDate
		{
			get
			{
				return 0;
			}
		}

		public int RepairEndDate
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public building __assign(int _i, ByteBuffer _bb)
		{
			return default(building);
		}

		public static Offset<building> Createbuilding(FlatBufferBuilder builder, int PPlaceId, short PosX, short PosY, ulong GuildId, ulong VisionGuildId, int BuildingId, int BuildingHp, int UpdateDate, int LastestPassiveIndex, int OwnerChangeDate, int AutoAttackTick, int AutoAttackIntervalTick, int RepairStartDate, int RepairEndDate)
		{
			return default(Offset<building>);
		}
	}
}
