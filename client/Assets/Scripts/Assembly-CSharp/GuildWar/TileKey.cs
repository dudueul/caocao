using System;
using System.Runtime.InteropServices;
using LitJson;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 4)]
	public struct TileKey : IKeyType<TileKey>, IKeyType, IEquatable<TileKey>
	{
		private short m_pos_x;

		private short m_pos_y;

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

		public TileKey(short pos_x, short pos_y)
		{
			m_pos_x = 0;
			m_pos_y = 0;
		}

		public TileKey(int pos_x, int pos_y)
		{
			m_pos_x = 0;
			m_pos_y = 0;
		}

		public void DeserializeKey(JsonData jsonData)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(TileKey left, TileKey right)
		{
			return false;
		}

		public static bool operator !=(TileKey left, TileKey right)
		{
			return false;
		}

		public bool Equals(TileKey other)
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
