using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 4)]
	public struct CorpsInformationKey : IKeyTypeDeserializable<CorpsInformationKey, corps_information, key_corps_information>, IKeyType<CorpsInformationKey>, IKeyType, IEquatable<CorpsInformationKey>
	{
		private int m_corps_id;

		public int corps_id
		{
			get
			{
				return 0;
			}
		}

		public CorpsInformationKey(int corps_id)
		{
			m_corps_id = 0;
		}

		public void DeserializeKey(key_corps_information data)
		{
		}

		public void DeserializeKeyFromData(corps_information data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(CorpsInformationKey left, CorpsInformationKey right)
		{
			return false;
		}

		public static bool operator !=(CorpsInformationKey left, CorpsInformationKey right)
		{
			return false;
		}

		public bool Equals(CorpsInformationKey other)
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
