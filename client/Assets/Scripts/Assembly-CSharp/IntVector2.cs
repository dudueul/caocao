using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using System.Xml;
using UnityEngine;

[Serializable]
[StructLayout((LayoutKind)0, Size = 8)]
public struct IntVector2 : IEventXmlSerializable, IEquatable<IntVector2>
{
	public class EqualityComparer : IEqualityComparer<IntVector2>
	{
		bool IEqualityComparer<IntVector2>.Equals(IntVector2 a, IntVector2 b)
		{
			return false;
		}

		int IEqualityComparer<IntVector2>.GetHashCode(IntVector2 obj)
		{
			return 0;
		}
	}

	public static EqualityComparer equalityComparer;

	public int x;

	public int y;

	public static readonly IntVector2 zero;

	public static readonly IntVector2 one;

	public int min
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int max
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int SqrMagnitude
	{
		get
		{
			return 0;
		}
	}

	public IntVector2(int x, int y)
	{
		this.x = 0;
		this.y = 0;
	}

	public IntVector2(IntVector2 value)
	{
		x = 0;
		y = 0;
	}

	public IntVector2(Vector2 value)
	{
		x = 0;
		y = 0;
	}

	public static IntVector2 operator -(IntVector2 a)
	{
		return default(IntVector2);
	}

	public static IntVector2 operator -(IntVector2 a, IntVector2 b)
	{
		return default(IntVector2);
	}

	public static IntVector2 operator -(IntVector2 a, int b)
	{
		return default(IntVector2);
	}

	public static IntVector2 operator +(IntVector2 a, IntVector2 b)
	{
		return default(IntVector2);
	}

	public static IntVector2 operator +(IntVector2 a, int b)
	{
		return default(IntVector2);
	}

	public static IntVector2 operator *(int d, IntVector2 a)
	{
		return default(IntVector2);
	}

	public static IntVector2 operator *(IntVector2 a, int d)
	{
		return default(IntVector2);
	}

	public static IntVector2 operator *(float d, IntVector2 a)
	{
		return default(IntVector2);
	}

	public static IntVector2 operator *(IntVector2 a, float d)
	{
		return default(IntVector2);
	}

	public static IntVector2 operator /(IntVector2 a, float d)
	{
		return default(IntVector2);
	}

	public static bool operator !=(IntVector2 lhs, IntVector2 rhs)
	{
		return false;
	}

	public static bool operator ==(IntVector2 lhs, IntVector2 rhs)
	{
		return false;
	}

	public bool Equals(IntVector2 other)
	{
		return false;
	}

	public override bool Equals(object obj)
	{
		return false;
	}

	public override int GetHashCode()
	{
		return 0;
	}

	public override string ToString()
	{
		return null;
	}

	public Vector2 ToVector()
	{
		return default(Vector2);
	}

	public void Swap()
	{
	}

	public static int ManhattanDistance(IntVector2 a, IntVector2 b)
	{
		return 0;
	}

	public int ManhattanDistance(IntVector2 a)
	{
		return 0;
	}

	public IntVector2 Normalized()
	{
		return default(IntVector2);
	}

	public void ImportFromXml(XmlNode xmlNode)
	{
	}
}
