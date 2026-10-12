namespace GuildWar
{
	public interface IDataType
	{
	}
	public interface IDataType<DataType> : IDataType, IDataUpdateListener<DataType> where DataType : class, IDataType
	{
	}
}
