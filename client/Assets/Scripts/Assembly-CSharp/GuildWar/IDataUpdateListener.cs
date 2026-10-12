using System;

namespace GuildWar
{
	public interface IDataUpdateListener<DataType> where DataType : class, IDataType
	{
		Action<DataType> onUpdate { get; set; }
	}
}
