using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_corps_custom_function : IFlatbufferObject
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

		public void __init(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
		}

		public key_corps_custom_function __assign(int _i, ByteBuffer _bb)
		{
			__p.bb_pos = _i;
			__p.bb = _bb;
			return this;
		}

		public static Offset<key_corps_custom_function> Createkey_corps_custom_function(FlatBufferBuilder builder, int CorpsId, short CustomActionType, int CustomActionKey)
		{
			builder.Prep(4, 12);
			builder.PutInt(CustomActionKey);
			builder.PutShort(CustomActionType);
			builder.PutInt(CorpsId);
			return new Offset<key_corps_custom_function>(builder.Offset);
		}
	}
}
