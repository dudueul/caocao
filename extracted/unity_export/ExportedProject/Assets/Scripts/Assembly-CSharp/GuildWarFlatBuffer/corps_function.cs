using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct corps_function : IFlatbufferObject
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

		public int PCorpsFunctionId
		{
			get
			{
				return 0;
			}
		}

		public int AvailableDate
		{
			get
			{
				return 0;
			}
		}

		public int AvailableDuration
		{
			get
			{
				return 0;
			}
		}

		public int CooldownDate
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public corps_function __assign(int _i, ByteBuffer _bb)
		{
			return default(corps_function);
		}

		public static Offset<corps_function> Createcorps_function(FlatBufferBuilder builder, int CorpsId, int PCorpsFunctionId, int AvailableDate, int AvailableDuration, int CooldownDate)
		{
			return default(Offset<corps_function>);
		}
	}
}
