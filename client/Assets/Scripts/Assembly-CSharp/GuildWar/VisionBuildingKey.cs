using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 8)]
	public struct VisionBuildingKey : IKeyTypeDeserializable<VisionBuildingKey, vision_building, key_vision_building>, IKeyType<VisionBuildingKey>, IKeyType, IEquatable<VisionBuildingKey>
	{
		private int m_p_place_id;

		private short m_pos_x;

		private short m_pos_y;

		public int p_place_id
		{
			get
			{
				return 0;
			}
		}

		public short pos_x
		{
			get
			{
				return 0;
			}
		}

		public short pos_y
		{
			get
			{
				return 0;
			}
		}

		public VisionBuildingKey(int p_place_id, short pos_x, short pos_y)
		{
			m_p_place_id = 0;
			m_pos_x = 0;
			m_pos_y = 0;
		}

		public void DeserializeKey(key_vision_building data)
		{
		}

		public void DeserializeKeyFromData(vision_building data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(VisionBuildingKey left, VisionBuildingKey right)
		{
			return false;
		}

		public static bool operator !=(VisionBuildingKey left, VisionBuildingKey right)
		{
			return false;
		}

		public bool Equals(VisionBuildingKey other)
		{
			return false;
		}

		public override bool Equals(object obj)
		{
			return false;
		}

		public override int GetHashCode()
		{
			return 0;
		}
	}
}
