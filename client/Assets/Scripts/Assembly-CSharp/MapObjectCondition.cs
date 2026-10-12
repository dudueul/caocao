using System.Collections.Generic;

public class MapObjectCondition
{
	public class StatusCondition
	{
		public readonly string value;

		public readonly int newStatusId;

		public StatusCondition(string val, int id)
		{
		}
	}

	public readonly string m_type;

	public readonly List<StatusCondition> changeStatus;

	public MapObjectCondition(string type, List<StatusCondition> statusConditions)
	{
	}
}
