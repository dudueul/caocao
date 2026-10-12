using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 12)]
	public struct BuildingHistoryKey : IKeyTypeDeserializable<BuildingHistoryKey, building_history, key_building_history>, IKeyType<BuildingHistoryKey>, IKeyType, IEquatable<BuildingHistoryKey>
	{
		private int m_p_place_id;

		private int m_history_id;

		private int m_history_key;

		public int p_place_id
		{
			get
			{
				return 0;
			}
		}

		public int history_id
		{
			get
			{
				return 0;
			}
		}

		public int history_key
		{
			get
			{
				return 0;
			}
		}

		public BuildingHistoryKey(int p_place_id, int history_id, int history_key)
		{
			m_p_place_id = 0;
			m_history_id = 0;
			m_history_key = 0;
		}

		public void DeserializeKey(key_building_history data)
		{
		}

		public void DeserializeKeyFromData(building_history data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(BuildingHistoryKey left, BuildingHistoryKey right)
		{
			return false;
		}

		public static bool operator !=(BuildingHistoryKey left, BuildingHistoryKey right)
		{
			return false;
		}

		public bool Equals(BuildingHistoryKey other)
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
