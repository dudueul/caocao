using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct user : IFlatbufferObject
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

		public int NationName
		{
			get
			{
				return 0;
			}
		}

		public int PPlaceId
		{
			get
			{
				return 0;
			}
		}

		public int Contribution
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

		public user __assign(int _i, ByteBuffer _bb)
		{
			return default(user);
		}

		public static Offset<user> Createuser(FlatBufferBuilder builder, ulong GuildId, ulong UserId, int FaceUnitId, int FaceSkinitemId, int NationName, int PPlaceId, int Contribution, int CreateDate)
		{
			return default(Offset<user>);
		}
	}
}
