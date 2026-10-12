using System;

namespace Eppy
{
	public static class Tuple
	{
		public static Tuple<T1, T2> Create<T1, T2>(T1 item1, T2 second)
		{
			return null;
		}

		public static Tuple<T1, T2, T3> Create<T1, T2, T3>(T1 item1, T2 second, T3 third)
		{
			return null;
		}

		public static Tuple<T1, T2, T3, T4> Create<T1, T2, T3, T4>(T1 item1, T2 second, T3 third, T4 fourth)
		{
			return null;
		}

		public static void Unpack<T1, T2>(this Tuple<T1, T2> tuple, out T1 ref1, out T2 ref2)
		{
			ref1 = default(T1);
			ref2 = default(T2);
		}

		public static void Unpack<T1, T2, T3>(this Tuple<T1, T2, T3> tuple, out T1 ref1, out T2 ref2, T3 ref3)
		{
			ref1 = default(T1);
			ref2 = default(T2);
		}

		public static void Unpack<T1, T2, T3, T4>(this Tuple<T1, T2, T3, T4> tuple, out T1 ref1, out T2 ref2, T3 ref3, T4 ref4)
		{
			ref1 = default(T1);
			ref2 = default(T2);
		}
	}
	public sealed class Tuple<T1, T2>
	{
		private readonly T1 item1;

		private readonly T2 item2;

		public T1 Item1
		{
			get
			{
				return default(T1);
			}
		}

		public T2 Item2
		{
			get
			{
				return default(T2);
			}
		}

		public Tuple(T1 item1, T2 item2)
		{
		}

		public override string ToString()
		{
			return null;
		}

		public override int GetHashCode()
		{
			return 0;
		}

		public override bool Equals(object o)
		{
			return false;
		}

		public bool Equals(Tuple<T1, T2> other)
		{
			return false;
		}

		public static bool operator ==(Tuple<T1, T2> a, Tuple<T1, T2> b)
		{
			return false;
		}

		public static bool operator !=(Tuple<T1, T2> a, Tuple<T1, T2> b)
		{
			return false;
		}

		public void Unpack(Action<T1, T2> unpackerDelegate)
		{
		}
	}
	public sealed class Tuple<T1, T2, T3>
	{
		private readonly T1 item1;

		private readonly T2 item2;

		private readonly T3 item3;

		public T1 Item1
		{
			get
			{
				return default(T1);
			}
		}

		public T2 Item2
		{
			get
			{
				return default(T2);
			}
		}

		public T3 Item3
		{
			get
			{
				return default(T3);
			}
		}

		public Tuple(T1 item1, T2 item2, T3 item3)
		{
		}

		public override int GetHashCode()
		{
			return 0;
		}

		public override bool Equals(object o)
		{
			return false;
		}

		public static bool operator ==(Tuple<T1, T2, T3> a, Tuple<T1, T2, T3> b)
		{
			return false;
		}

		public static bool operator !=(Tuple<T1, T2, T3> a, Tuple<T1, T2, T3> b)
		{
			return false;
		}

		public void Unpack(Action<T1, T2, T3> unpackerDelegate)
		{
		}
	}
	public sealed class Tuple<T1, T2, T3, T4>
	{
		private readonly T1 item1;

		private readonly T2 item2;

		private readonly T3 item3;

		private readonly T4 item4;

		public T1 Item1
		{
			get
			{
				return default(T1);
			}
		}

		public T2 Item2
		{
			get
			{
				return default(T2);
			}
		}

		public T3 Item3
		{
			get
			{
				return default(T3);
			}
		}

		public T4 Item4
		{
			get
			{
				return default(T4);
			}
		}

		public Tuple(T1 item1, T2 item2, T3 item3, T4 item4)
		{
		}

		public override int GetHashCode()
		{
			return 0;
		}

		public override bool Equals(object o)
		{
			return false;
		}

		public static bool operator ==(Tuple<T1, T2, T3, T4> a, Tuple<T1, T2, T3, T4> b)
		{
			return false;
		}

		public static bool operator !=(Tuple<T1, T2, T3, T4> a, Tuple<T1, T2, T3, T4> b)
		{
			return false;
		}

		public void Unpack(Action<T1, T2, T3, T4> unpackerDelegate)
		{
		}
	}
}
