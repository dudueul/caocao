using FlatBuffers;

namespace GuildWarFlatBuffer
{
	public struct building_custom_function : IFlatbufferObject
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

		public short CustomActionType
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

		public building_custom_function __assign(int _i, ByteBuffer _bb)
		{
			return default(building_custom_function);
		}

		public static Offset<building_custom_function> Createbuilding_custom_function(FlatBufferBuilder builder, int PPlaceId, short CustomActionType, int CooldownDate)
		{
			return default(Offset<building_custom_function>);
		}
	}
}
