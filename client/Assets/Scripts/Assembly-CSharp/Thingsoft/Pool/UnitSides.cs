using System.Collections.Generic;
using Caocao;

namespace Thingsoft.Pool
{
	public class UnitSides
	{
		public List<UnitSide> list;

		private static bool initPool;

		private UnitSides()
		{
		}

		public void Clear()
		{
		}

		public static UnitSides Allocate()
		{
			return null;
		}

		public static void Restore(UnitSides one)
		{
		}
	}
}
