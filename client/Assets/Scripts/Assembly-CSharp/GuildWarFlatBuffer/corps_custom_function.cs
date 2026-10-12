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
				return __p.bb;
			}
		}

		public int CorpsId
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 0);
			}
		}

		public short CustomActionType
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 4);
			}
		}

		public int CustomActionKey
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 8);
			}
		}

		public int AvailableDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 12);
			}
		}

		public int AvailableDuration
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 16);
			}
		}

		public int CooldownDate
		{
			get
			{
				return __p.bb.GetInt(__p.bb_pos + 20);
			}
		}

		public short State
		{
			get
			{
				return __p.bb.GetShort(__p.bb_pos + 24);
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
		}

		public corps_custom_function __assign(int _i, ByteBuffer _bb)
		{
			__p = new Struct(_i, _bb);
			return this;
		}

		public static Offset<corps_custom_function> Createcorps_custom_function(FlatBufferBuilder builder, int CorpsId, short CustomActionType, int CustomActionKey, int AvailableDate, int AvailableDuration, int CooldownDate, short State)
		{
			builder.Prep(4, 28);
			builder.PutShort(State);
			builder.PutInt(CooldownDate);
			builder.PutInt(AvailableDuration);
			builder.PutInt(AvailableDate);
			builder.PutInt(CustomActionKey);
			builder.PutShort(CustomActionType);
			builder.PutInt(CorpsId);
			return new Offset<corps_custom_function>(builder.Offset);
		}
	}
}
