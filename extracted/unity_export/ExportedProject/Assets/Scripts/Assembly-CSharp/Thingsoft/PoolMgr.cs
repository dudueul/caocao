using System;
using System.Collections.Generic;

namespace Thingsoft
{
	public static class PoolMgr
	{
		private static Dictionary<Type, IPool> pools;

		public static Pool<T> Get<T>()
		{
			return null;
		}

		public static void Clear()
		{
		}
	}
}
