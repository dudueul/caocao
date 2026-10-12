using System.Runtime.InteropServices;
using System.Text;

namespace nAI
{
	[StructLayout((LayoutKind)0, Size = 16)]
	public struct logFloat
	{
		private StringBuilder m_sb;

		public float m_value;

		public StringBuilder sb
		{
			get
			{
				return null;
			}
		}

		public bool IsNull
		{
			get
			{
				return false;
			}
		}

		public string log
		{
			get
			{
				return null;
			}
		}

		public float value
		{
			get
			{
				return 0f;
			}
			set
			{
			}
		}

		public logFloat(float value = 0f)
		{
			m_sb = null;
			m_value = 0f;
		}

		public static logFloat operator +(logFloat a, float b)
		{
			return default(logFloat);
		}

		public static float operator -(logFloat a, float b)
		{
			return 0f;
		}

		public static implicit operator logFloat(float b)
		{
			return default(logFloat);
		}

		public static implicit operator float(logFloat b)
		{
			return 0f;
		}

		internal int CompareTo(logFloat floatLog)
		{
			return 0;
		}
	}
}
