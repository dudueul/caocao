using System.Collections;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class DataTable<KeyType, DataType> : IEnumerable<KeyValuePair<KeyType, DataType>>, IEnumerable where KeyType : struct, IKeyType where DataType : class, IDataTableOwner
	{
		// C# has no syntax for parameterized property 'Item'.
		public abstract DataType get_Item(string stringKey);

		// C# has no syntax for parameterized property 'Item'.
		public abstract DataType get_Item(KeyType kType);

		public abstract int Count { get; }

		public abstract IEnumerator<KeyValuePair<KeyType, DataType>> GetEnumerator();

		IEnumerator IEnumerable.GetEnumerator()
		{
			return null;
		}

		public abstract DataType GetData(string stringKey);

		public abstract DataType GetData(KeyType kType);

		public abstract bool TryGetValue(string stringKey, out DataType value);

		public abstract bool TryGetValue(KeyType key, out DataType value);
	}
}
