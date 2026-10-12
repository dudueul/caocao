using System.Collections.Generic;

namespace Thingsoft.Pool
{
	public class UnitData
	{
		private static bool initPool;

		public List<global::UnitData> list;

		// C# has no syntax for parameterized property 'Item'.
		public global::UnitData get_Item(int idx)
		{
			return null;
		}

		public int Count
		{
			get
			{
				return 0;
			}
		}

		private UnitData()
		{
		}

		public void Clear()
		{
		}

		public int IndexOf(global::UnitData one)
		{
			return 0;
		}

		public bool Contains(global::UnitData one)
		{
			return false;
		}

		public void Add(global::UnitData one)
		{
		}

		public void AddRange(IEnumerable<global::UnitData> collection)
		{
		}

		public void AddRange(UnitData collection)
		{
		}

		public static UnitData Allocate()
		{
			return null;
		}

		public static void Restore(UnitData one)
		{
		}
	}
}
