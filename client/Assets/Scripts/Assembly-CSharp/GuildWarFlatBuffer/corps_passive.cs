using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct corps_passive : IFlatbufferObject
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

		public int PassiveIndex
		{
			get
			{
				return 0;
			}
		}

		public int PPassiveList
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

		public corps_passive __assign(int _i, ByteBuffer _bb)
		{
			return default(corps_passive);
		}

		public static Offset<corps_passive> Createcorps_passive(FlatBufferBuilder builder, int CorpsId, int PassiveIndex, int PPassiveList, int CreateDate, int UpdateDate, int EndDate)
		{
			return default(Offset<corps_passive>);
		}
	}
}
