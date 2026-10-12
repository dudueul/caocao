using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct tile_effect : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return __p.bb;
			}
		}

		public short PosX
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 0);
			}
		}

		public short PosY
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 2);
			}
		}

		public int PTileEffect
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 4);
			}
		}

		public int CreateDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public int UpdateDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 12);
			}
		}

		public int EndDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
		}

		public tile_effect __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<tile_effect> Createtile_effect(FlatBufferBuilder builder, short PosX, short PosY, int PTileEffect, int CreateDate, int UpdateDate, int EndDate)
		{
			builder.Prep(4, 20);
			builder.PutInt(EndDate);
			builder.PutInt(UpdateDate);
			builder.PutInt(CreateDate);
			builder.PutInt(PTileEffect);
			builder.PutShort(PosY);
			builder.PutShort(PosX);
			return new Offset<tile_effect>(builder.Offset);
		}
	}
}
