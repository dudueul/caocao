using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;

namespace MAssist.SimpleJSON
{
	public class JSONClass : JSONNode, IEnumerable
	{
		private Dictionary<string, JSONNode> m_Dict;

		// C# has no syntax for parameterized property 'Item'.
		public override JSONNode get_Item(string aKey)
		{
			return null;
		}

		public override void set_Item(string aKey, JSONNode value)
		{
		}

		// C# has no syntax for parameterized property 'Item'.
		public override JSONNode get_Item(int aIndex)
		{
			return null;
		}

		public override void set_Item(int aIndex, JSONNode value)
		{
		}

		public override int Count
		{
			get
			{
				return 0;
			}
		}

		public override IEnumerable<JSONNode> Childs
		{
			get
			{
				return null;
			}
		}

		public override void Add(string aKey, JSONNode aItem)
		{
		}

		public override JSONNode Remove(string aKey)
		{
			return null;
		}

		public override JSONNode Remove(int aIndex)
		{
			return null;
		}

		public override JSONNode Remove(JSONNode aNode)
		{
			return null;
		}

		[DebuggerHidden]
		public IEnumerator GetEnumerator()
		{
			return null;
		}

		public override string ToString()
		{
			return null;
		}

		public override string ToString(string aPrefix)
		{
			return null;
		}

		public override void Serialize(BinaryWriter aWriter)
		{
		}
	}
}
