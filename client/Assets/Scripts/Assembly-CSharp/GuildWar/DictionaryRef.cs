using System;
using System.Collections;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class DictionaryRef<TargetKey, TargetData> : IEnumerable, IEnumerable<TargetData> where TargetKey : struct, IKeyType where TargetData : class, IDataType, IKeyOwner<TargetKey>, IDataRefAble<TargetData>
	{
		public abstract Action<TargetKey, TargetData> onDataRefAdded { get; set; }

		public abstract Action<TargetKey, TargetData> onDataRefRemoved { get; set; }

		// C# has no syntax for parameterized property 'Item'.
		public abstract TargetData get_Item(TargetKey key);

		public abstract ICollection<TargetKey> Keys { get; }

		public abstract int Count { get; }

		IEnumerator IEnumerable.GetEnumerator()
		{
			return null;
		}

		public abstract IEnumerator<TargetData> GetEnumerator();

		public abstract bool ContainsKey(TargetKey key);

		public abstract bool TryGetValue(TargetKey key, out TargetData value);
	}
}
