using System;
using LitJson;

namespace CustomEvent
{
	public class FilterClasses
	{
		public class UserFilter : IEventFilter
		{
			public int rankMin { get; private set; }

			public int rankMax { get; private set; }

			public DateTime createDate { get; private set; }

			public int platformCode { get; private set; }

			public void Parse(JsonData jsonData)
			{
			}

			public bool Check(params object[] param)
			{
				return false;
			}
		}
	}
}
