using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct corps_custom_function : IFlatbufferObject
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

		public short CustomActionType
		{
			get
			{
				return 0;
			}
		}

		public int CustomActionKey
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

		public short State
		{
			get
			{
				return 0;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public corps_custom_function __assign(int _i, ByteBuffer _bb)
		{
			return default(corps_custom_function);
		}

		public static Offset<corps_custom_function> Createcorps_custom_function(FlatBufferBuilder builder, int CorpsId, short CustomActionType, int CustomActionKey, int AvailableDate, int AvailableDuration, int CooldownDate, short State)
		{
			return default(Offset<corps_custom_function>);
		}
	}
}
