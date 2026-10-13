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
				return __p.bb;
			}
		}

		public ulong GuildId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 0);
			}
		}

		public ulong UserId
		{
			get
			{
				return __p.bb.GetUlong(__p.bb_pos + 8);
			}
		}

		public int FaceUnitId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public int FaceSkinitemId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 20);
			}
		}

		public int NationName
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 24);
			}
		}

		public int PPlaceId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 28);
			}
		}

		public int Contribution
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 32);
			}
		}

		public int CreateDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 36);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public user __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<user> Createuser(FlatBufferBuilder builder, ulong GuildId, ulong UserId, int FaceUnitId, int FaceSkinitemId, int NationName, int PPlaceId, int Contribution, int CreateDate)
		{
			builder.Prep(8, 40);
			builder.PutInt(CreateDate);
			builder.PutInt(Contribution);
			builder.PutInt(PPlaceId);
			builder.PutInt(NationName);
			builder.PutInt(FaceSkinitemId);
			builder.PutInt(FaceUnitId);
			builder.PutUlong(UserId);
			builder.PutUlong(GuildId);
			return new Offset<user>(builder.Offset);
		}
	}
}
