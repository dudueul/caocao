using System;
using System.Collections;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class ListRef<TargetData> : IEnumerable, IEnumerable<TargetData> where TargetData : class, IDataType, IDataRefAble<TargetData>
	{
		public abstract Action<TargetData> onDataRefAdded { get; set; }

		public abstract Action<TargetData> onDataRefRemoved { get; set; }

		// C# has no syntax for parameterized property 'Item'.
		public abstract TargetData get_Item(int index);

		public abstract int Count { get; }

		IEnumerator IEnumerable.GetEnumerator()
		{
			return null;
		}

		public abstract IEnumerator<TargetData> GetEnumerator();

		public abstract bool Contains(TargetData targetData);
	}
}
