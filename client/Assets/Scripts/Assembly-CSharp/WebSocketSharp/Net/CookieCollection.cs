using System;
using System.Collections;
using System.Collections.Generic;

namespace WebSocketSharp.Net
{
	[Serializable]
	public class CookieCollection : ICollection, IEnumerable
	{
		private List<Cookie> _list;

		private object _sync;

		internal IList<Cookie> List
		{
			get
			{
				return null;
			}
		}

		internal IEnumerable<Cookie> Sorted
		{
			get
			{
				return null;
			}
		}

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

		// C# has no syntax for parameterized property 'Item'.
		public Cookie get_Item(int index)
		{
			return null;
		}

		// C# has no syntax for parameterized property 'Item'.
		public Cookie get_Item(string name)
		{
			return null;
		}

		public object SyncRoot
		{
			get
			{
				return null;
			}
		}

		private static int compareCookieWithinSort(Cookie x, Cookie y)
		{
			return 0;
		}

		private static int compareCookieWithinSorted(Cookie x, Cookie y)
		{
			return 0;
		}

		private static CookieCollection parseRequest(string value)
		{
			return null;
		}

		private static CookieCollection parseResponse(string value)
		{
			return null;
		}

		private int searchCookie(Cookie cookie)
		{
			return 0;
		}

		private static string[] splitCookieHeaderValue(string value)
		{
			return null;
		}

		internal static CookieCollection Parse(string value, bool response)
		{
			return null;
		}

		internal void SetOrRemove(Cookie cookie)
		{
		}

		internal void SetOrRemove(CookieCollection cookies)
		{
		}

		internal void Sort()
		{
		}

		public void Add(Cookie cookie)
		{
		}

		public void Add(CookieCollection cookies)
		{
		}

		public void CopyTo(Array array, int index)
		{
		}

		public void CopyTo(Cookie[] array, int index)
		{
		}

		public IEnumerator GetEnumerator()
		{
			return null;
		}
	}
}
