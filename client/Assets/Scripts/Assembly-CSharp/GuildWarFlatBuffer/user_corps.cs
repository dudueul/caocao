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

		public int CorpsId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public int CorpsSucceedingExp
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 20);
			}
		}

		public int FaceUnitId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 24);
			}
		}

		public int FaceSkinitemId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 28);
			}
		}

		public int JobId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 32);
			}
		}

		public int JobTypeId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 36);
			}
		}

		public int CreateCount
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 40);
			}
		}

		public int CorpsCreatableDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 44);
			}
		}

		public int CreateDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 48);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
		}

		public user_corps __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<user_corps> Createuser_corps(FlatBufferBuilder builder, ulong GuildId, ulong UserId, int CorpsId, int CorpsSucceedingExp, int FaceUnitId, int FaceSkinitemId, int JobId, int JobTypeId, int CreateCount, int CorpsCreatableDate, int CreateDate)
		{
			builder.Prep(8, 56);
			builder.PutInt(CreateDate);
			builder.PutInt(CorpsCreatableDate);
			builder.PutInt(CreateCount);
			builder.PutInt(JobTypeId);
			builder.PutInt(JobId);
			builder.PutInt(FaceSkinitemId);
			builder.PutInt(FaceUnitId);
			builder.PutInt(CorpsSucceedingExp);
			builder.PutInt(CorpsId);
			builder.PutUlong(UserId);
			builder.PutUlong(GuildId);
			return new Offset<user_corps>(builder.Offset);
		}
	}
}
