using System.IO;

namespace TSI.SimpleJSON
{
	public class JSONData : JSONNode
	{
		public enum JSONDataType
		{
			NONE = 0,
			STRING = 1,
			FLOAT = 2,
			DOUBLE = 3,
			BOOL = 4,
			INT = 5
		}

		private JSONDataType _type;

		private string m_Data;

		public override string Value
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public JSONData(string aData)
		{
		}

		public JSONData(float aData)
		{
		}

		public JSONData(double aData)
		{
		}

		public JSONData(bool aData)
		{
		}

		public JSONData(int aData)
		{
		}

		private JSONDataType checkType(string value)
		{
			return JSONDataType.NONE;
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
