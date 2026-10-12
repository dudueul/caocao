using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 8)]
	public struct BuildingPassiveKey : IKeyTypeDeserializable<BuildingPassiveKey, building_passive, key_building_passive>, IKeyType<BuildingPassiveKey>, IKeyType, IEquatable<BuildingPassiveKey>
	{
		private int m_p_place_id;

		private int m_passive_index;

		public int p_place_id
		{
			get
			{
				return 0;
			}
		}

		public int passive_index
		{
			get
			{
				return 0;
			}
		}

		public BuildingPassiveKey(int p_place_id, int passive_index)
		{
			m_p_place_id = 0;
			m_passive_index = 0;
		}

		public void DeserializeKey(key_building_passive data)
		{
		}

		public void DeserializeKeyFromData(building_passive data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(BuildingPassiveKey left, BuildingPassiveKey right)
		{
			return false;
		}

		public static bool operator !=(BuildingPassiveKey left, BuildingPassiveKey right)
		{
			return false;
		}

		public bool Equals(BuildingPassiveKey other)
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
