using System;
using System.Collections.Generic;

internal class DescendComparer<T> : IComparer<T> where T : IComparable<T>
{
	public int Compare(T x, T y)
	{
		return 0;
	}
}
