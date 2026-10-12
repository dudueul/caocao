using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct key_corps_passive_damage : IFlatbufferObject
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

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public key_corps_passive_damage __assign(int _i, ByteBuffer _bb)
		{
			return default(key_corps_passive_damage);
		}

		public static Offset<key_corps_passive_damage> Createkey_corps_passive_damage(FlatBufferBuilder builder, int CorpsId, int PassiveIndex)
		{
			return default(Offset<key_corps_passive_damage>);
		}
	}
}
