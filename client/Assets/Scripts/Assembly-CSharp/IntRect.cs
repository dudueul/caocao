using System;
using System.Runtime.InteropServices;
using System.Xml;
using UnityEngine;

[Serializable]
[StructLayout((LayoutKind)0, Size = 16)]
public struct IntRect : IEventXmlSerializable, IEquatable<IntRect>
{
	public int x;

	public int y;

	public int width;

	public int height;

	public IntVector2 position
	{
		get
		{
			return default(IntVector2);
		}
		set
		{
		}
	}

	public IntVector2 size
	{
		get
		{
			return default(IntVector2);
		}
		set
		{
		}
	}

	public IntRect(int x, int y, int width, int height)
	{
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
	}

	public IntRect(IntRect value)
	{
		x = 0;
		y = 0;
		width = 0;
		height = 0;
	}

	public IntRect(Rect value)
	{
		x = 0;
		y = 0;
		width = 0;
		height = 0;
	}

	public static bool operator !=(IntRect lhs, IntRect rhs)
	{
		return false;
	}

	public static bool operator ==(IntRect lhs, IntRect rhs)
	{
		return false;
	}

	public bool Equals(IntRect other)
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

	public void ImportFromXml(XmlNode xmlNode)
	{
	}
}
