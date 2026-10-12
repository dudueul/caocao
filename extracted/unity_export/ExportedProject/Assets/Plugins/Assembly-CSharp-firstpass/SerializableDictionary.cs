using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[Serializable]
public class SerializableDictionary<K, V> : IEnumerable<KeyValuePair<K, V>>, IEnumerable
{
	private class Enumerator : IEnumerator<KeyValuePair<K, V>>, IEnumerator, IDisposable
	{
		private SerializableDictionary<K, V> Dictionary;

		private int current;

		object IEnumerator.Current
		{
			get
			{
				return null;
			}
		}

		public KeyValuePair<K, V> Current
		{
			get
			{
				return default(KeyValuePair<K, V>);
			}
		}

		public Enumerator(SerializableDictionary<K, V> dictionary)
		{
		}

		public void Dispose()
		{
		}

		public bool MoveNext()
		{
			return false;
		}

		public void Reset()
		{
		}
	}

	private Dictionary<K, int> Dictionary;

	[SerializeField]
	private List<K> KeysList;

	[SerializeField]
	private List<V> ValuesList;

	[NonSerialized]
	private bool dictionaryRestored;

	// C# has no syntax for parameterized property 'Item'.
	public V get_Item(K key)
	{
		return default(V);
	}

	public void set_Item(K key, V value)
	{
	}

	public int Count
	{
		get
		{
			return 0;
		}
	}

	public V Get(K key, V defaultValue)
	{
		return default(V);
	}

	public bool TryGetValue(K key, out V value)
	{
		value = default(V);
		return false;
	}

	public bool Remove(K key)
	{
		return false;
	}

	public void RemoveAt(int index)
	{
	}

	public KeyValuePair<K, V> GetAt(int index)
	{
		return default(KeyValuePair<K, V>);
	}

	public V GetValueAt(int index)
	{
		return default(V);
	}

	public bool ContainsKey(K key)
	{
		return false;
	}

	public void Clear()
	{
	}

	private void RestoreDictionary()
	{
	}

	public IEnumerator<KeyValuePair<K, V>> GetEnumerator()
	{
		return null;
	}

	IEnumerator IEnumerable.GetEnumerator()
	{
		return null;
	}
}
