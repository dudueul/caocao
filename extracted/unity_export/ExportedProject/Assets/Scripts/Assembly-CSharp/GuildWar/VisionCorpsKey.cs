using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 8)]
	public struct VisionCorpsKey : IKeyTypeDeserializable<VisionCorpsKey, vision_corps, key_vision_corps>, IKeyType<VisionCorpsKey>, IKeyType, IEquatable<VisionCorpsKey>
	{
		private int m_corps_id;

		private short m_pos_x;

		private short m_pos_y;

		public int corps_id
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

		public VisionCorpsKey(int corps_id, short pos_x, short pos_y)
		{
			m_corps_id = 0;
			m_pos_x = 0;
			m_pos_y = 0;
		}

		public void DeserializeKey(key_vision_corps data)
		{
		}

		public void DeserializeKeyFromData(vision_corps data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(VisionCorpsKey left, VisionCorpsKey right)
		{
			return false;
		}

		public static bool operator !=(VisionCorpsKey left, VisionCorpsKey right)
		{
			return false;
		}

		public bool Equals(VisionCorpsKey other)
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
