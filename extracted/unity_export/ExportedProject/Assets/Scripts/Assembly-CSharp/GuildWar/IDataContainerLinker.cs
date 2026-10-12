namespace GuildWar
{
	public interface IDataContainerLinker
	{
		void MakeDataRefEachOther<TargetData1, TargetData2>(DataRef<TargetData2> ref1, DataRef<TargetData1> ref2) where TargetData1 : class, IDataType, IDataRefAble<TargetData1> where TargetData2 : class, IDataType, IDataRefAble<TargetData2>;

		void MakeDataList<TargetData1, TargetData2>(ListRef<TargetData2> ref1, DataRef<TargetData1> ref2) where TargetData1 : class, IDataType, IDataRefAble<TargetData1> where TargetData2 : class, IDataType, IDataRefAble<TargetData2>;

		void SkipDataRef<TargetData>(DataRef<TargetData> target) where TargetData : class, IDataType, IDataRefAble<TargetData>;

		void SkipDataDictionary<TargetKey, TargetData>(DictionaryRef<TargetKey, TargetData> target) where TargetKey : struct, IKeyType<TargetKey> where TargetData : class, IDataType, IKeyOwner<TargetKey>, IDataRefAble<TargetData>;

		void SkipDataList<TargetData>(ListRef<TargetData> target) where TargetData : class, IDataType, IDataRefAble<TargetData>;
	}
	public interface IDataContainerLinker<KeyType> : IDataContainerLinker where KeyType : struct, IKeyType
	{
		void MakeDataDictionary<TargetData1, TargetData2>(DictionaryRef<KeyType, TargetData2> ref1, DataRef<TargetData1> ref2) where TargetData1 : class, IDataType, IDataRefAble<TargetData1> where TargetData2 : class, IDataType, IKeyOwner<KeyType>, IDataRefAble<TargetData2>;
	}
}
