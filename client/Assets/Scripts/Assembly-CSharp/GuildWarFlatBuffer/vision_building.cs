using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct vision_building : IFlatbufferObject
	{
		private Struct __p;

		public ByteBuffer ByteBuffer
		{
			get
			{
				return null;
			}
		}

		public int PPlaceId
		{
			get
			{
				return 0;
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

		public ulong GuildId
		{
			get
			{
				return 0uL;
			}
		}

		public void __init(int _i, ByteBuffer _bb)
		{
		}

		public vision_building __assign(int _i, ByteBuffer _bb)
		{
			return default(vision_building);
		}

		public static Offset<vision_building> Createvision_building(FlatBufferBuilder builder, int PPlaceId, short PosX, short PosY, ulong GuildId)
		{
			return default(Offset<vision_building>);
		}
	}
}
