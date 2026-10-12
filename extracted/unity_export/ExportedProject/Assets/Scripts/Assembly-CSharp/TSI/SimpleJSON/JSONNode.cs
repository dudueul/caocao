using System.Collections.Generic;
using System.IO;

namespace TSI.SimpleJSON
{
	public class JSONNode
	{
		// C# has no syntax for parameterized property 'Item'.
		public virtual JSONNode get_Item(int aIndex)
		{
			return null;
		}

		public virtual void set_Item(int aIndex, JSONNode value)
		{
		}

		// C# has no syntax for parameterized property 'Item'.
		public virtual JSONNode get_Item(string aKey)
		{
			return null;
		}

		public virtual void set_Item(string aKey, JSONNode value)
		{
		}

		public virtual string Value
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public virtual int Count
		{
			get
			{
				return 0;
			}
		}

		public virtual IEnumerable<JSONNode> Childs
		{
			get
			{
				return null;
			}
		}

		public IEnumerable<JSONNode> DeepChilds
		{
			get
			{
				return null;
			}
		}

		public virtual int AsInt
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public virtual float AsFloat
		{
			get
			{
				return 0f;
			}
			set
			{
			}
		}

		public virtual double AsDouble
		{
			get
			{
				return 0.0;
			}
			set
			{
			}
		}

		public virtual bool AsBool
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public virtual JSONArray AsArray
		{
			get
			{
				return null;
			}
		}

		public virtual JSONClass AsObject
		{
			get
			{
				return null;
			}
		}

		public virtual void Add(string aKey, JSONNode aItem)
		{
		}

		public virtual void Add(JSONNode aItem)
		{
		}

		public virtual JSONNode Remove(string aKey)
		{
			return null;
		}

		public virtual JSONNode Remove(int aIndex)
		{
			return null;
		}

		public virtual JSONNode Remove(JSONNode aNode)
		{
			return null;
		}

		public override string ToString()
		{
			return null;
		}

		public virtual string ToString(string aPrefix)
		{
			return null;
		}

		public static implicit operator JSONNode(string s)
		{
			return null;
		}

		public static implicit operator JSONNode(int i)
		{
			return null;
		}

		public static implicit operator JSONNode(float f)
		{
			return null;
		}

		public static implicit operator JSONNode(double d)
		{
			return null;
		}

		public static implicit operator JSONNode(bool b)
		{
			return null;
		}

		public static implicit operator string(JSONNode d)
		{
			return null;
		}

		public static bool operator ==(JSONNode a, object b)
		{
			return false;
		}

		public static bool operator !=(JSONNode a, object b)
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

		internal static string Escape(string aText)
		{
			return null;
		}

		public static JSONNode Parse(string aJSON)
		{
			return null;
		}

		public virtual void Serialize(BinaryWriter aWriter)
		{
		}

		public void SaveToStream(Stream aData)
		{
		}

		public void SaveToCompressedStream(Stream aData)
		{
		}

		public void SaveToCompressedFile(string aFileName)
		{
		}

		public string SaveToCompressedBase64()
		{
			return null;
		}

		public void SaveToFile(string aFileName)
		{
		}

		public string SaveToBase64()
		{
			return null;
		}

		public static JSONNode Deserialize(BinaryReader aReader)
		{
			return null;
		}

		public static JSONNode LoadFromCompressedFile(string aFileName)
		{
			return null;
		}

		public static JSONNode LoadFromCompressedStream(Stream aData)
		{
			return null;
		}

		public static JSONNode LoadFromCompressedBase64(string aBase64)
		{
			return null;
		}

		public static JSONNode LoadFromStream(Stream aData)
		{
			return null;
		}

		public static JSONNode LoadFromFile(string aFileName)
		{
			return null;
		}

		public static JSONNode LoadFromBase64(string aBase64)
		{
			return null;
		}
	}
}
