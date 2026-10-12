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
				return null;
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

		public int PTileEffect
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

		public int UpdateDate
		{
			get
			{
				return 0;
			}
		}

		public int EndDate
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public tile_effect __assign(int _i, ByteBuffer _bb)
		{
			return default(tile_effect);
		}

		public static Offset<tile_effect> Createtile_effect(FlatBufferBuilder builder, short PosX, short PosY, int PTileEffect, int CreateDate, int UpdateDate, int EndDate)
		{
			return default(Offset<tile_effect>);
		}
	}
}
