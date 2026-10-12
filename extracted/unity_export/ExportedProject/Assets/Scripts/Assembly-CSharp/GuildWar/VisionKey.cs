using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 16)]
	public struct VisionKey : IKeyTypeDeserializable<VisionKey, vision, key_vision>, IKeyType<VisionKey>, IKeyType, IEquatable<VisionKey>
	{
		private ulong m_guild_id;

		private short m_pos_x;

		private short m_pos_y;

		public ulong guild_id
		{
			get
			{
				return 0uL;
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

		public VisionKey(ulong guild_id, short pos_x, short pos_y)
		{
			m_guild_id = 0uL;
			m_pos_x = 0;
			m_pos_y = 0;
		}

		public void DeserializeKey(key_vision data)
		{
		}

		public void DeserializeKeyFromData(vision data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(VisionKey left, VisionKey right)
		{
			return false;
		}

		public static bool operator !=(VisionKey left, VisionKey right)
		{
			return false;
		}

		public bool Equals(VisionKey other)
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
