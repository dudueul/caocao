using System;

namespace WebSocketSharp.Net
{
	[Serializable]
	public sealed class Cookie
	{
		private static char[] _reservedCharsForName;

		private static char[] _reservedCharsForValue;

		private string _comment;

		private Uri _commentUri;

		private bool _discard;

		private string _domain;

		private DateTime _expires;

		private bool _httpOnly;

		private string _name;

		private string _path;

		private string _port;

		private int[] _ports;

		private bool _secure;

		private DateTime _timestamp;

		private string _value;

		private int _version;

		internal bool ExactDomain { get; set; }

		internal int MaxAge
		{
			get
			{
				return 0;
			}
		}

		internal int[] Ports
		{
			get
			{
				return null;
			}
		}

		public string Comment
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public Uri CommentUri
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public bool Discard
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public string Domain
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public bool Expired
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public DateTime Expires
		{
			get
			{
				return default(DateTime);
			}
			set
			{
			}
		}

		public bool HttpOnly
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public string Name
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public string Path
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public string Port
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public bool Secure
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public DateTime TimeStamp
		{
			get
			{
				return default(DateTime);
			}
		}

		public string Value
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public int Version
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public Cookie()
		{
		}

		public Cookie(string name, string value)
		{
		}

		public Cookie(string name, string value, string path)
		{
		}

		public Cookie(string name, string value, string path, string domain)
		{
		}

		private static bool canSetName(string name, out string message)
		{
			message = null;
			return false;
		}

		private static bool canSetValue(string value, out string message)
		{
			message = null;
			return false;
		}

		private static int hash(int i, int j, int k, int l, int m)
		{
			return 0;
		}

		private string toResponseStringVersion0()
		{
			return null;
		}

		private string toResponseStringVersion1()
		{
			return null;
		}

		private static bool tryCreatePorts(string value, out int[] result, out string parseError)
		{
			result = null;
			parseError = null;
			return false;
		}

		internal string ToRequestString(Uri uri)
		{
			return null;
		}

		internal string ToResponseString()
		{
			return null;
		}

		public override bool Equals(object comparand)
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
	}
}
