namespace GuildWar
{
	public interface IDataRefInternal
	{
		void ResetAllData();
	}
	public interface IDataRefInternal<TargetData> : IDataRefInternal where TargetData : class, IDataType, IDataRefAble<TargetData>
	{
		TargetData GetData();
	}
}
