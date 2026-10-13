using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct building_passive : IFlatbufferObject
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

		public int PassiveIndex
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 4);
			}
		}

		public int PPassiveList
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public int CreateDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 12);
			}
		}

		public int UpdateDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public int EndDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 20);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public building_passive __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<building_passive> Createbuilding_passive(FlatBufferBuilder builder, int PPlaceId, int PassiveIndex, int PPassiveList, int CreateDate, int UpdateDate, int EndDate)
		{
			builder.Prep(4, 24);
			builder.PutInt(EndDate);
			builder.PutInt(UpdateDate);
			builder.PutInt(CreateDate);
			builder.PutInt(PPassiveList);
			builder.PutInt(PassiveIndex);
			builder.PutInt(PPlaceId);
			return new Offset<building_passive>(builder.Offset);
		}
	}
}
