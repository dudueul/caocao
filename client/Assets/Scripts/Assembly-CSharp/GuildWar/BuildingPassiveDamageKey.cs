using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 8)]
	public struct BuildingPassiveDamageKey : IKeyTypeDeserializable<BuildingPassiveDamageKey, building_passive_damage, key_building_passive_damage>, IKeyType<BuildingPassiveDamageKey>, IKeyType, IEquatable<BuildingPassiveDamageKey>
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

		public BuildingPassiveDamageKey(int p_place_id, int passive_index)
		{
			m_p_place_id = 0;
			m_passive_index = 0;
		}

		public void DeserializeKey(key_building_passive_damage data)
		{
		}

		public void DeserializeKeyFromData(building_passive_damage data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(BuildingPassiveDamageKey left, BuildingPassiveDamageKey right)
		{
			return false;
		}

		public static bool operator !=(BuildingPassiveDamageKey left, BuildingPassiveDamageKey right)
		{
			return false;
		}

		public bool Equals(BuildingPassiveDamageKey other)
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
