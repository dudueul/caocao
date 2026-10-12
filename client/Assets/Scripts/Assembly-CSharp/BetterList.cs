using System.Collections.Generic;
using System.Diagnostics;

public class BetterList<T>
{
	public delegate int CompareFunc(T left, T right);

	public T[] buffer;

	public int size;

	// C# has no syntax for parameterized property 'Item'.
	// Its 'property:' attributes below are ignored by the compiler (CS0657).
	[property: DebuggerHidden]
	public T get_Item(int i)
	{
		return default(T);
	}

	public void set_Item(int i, T value)
	{
	}

	[DebuggerHidden]
	[DebuggerHidden]
	[DebuggerStepThrough]
	public IEnumerator<T> GetEnumerator()
	{
		return null;
	}

	private void AllocateMore()
	{
	}

	public void CopyFrom(BetterList<T> src)
	{
	}

	private void Trim()
	{
	}

	public void Clear()
	{
	}

	public void Release()
	{
	}

	public void Add(T item)
	{
	}

	public void Insert(int index, T item)
	{
	}

	public bool Contains(T item)
	{
		return false;
	}

	public int IndexOf(T item)
	{
		return 0;
	}

	public bool Remove(T item)
	{
		return false;
	}

	public void RemoveAt(int index)
	{
	}

	public T Pop()
	{
		return default(T);
	}

	public T[] ToArray()
	{
		return null;
	}

	[DebuggerHidden]
	[DebuggerStepThrough]
	public void Sort(CompareFunc comparer)
	{
	}
}
