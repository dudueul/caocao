using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 4)]
	public struct BuildingKey : IKeyTypeDeserializable<BuildingKey, building, key_building>, IKeyType<BuildingKey>, IKeyType, IEquatable<BuildingKey>
	{
		private int m_p_place_id;

		public int p_place_id
		{
			get
			{
				return 0;
			}
		}

		public BuildingKey(int p_place_id)
		{
			m_p_place_id = 0;
		}

		public void DeserializeKey(key_building data)
		{
		}

		public void DeserializeKeyFromData(building data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(BuildingKey left, BuildingKey right)
		{
			return false;
		}

		public static bool operator !=(BuildingKey left, BuildingKey right)
		{
			return false;
		}

		public bool Equals(BuildingKey other)
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
