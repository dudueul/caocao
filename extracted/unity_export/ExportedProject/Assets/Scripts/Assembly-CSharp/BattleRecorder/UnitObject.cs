using UnityEngine;

namespace BattleRecorder
{
	public sealed class UnitObject : IWritable
	{
		private UnitData m_unitData;

		private uint m_unitIndex;

		public UnitData unitData
		{
			get
			{
				return null;
			}
		}

		public byte key
		{
			get
			{
				return 0;
			}
		}

		private UnitObject(uint unitIndex)
		{
		}

		private UnitObject(UnitData o)
		{
		}

		public void SetActive(bool value)
		{
		}

		public T GetComponent<T>() where T : Component
		{
			return null;
		}

		public T[] GetComponentsInChildren<T>() where T : Component
		{
			return null;
		}

		public static explicit operator UnitData(UnitObject o)
		{
			return null;
		}

		public static implicit operator UnitObject(UnitData o)
		{
			return null;
		}

		public static UnitObject Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
