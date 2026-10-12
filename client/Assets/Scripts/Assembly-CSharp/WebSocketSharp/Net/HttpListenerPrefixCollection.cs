using System;
using System.Collections;
using System.Collections.Generic;

namespace WebSocketSharp.Net
{
	public class HttpListenerPrefixCollection : ICollection<string>, IEnumerable<string>, IEnumerable
	{
		private HttpListener _listener;

		private List<string> _prefixes;

		public int Count
		{
			get
			{
				return 0;
			}
		}

		public bool IsReadOnly
		{
			get
			{
				return false;
			}
		}

		public bool IsSynchronized
		{
			get
			{
				return false;
			}
		}

		private HttpListenerPrefixCollection()
		{
		}

		internal HttpListenerPrefixCollection(HttpListener listener)
		{
		}

		public void Add(string uriPrefix)
		{
		}

		public void Clear()
		{
		}

		public bool Contains(string uriPrefix)
		{
			return false;
		}

		public void CopyTo(Array array, int offset)
		{
		}

		public void CopyTo(string[] array, int offset)
		{
		}

		public IEnumerator<string> GetEnumerator()
		{
			return null;
		}

		public bool Remove(string uriPrefix)
		{
			return false;
		}

		IEnumerator IEnumerable.GetEnumerator()
		{
			return null;
		}
	}
}
