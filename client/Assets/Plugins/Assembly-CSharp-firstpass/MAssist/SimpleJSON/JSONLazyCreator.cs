namespace MAssist.SimpleJSON
{
	internal class JSONLazyCreator : JSONNode
	{
		private JSONNode m_Node;

		private string m_Key;

		// C# has no syntax for parameterized property 'Item'.
		public override JSONNode get_Item(int aIndex)
		{
			return null;
		}

		public override void set_Item(int aIndex, JSONNode value)
		{
		}

		// C# has no syntax for parameterized property 'Item'.
		public override JSONNode get_Item(string aKey)
		{
			return null;
		}

		public override void set_Item(string aKey, JSONNode value)
		{
		}

		public override int AsInt
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public override float AsFloat
		{
			get
			{
				return 0f;
			}
			set
			{
			}
		}

		public override double AsDouble
		{
			get
			{
				return 0.0;
			}
			set
			{
			}
		}

		public override bool AsBool
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public override JSONArray AsArray
		{
			get
			{
				return null;
			}
		}

		public override JSONClass AsObject
		{
			get
			{
				return null;
			}
		}

		public JSONLazyCreator(JSONNode aNode)
		{
		}

		public JSONLazyCreator(JSONNode aNode, string aKey)
		{
		}

		private void Set(JSONNode aVal)
		{
		}

		public override void Add(JSONNode aItem)
		{
		}

		public override void Add(string aKey, JSONNode aItem)
		{
		}

		public static bool operator ==(JSONLazyCreator a, object b)
		{
			return false;
		}

		public static bool operator !=(JSONLazyCreator a, object b)
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

		public override string ToString(string aPrefix)
		{
			return null;
		}
	}
}
