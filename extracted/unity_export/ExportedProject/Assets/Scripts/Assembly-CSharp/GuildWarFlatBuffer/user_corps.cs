using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct user_corps : IFlatbufferObject
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

		public ulong UserId
		{
			get
			{
				return 0uL;
			}
		}

		public int CorpsId
		{
			get
			{
				return 0;
			}
		}

		public int CorpsSucceedingExp
		{
			get
			{
				return 0;
			}
		}

		public int FaceUnitId
		{
			get
			{
				return 0;
			}
		}

		public int FaceSkinitemId
		{
			get
			{
				return 0;
			}
		}

		public int JobId
		{
			get
			{
				return 0;
			}
		}

		public int JobTypeId
		{
			get
			{
				return 0;
			}
		}

		public int CreateCount
		{
			get
			{
				return 0;
			}
		}

		public int CorpsCreatableDate
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

		public user_corps __assign(int _i, ByteBuffer _bb)
		{
			return default(user_corps);
		}

		public static Offset<user_corps> Createuser_corps(FlatBufferBuilder builder, ulong GuildId, ulong UserId, int CorpsId, int CorpsSucceedingExp, int FaceUnitId, int FaceSkinitemId, int JobId, int JobTypeId, int CreateCount, int CorpsCreatableDate, int CreateDate)
		{
			return default(Offset<user_corps>);
		}
	}
}
