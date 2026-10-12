using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 8)]
	public struct BuildingCustomFunctionKey : IKeyTypeDeserializable<BuildingCustomFunctionKey, building_custom_function, key_building_custom_function>, IKeyType<BuildingCustomFunctionKey>, IKeyType, IEquatable<BuildingCustomFunctionKey>
	{
		private int m_p_place_id;

		private short m_custom_action_type;

		public int p_place_id
		{
			get
			{
				return 0;
			}
		}

		public short custom_action_type
		{
			get
			{
				return 0;
			}
		}

		public BuildingCustomFunctionKey(int p_place_id, short custom_action_type)
		{
			m_p_place_id = 0;
			m_custom_action_type = 0;
		}

		public void DeserializeKey(key_building_custom_function data)
		{
		}

		public void DeserializeKeyFromData(building_custom_function data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(BuildingCustomFunctionKey left, BuildingCustomFunctionKey right)
		{
			return false;
		}

		public static bool operator !=(BuildingCustomFunctionKey left, BuildingCustomFunctionKey right)
		{
			return false;
		}

		public bool Equals(BuildingCustomFunctionKey other)
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
