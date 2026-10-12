using System;
using System.Runtime.InteropServices;
using GuildWarFlatBuffer;

namespace GuildWar
{
	[StructLayout((LayoutKind)0, Size = 4)]
	public struct CorpsKey : IKeyTypeDeserializable<CorpsKey, corps, key_corps>, IKeyType<CorpsKey>, IKeyType, IEquatable<CorpsKey>
	{
		private int m_corps_id;

		public int corps_id
		{
			get
			{
				return 0;
			}
		}

		public CorpsKey(int corps_id)
		{
			m_corps_id = 0;
		}

		public void DeserializeKey(key_corps data)
		{
		}

		public void DeserializeKeyFromData(corps data)
		{
		}

		public string GetStringKey()
		{
			return null;
		}

		public void SetValueWithStringKey(string stringKey)
		{
		}

		public static bool operator ==(CorpsKey left, CorpsKey right)
		{
			return false;
		}

		public static bool operator !=(CorpsKey left, CorpsKey right)
		{
			return false;
		}

		public bool Equals(CorpsKey other)
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
