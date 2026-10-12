using System;

namespace GuildWar
{
	public abstract class DataRef<TargetData> where TargetData : class, IDataType, IDataRefAble<TargetData>
	{
		public abstract TargetData data { get; }

		public abstract Action onDataRefUpdated { get; set; }

		public static implicit operator TargetData(DataRef<TargetData> data)
		{
			return null;
		}
	}
}
