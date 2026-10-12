using System.Collections.Generic;

namespace Thingsoft.Pool
{
	public class UserItems
	{
		public List<UserItem> list;

		private static bool initPool;

		private UserItems()
		{
		}

		public void Clear()
		{
		}

		public static UserItems Allocate()
		{
			return null;
		}

		public static void Restore(UserItems one)
		{
		}
	}
}
