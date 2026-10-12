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

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_corps_custom_function __assign(int _i, ByteBuffer _bb)
		{
			return default(key_corps_custom_function);
		}

		public static Offset<key_corps_custom_function> Createkey_corps_custom_function(FlatBufferBuilder builder, int CorpsId, short CustomActionType, int CustomActionKey)
		{
			return default(Offset<key_corps_custom_function>);
		}
	}
}
