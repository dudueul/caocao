namespace GuildWar
{
	public interface IDataRefAble<TargetData> where TargetData : class, IDataType, IDataRefAble<TargetData>
	{
		void AddDataRef(IDataRefInternal<TargetData> dataRef);

		void RemoveDataRef(IDataRefInternal<TargetData> dataRef);

		void ResetDataRefsAll();
	}
}
