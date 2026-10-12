namespace GuildWar
{
	public interface IDataContainerCreator
	{
		void CreateData<TargetData>(out DataRef<TargetData> data) where TargetData : class, IDataType, IDataRefAble<TargetData>;

		DataRef<TargetData> CreateData<TargetData>() where TargetData : class, IDataType, IDataRefAble<TargetData>;

		void CreateDictionary<TargetKey, TargetData>(out DictionaryRef<TargetKey, TargetData> dictionary) where TargetKey : struct, IKeyType<TargetKey> where TargetData : class, IDataType, IKeyOwner<TargetKey>, IDataRefAble<TargetData>;

		DictionaryRef<TargetKey, TargetData> CreateDictionary<TargetKey, TargetData>() where TargetKey : struct, IKeyType<TargetKey> where TargetData : class, IDataType, IKeyOwner<TargetKey>, IDataRefAble<TargetData>;

		void CreateList<TargetData>(out ListRef<TargetData> list) where TargetData : class, IDataType, IDataRefAble<TargetData>;

		ListRef<TargetData> CreateList<TargetData>() where TargetData : class, IDataType, IDataRefAble<TargetData>;
	}
}
