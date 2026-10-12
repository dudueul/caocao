using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 8)]
	public struct TileEffectKey : IKeyTypeDeserializable<TileEffectKey, tile_effect, key_tile_effect>, IKeyType<TileEffectKey>, IKeyType, IEquatable<TileEffectKey>
	{
		private short m_pos_x;

		private short m_pos_y;

		private int m_p_tile_effect;

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

		public int p_tile_effect
		{
			get
			{
				return 0;
			}
		}

		public TileEffectKey(short pos_x, short pos_y, int p_tile_effect)
		{
			m_pos_x = 0;
			m_pos_y = 0;
			m_p_tile_effect = 0;
		}

		public void DeserializeKey(key_tile_effect data)
		{
		}

		public void DeserializeKeyFromData(tile_effect data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(TileEffectKey left, TileEffectKey right)
		{
			return false;
		}

		public static bool operator !=(TileEffectKey left, TileEffectKey right)
		{
			return false;
		}

		public bool Equals(TileEffectKey other)
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
