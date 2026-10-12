using System;
using System.Collections.Generic;

namespace Thingsoft
{
	public class Pool<T> : IPool
	{
		private Func<T> createFunc;

		private Func<T, bool> resetFunc;

		private Func<T, bool> disposeFunc;

		private Stack<T> __restored;

		private int defaultCapacity;

		private List<T> m_used;

		private int accumulatedCount;

		private int restoreCount;

		private Stack<T> restored
		{
			get
			{
				return null;
			}
		}

		public void AddDebugInfo(T one)
		{
		}

		public void RemoveDebugInfo(T one)
		{
		}

		public void Stat(T one)
		{
		}

		public void ClearDebugInfo()
		{
		}

		public void SetConfig(int _defaultCapacity)
		{
		}

		public void SetCreator(Func<T> creatFunc)
		{
		}

		public void SetResetter(Func<T, bool> resetFunc)
		{
		}

		public void SetDisposer(Func<T, bool> disposFunc)
		{
		}

		public T New()
		{
			return default(T);
		}

		public void Delete(T one)
		{
		}

		public void Clear()
		{
		}

		public void Log()
		{
		}
	}
}
