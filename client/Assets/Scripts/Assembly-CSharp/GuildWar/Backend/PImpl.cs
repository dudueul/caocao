using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Runtime.InteropServices;
using CodeStage.AntiCheat.ObscuredTypes;
using FlatBuffers;
using GuildWarFlatBuffer;
using LitJson;
using SocketIO;
using UnityEngine;

namespace GuildWar.Backend
{
	internal static class PImpl
	{
		// [RE] rebuild-only: public login hook (original: Manager subscribed the
		// session events internally; rebuild boot (SessionBoot) needs access).
		public static event Action<string> OnAnySessionLogined;

		public class CameraDataImpl : CameraData, IDataTableOwner, IClientDataOwnerImpl, IDataTypeImpl<CameraData>, IDataType<CameraData>, IDataType, IDataUpdateListener<CameraData>
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<CameraData> m_onUpdate;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<CameraData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}
		}

		public class ClientVisionDataImpl : ClientVisionData, IDataTableOwner, IClientDataOwnerImpl, IDataTypeImpl<ClientVisionData>, IDataType<ClientVisionData>, IDataType, IDataUpdateListener<ClientVisionData>
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<ClientVisionData> m_onUpdate;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<ClientVisionData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}
		}

		public class TileCandidatesDataImpl : TileCandidatesData, IDataTableOwner, IClientDataOwnerImpl, IDataTypeImpl<TileCandidatesData>, IDataType<TileCandidatesData>, IDataType, IDataUpdateListener<TileCandidatesData>
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<TileCandidatesData> m_onUpdate;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<TileCandidatesData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}
		}

		public class TileDataImpl : TileData, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataTypeImpl<TileData>, IDataTableOwner, IDataType<TileData>, IDataType, IDataUpdateListener<TileData>
		{
			private int m_p_tile_id;

			private Dictionary<int, List<TileKey>> m_neighborsTiles;

			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<TileData> m_onUpdate;

			private TileKey m_key;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<TileData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override short pos_x
			{
				get
				{
					return 0;
				}
			}

			public override short pos_y
			{
				get
				{
					return 0;
				}
			}

			public override int p_tile_id
			{
				get
				{
					return 0;
				}
			}

			public override Dictionary<int, List<TileKey>> neighborsTiles
			{
				get
				{
					return null;
				}
			}

			public TileDataImpl(short pos_x, short pos_y, int p_tile_id)
			{
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override TileKey GetKey()
			{
				return default(TileKey);
			}
		}

		public class TileDataTableImpl : DataTable<TileKey, TileData>
		{
			private Dictionary<TileKey, TileData> m_key2Data;

			private int m_mapSizeX;

			private int m_mapSizeY;

			public int mapSizeX
			{
				get
				{
					return 0;
				}
			}

			public int mapSizeY
			{
				get
				{
					return 0;
				}
			}

			// C# has no syntax for parameterized property 'Item'.
			public override TileData get_Item(string stringKey)
			{
				return null;
			}

			// C# has no syntax for parameterized property 'Item'.
			public override TileData get_Item(TileKey kType)
			{
				return null;
			}

			public override int Count
			{
				get
				{
					return 0;
				}
			}

			public TileDataTableImpl(DeserializeContext context, DataTables dataTables, string GuildWarMapId)
			{
			}

			public override IEnumerator<KeyValuePair<TileKey, TileData>> GetEnumerator()
			{
				return null;
			}

			public static string GetStringKey(TileKey keyType)
			{
				return null;
			}

			public static TileKey GetKeyWithStringKey(string stringKey)
			{
				return default(TileKey);
			}

			private static void oddr_to_cube(int posX, int posY, out int x, out int y, out int z)
			{
				x = default(int);
				y = default(int);
				z = default(int);
			}

			private static void cube_to_oddr(int posX, int posY, int posZ, out int x, out int y)
			{
				x = default(int);
				y = default(int);
			}

			private static void CubeNeighbor(ref int x, ref int y, ref int z, HexDirection direction)
			{
			}

			private static void InitTileNeighborWithRange(Dictionary<TileKey, TileDataImpl> tiles, TileData tileData, List<TileKey> neighbors, int range)
			{
			}

			private static void InitTileNeighbors(Dictionary<TileKey, TileDataImpl> tiles, TileData target)
			{
			}

			~TileDataTableImpl()
			{
			}

			public override TileData GetData(string stringKey)
			{
				return null;
			}

			public override TileData GetData(TileKey kType)
			{
				return null;
			}

			public override bool TryGetValue(string stringKey, out TileData value)
			{
				value = null;
				return false;
			}

			public override bool TryGetValue(TileKey key, out TileData value)
			{
				value = null;
				return false;
			}

			public void Clear()
			{
			}
		}

		private class ManagerImpl : Manager
		{
			private int m_worldId;

			private int m_clanShardIndex;

			private int m_guildWarShardIndex;

			private string m_map;

			private WorldMetaDataImpl m_worldMetaData;

			private DataTablesImpl m_dataTable;

			private Dictionary<int, byte[]> m_dataChunks;

			private Dictionary<ConnectionInfoImpl, SessionImpl> m_sessions;

			private bool m_doingGetWorldMetaData;

			private bool m_init;

			private string m_emptyUuid;

			private float m_timerGetWorldData;

			private bool m_doingGetWorldData;

			private bool m_firstWorldDataInit;

			private int nextRequestIdx;

			public override int GetWorldID()
			{
				return 0;
			}

			public override int GetClanShardIndex()
			{
				return 0;
			}

			public override int GetGuildWarShardIndex()
			{
				return 0;
			}

			public override string GetMap()
			{
				return null;
			}

			public override DataTables GetDataTable()
			{
				return null;
			}

			public override WorldData GetWorldData()
			{
				return null;
			}

			private WorldMetaDataImpl GetWorldMetaDataWithWorldID(int clanDbShard, int world_id)
			{
				return null;
			}

			private DataTablesImpl GetOrCreateDataTables(DeserializeContext context)
			{
				return null;
			}

			private void ClearDataTables()
			{
			}

			private void UpdateWorldMetaDataFromWorldData()
			{
			}

			private void UpdateWorldDataUpdate(int worldSeq)
			{
			}

			public void UpdateWorldLastestSequence(int worldSeq)
			{
			}

			private void AddWorldDataChunk(int worldSeq, byte[] data)
			{
			}

			public void UpdateWorldLastestHistory(int fromSeq, int toSeq, JsonData jsonData)
			{
			}

			private void UpdateWithSnapshotAndHistory(int snapshot_seq, JsonData snapshot_data, JsonData histories)
			{
			}

			public void UpdateWithHistory(JsonData jsonData)
			{
			}

			private void SendResultEvent(DeserializeContext context)
			{
			}

			public void AssembleDataChunks(DeserializeContext context)
			{
			}

			public override void Update()
			{
			}

			public override void LogOut()
			{
			}

			protected override int Send(int clanDbShard, int worldID, string uri, string method, bool showNetworkIndicator, object[] args)
			{
				return 0;
			}

			protected override int SendAndWait(int clanDbShard, int worldID, string uri, string method, bool showNetworkIndicator, object[] args)
			{
				return 0;
			}

			private void UpdateSessions()
			{
			}

			private SessionImpl CreateSession(ConnectionInfoImpl connectionInfo)
			{
				return null;
			}

			public override Session RequestSession(string socketServerUuid, string socketServerIP, int socketServerPort, string logicServerUuid, string logicServerIP, int logicServerPort, string distributorServerUuid, string distributorServerIP, int distributorServerPort, ulong userId)
			{
				return null;
			}

			private void UpdateGetWorldMetaData()
			{
			}

			private bool NeedToUpdateGetInfo()
			{
				return false;
			}

			private void OnGetWorldMetaData(JsonData response)
			{
			}

			private void RequestGetWorldMetaData()
			{
			}

			private void UpdateWorlds()
			{
			}

			private bool CanRequestWorldData()
			{
				return false;
			}

			private int GetWorldSequence(int clan_db_shard, int world_id)
			{
				return 0;
			}

			private void RequestWorldData()
			{
			}

			private void OnGetWorldData(JsonData response)
			{
			}

			public override int GetWorldSeq()
			{
				return 0;
			}

			private void CommonResponse(JsonData jsonData, int requestIdx)
			{
			}
		}

		public interface IDataTableOwnerImpl : IDataTableOwner
		{
			DataTables dataTables { get; set; }
		}

		public interface IDataTypeImpl<DataType> : IDataType<DataType>, IDataType, IDataUpdateListener<DataType> where DataType : class, IDataType
		{
		}

		public interface IClientDataOwnerImpl
		{
			void InitClientData();

			void UpdateClientData();

			void DestroyClientData();

			void EmitUpdateEvent();
		}

		public interface ICreatorOwner<OwnerData>
		{
			OwnerData creator { get; }
		}

		public interface IServerDataType<FlatBufferDataType> where FlatBufferDataType : struct, IFlatbufferObject
		{
			void DeserializeData(FlatBufferDataType data, data allDatas);
		}

		public class DataConstructionException : Exception
		{
			public DataConstructionException(Type type)
			{
			}
		}

		public class DataRefImpl<OwnerData, TargetData> : DataRef<TargetData>, ICreatorOwner<OwnerData> where OwnerData : class, IDataType, IDataRefAble<OwnerData> where TargetData : class, IDataType, IDataRefAble<TargetData>
		{
			private Action m_onDataRefUpdated;

			private OwnerData m_ownerData;

			private IDataRefInternal<TargetData> m_dataRefInternal;

			public override Action onDataRefUpdated
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public OwnerData creator
			{
				get
				{
					return null;
				}
			}

			public override TargetData data
			{
				get
				{
					return null;
				}
			}

			public IDataRefInternal<TargetData> dataRefInternal
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public DataRefImpl(OwnerData ownerData)
			{
			}

			~DataRefImpl()
			{
			}
		}

		public class DataRefInternalEachOther<OwnerData, TargetData> : IDataRefInternal<TargetData>, IDataRefInternal where OwnerData : class, IDataType, IDataRefAble<OwnerData> where TargetData : class, IDataType, IDataRefAble<TargetData>
		{
			private DataRefImpl<OwnerData, TargetData> m_owner;

			private TargetData m_data;

			private DataRefInternalEachOther<TargetData, OwnerData> m_other;

			public void ResetAllData()
			{
			}

			public TargetData GetData()
			{
				return null;
			}

			public static void SetData(DataRefInternalEachOther<OwnerData, TargetData> self, DataRefInternalEachOther<TargetData, OwnerData> other, DataRefImpl<OwnerData, TargetData> selfOwner, DataRefImpl<TargetData, OwnerData> otherOwner)
			{
			}

			private static void ResetData(DataRefInternalEachOther<OwnerData, TargetData> self, DataRefInternalEachOther<TargetData, OwnerData> other)
			{
			}

			public void UpdateCallback()
			{
			}
		}

		public class DataRefInternalFromDictionary<DictionaryTargetData, DictionaryTargetKey, DictionaryOwnerData> : IDataRefInternal<DictionaryOwnerData>, IDataRefInternal where DictionaryTargetData : class, IDataType, IKeyOwner<DictionaryTargetKey>, IDataRefAble<DictionaryTargetData> where DictionaryTargetKey : struct, IKeyType<DictionaryTargetKey> where DictionaryOwnerData : class, IDataType, IDataRefAble<DictionaryOwnerData>
		{
			public DataRefImpl<DictionaryTargetData, DictionaryOwnerData> m_owner;

			public DictionaryOwnerData m_data;

			public DataRefInternalInDictionary<DictionaryTargetData, DictionaryTargetKey, DictionaryOwnerData> m_other;

			public DictionaryOwnerData GetData()
			{
				return null;
			}

			public void ResetAllData()
			{
			}

			public static void SetData(DictionaryTargetKey key, DataRefInternalFromDictionary<DictionaryTargetData, DictionaryTargetKey, DictionaryOwnerData> self, DataRefInternalInDictionary<DictionaryTargetData, DictionaryTargetKey, DictionaryOwnerData> other, DataRefImpl<DictionaryTargetData, DictionaryOwnerData> selfOwner, DictionaryRefImpl<DictionaryOwnerData, DictionaryTargetKey, DictionaryTargetData> otherOwner)
			{
			}

			public static void ResetData(DataRefInternalFromDictionary<DictionaryTargetData, DictionaryTargetKey, DictionaryOwnerData> self, DataRefInternalInDictionary<DictionaryTargetData, DictionaryTargetKey, DictionaryOwnerData> other)
			{
			}

			public void UpdateCallback()
			{
			}
		}

		public class DataRefInternalInDictionary<DictionaryTargetData, DictionaryTargetKey, DictionaryOwnerData> : IDataRefInternal<DictionaryTargetData>, IDataRefInternal where DictionaryTargetData : class, IDataType, IKeyOwner<DictionaryTargetKey>, IDataRefAble<DictionaryTargetData> where DictionaryTargetKey : struct, IKeyType<DictionaryTargetKey> where DictionaryOwnerData : class, IDataType, IDataRefAble<DictionaryOwnerData>
		{
			public DictionaryRefImpl<DictionaryOwnerData, DictionaryTargetKey, DictionaryTargetData> m_owner;

			public DictionaryTargetKey m_key;

			public DictionaryTargetData m_data;

			public DataRefInternalFromDictionary<DictionaryTargetData, DictionaryTargetKey, DictionaryOwnerData> m_other;

			public DictionaryTargetData GetData()
			{
				return null;
			}

			public void ResetAllData()
			{
			}

			public void AddCallback()
			{
			}

			public void RemoveCallback(DictionaryTargetData data)
			{
			}
		}

		public class DictionaryRefImpl<OwnerData, TargetKey, TargetData> : DictionaryRef<TargetKey, TargetData>, ICreatorOwner<OwnerData> where OwnerData : class, IDataType, IDataRefAble<OwnerData> where TargetKey : struct, IKeyType<TargetKey> where TargetData : class, IDataType, IKeyOwner<TargetKey>, IDataRefAble<TargetData>
		{
			public struct Enumerator : IEnumerator, IDisposable, IEnumerator<TargetData>
			{
				private Dictionary<TargetKey, DataRefInternalInDictionary<TargetData, TargetKey, OwnerData>>.ValueCollection m_refs;

				private Dictionary<TargetKey, DataRefInternalInDictionary<TargetData, TargetKey, OwnerData>>.ValueCollection.Enumerator m_originEnumerator;

				private TargetData m_current;

				object IEnumerator.Current
				{
					get
					{
						return null;
					}
				}

				public TargetData Current
				{
					get
					{
						return null;
					}
				}

				public Enumerator(DictionaryRefImpl<OwnerData, TargetKey, TargetData> parent)
				{
					m_refs = null;
					m_originEnumerator = default(Dictionary<TargetKey, DataRefInternalInDictionary<TargetData, TargetKey, OwnerData>>.ValueCollection.Enumerator);
					m_current = null;
				}

				public void Dispose()
				{
				}

				public bool MoveNext()
				{
					return false;
				}

				public void Reset()
				{
				}
			}

			private Action<TargetKey, TargetData> m_onDataRefAdded;

			private Action<TargetKey, TargetData> m_onDataRefRemoved;

			public OwnerData m_owner;

			private Dictionary<TargetKey, DataRefInternalInDictionary<TargetData, TargetKey, OwnerData>> m_key2Ref;

			public override Action<TargetKey, TargetData> onDataRefAdded
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<TargetKey, TargetData> onDataRefRemoved
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public OwnerData creator
			{
				get
				{
					return null;
				}
			}

			// C# has no syntax for parameterized property 'Item'.
			public override TargetData get_Item(TargetKey kType)
			{
				return null;
			}

			public override ICollection<TargetKey> Keys
			{
				get
				{
					return null;
				}
			}

			public override int Count
			{
				get
				{
					return 0;
				}
			}

			public DictionaryRefImpl(OwnerData owner)
			{
			}

			~DictionaryRefImpl()
			{
			}

			public override IEnumerator<TargetData> GetEnumerator()
			{
				return null;
			}

			public override bool ContainsKey(TargetKey key)
			{
				return false;
			}

			public override bool TryGetValue(TargetKey key, out TargetData value)
			{
				value = null;
				return false;
			}

			public TargetData GetData(TargetKey kType)
			{
				return null;
			}

			public DataRefInternalInDictionary<TargetData, TargetKey, OwnerData> GetDataInternal(TargetKey kType)
			{
				return null;
			}

			public void RemoveDataInternal(TargetKey key)
			{
			}

			public void RemoveDataInternalsAll()
			{
			}

			public void AddDataInternal(TargetKey key, DataRefInternalInDictionary<TargetData, TargetKey, OwnerData> dataRefInternal)
			{
			}
		}

		public class DataRefInternalFromList<ListTargetData, ListOwnerData> : IDataRefInternal<ListOwnerData>, IDataRefInternal where ListTargetData : class, IDataType, IDataRefAble<ListTargetData> where ListOwnerData : class, IDataType, IDataRefAble<ListOwnerData>
		{
			public DataRefImpl<ListTargetData, ListOwnerData> m_owner;

			public ListOwnerData m_data;

			public DataRefInternalInList<ListTargetData, ListOwnerData> m_other;

			public void ResetAllData()
			{
			}

			public ListOwnerData GetData()
			{
				return null;
			}

			public static void SetData(DataRefInternalFromList<ListTargetData, ListOwnerData> self, DataRefInternalInList<ListTargetData, ListOwnerData> other, DataRefImpl<ListTargetData, ListOwnerData> selfOwner, ListRefImpl<ListOwnerData, ListTargetData> otherOwner)
			{
			}

			public static void ResetData(DataRefInternalFromList<ListTargetData, ListOwnerData> self, DataRefInternalInList<ListTargetData, ListOwnerData> other)
			{
			}

			public void UpdateCallback()
			{
			}
		}

		public class DataRefInternalInList<ListTargetData, ListOwnerData> : IDataRefInternal<ListTargetData>, IDataRefInternal where ListTargetData : class, IDataType, IDataRefAble<ListTargetData> where ListOwnerData : class, IDataType, IDataRefAble<ListOwnerData>
		{
			public ListRefImpl<ListOwnerData, ListTargetData> m_owner;

			public ListTargetData m_data;

			public DataRefInternalFromList<ListTargetData, ListOwnerData> m_other;

			public void ResetAllData()
			{
			}

			public ListTargetData GetData()
			{
				return null;
			}

			public void AddCallback()
			{
			}

			public void RemoveCallback(ListTargetData data)
			{
			}
		}

		public class ListRefImpl<OwnerData, TargetData> : ListRef<TargetData>, ICreatorOwner<OwnerData> where OwnerData : class, IDataType, IDataRefAble<OwnerData> where TargetData : class, IDataType, IDataRefAble<TargetData>
		{
			public struct Enumerator : IEnumerator, IDisposable, IEnumerator<TargetData>
			{
				private List<DataRefInternalInList<TargetData, OwnerData>> m_key2Ref;

				private List<DataRefInternalInList<TargetData, OwnerData>>.Enumerator m_originEnumerator;

				private TargetData m_current;

				object IEnumerator.Current
				{
					get
					{
						return null;
					}
				}

				public TargetData Current
				{
					get
					{
						return null;
					}
				}

				public Enumerator(ListRefImpl<OwnerData, TargetData> parent)
				{
					m_key2Ref = null;
					m_originEnumerator = default(List<DataRefInternalInList<TargetData, OwnerData>>.Enumerator);
					m_current = null;
				}

				public void Dispose()
				{
				}

				public bool MoveNext()
				{
					return false;
				}

				public void Reset()
				{
				}
			}

			private Action<TargetData> m_onDataRefAdded;

			private Action<TargetData> m_onDataRefRemoved;

			public OwnerData m_owner;

			private List<DataRefInternalInList<TargetData, OwnerData>> m_refList;

			public override Action<TargetData> onDataRefAdded
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<TargetData> onDataRefRemoved
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public OwnerData creator
			{
				get
				{
					return null;
				}
			}

			// C# has no syntax for parameterized property 'Item'.
			public override TargetData get_Item(int index)
			{
				return null;
			}

			public override int Count
			{
				get
				{
					return 0;
				}
			}

			public ListRefImpl(OwnerData owner)
			{
			}

			~ListRefImpl()
			{
			}

			public override IEnumerator<TargetData> GetEnumerator()
			{
				return null;
			}

			public override bool Contains(TargetData targetData)
			{
				return false;
			}

			public TargetData GetData(int index)
			{
				return null;
			}

			public DataRefInternalInList<TargetData, OwnerData> GetDataInternal(TargetData data)
			{
				return null;
			}

			public void RemoveDataInternal(DataRefInternalInList<TargetData, OwnerData> data)
			{
			}

			public void RemoveDataInternalsAll()
			{
			}

			public void AddDataInternal(DataRefInternalInList<TargetData, OwnerData> dataRefInternal)
			{
			}
		}

		public class DataContainerLinkerImpl<CreatorDataType> : IDataContainerLinker where CreatorDataType : class, IDataType, IDataRefAble<CreatorDataType>
		{
			protected CreatorDataType m_creatorData;

			public DataContainerLinkerImpl(CreatorDataType creatorData)
			{
			}

			public void MakeDataRefEachOther<TargetData1, TargetData2>(DataRef<TargetData2> _target1To2, DataRef<TargetData1> _target2To1) where TargetData1 : class, IDataType, IDataRefAble<TargetData1> where TargetData2 : class, IDataType, IDataRefAble<TargetData2>
			{
			}

			public void MakeDataList<TargetData1, TargetData2>(ListRef<TargetData2> _target1To2, DataRef<TargetData1> _target2To1) where TargetData1 : class, IDataType, IDataRefAble<TargetData1> where TargetData2 : class, IDataType, IDataRefAble<TargetData2>
			{
			}

			public void SkipDataRef<TargetData>(DataRef<TargetData> target) where TargetData : class, IDataType, IDataRefAble<TargetData>
			{
			}

			public void SkipDataDictionary<TargetKey, TargetData>(DictionaryRef<TargetKey, TargetData> target) where TargetKey : struct, IKeyType<TargetKey> where TargetData : class, IDataType, IKeyOwner<TargetKey>, IDataRefAble<TargetData>
			{
			}

			public void SkipDataList<TargetData>(ListRef<TargetData> target) where TargetData : class, IDataType, IDataRefAble<TargetData>
			{
			}
		}

		public class DataContainerLinkerImpl<TargetKey2, CreatorDataType> : DataContainerLinkerImpl<CreatorDataType>, IDataContainerLinker<TargetKey2>, IDataContainerLinker where TargetKey2 : struct, IKeyType<TargetKey2> where CreatorDataType : class, IDataType, IKeyOwner<TargetKey2>, IDataRefAble<CreatorDataType>
		{
			public DataContainerLinkerImpl(CreatorDataType creatorData)
				: base((CreatorDataType)null)
			{
			}

			public void MakeDataDictionary<TargetData1, TargetData2>(DictionaryRef<TargetKey2, TargetData2> _target1To2, DataRef<TargetData1> _target2To1) where TargetData1 : class, IDataType, IDataRefAble<TargetData1> where TargetData2 : class, IDataType, IKeyOwner<TargetKey2>, IDataRefAble<TargetData2>
			{
			}
		}

		public class DataContainerCreatorImpl<CreatorDataType> : IDataContainerCreator where CreatorDataType : class, IDataType, IDataRefAble<CreatorDataType>
		{
			private CreatorDataType m_creatorData;

			public DataContainerCreatorImpl(CreatorDataType creatorData)
			{
			}

			public void CreateData<TargetData>(out DataRef<TargetData> data) where TargetData : class, IDataType, IDataRefAble<TargetData>
			{
				data = null;
			}

			public DataRef<TargetData> CreateData<TargetData>() where TargetData : class, IDataType, IDataRefAble<TargetData>
			{
				return null;
			}

			public void CreateDictionary<TargetKey, TargetData>(out DictionaryRef<TargetKey, TargetData> dictionary) where TargetKey : struct, IKeyType<TargetKey> where TargetData : class, IDataType, IKeyOwner<TargetKey>, IDataRefAble<TargetData>
			{
				dictionary = null;
			}

			public DictionaryRef<TargetKey, TargetData> CreateDictionary<TargetKey, TargetData>() where TargetKey : struct, IKeyType<TargetKey> where TargetData : class, IDataType, IKeyOwner<TargetKey>, IDataRefAble<TargetData>
			{
				return null;
			}

			public void CreateList<TargetData>(out ListRef<TargetData> list) where TargetData : class, IDataType, IDataRefAble<TargetData>
			{
				list = null;
			}

			public ListRef<TargetData> CreateList<TargetData>() where TargetData : class, IDataType, IDataRefAble<TargetData>
			{
				return null;
			}
		}

		public class DirectionParser<DataType, ClientDataType, FlatBufferDataType> where DataType : class, IDataTableOwner, IDataType where ClientDataType : class, IDataTableOwnerImpl, IServerDataType<FlatBufferDataType>, DataType, new() where FlatBufferDataType : struct, IFlatbufferObject
		{
			public Action<DataType> dataTableAction;

			public Action<DataType> worldDataAction;

			public void Deserialize(DataTables dataTables, DeserializeContext deserializeContext, FlatBufferDataType data, data allDatas)
			{
			}

			public void Clear(DeserializeContext context)
			{
			}
		}

		public class BuildingDataImpl : BuildingData, IDataTypeImpl<BuildingData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<BuildingData>, IServerDataType<building>, IDataType<BuildingData>, IDataType, IDataUpdateListener<BuildingData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<BuildingData> m_onUpdate;

			private BuildingKey _key;

			private int m_p_place_id;

			private short m_pos_x;

			private short m_pos_y;

			private ulong m_guild_id;

			private ulong m_vision_guild_id;

			private int m_building_id;

			private int m_building_hp;

			private int m_update_date;

			private int m_lastest_passive_index;

			private int m_owner_change_date;

			private int m_auto_attack_tick;

			private int m_auto_attack_interval_tick;

			private int m_repair_start_date;

			private int m_repair_end_date;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<BuildingData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int p_place_id
			{
				get
				{
					return 0;
				}
			}

			public override short pos_x
			{
				get
				{
					return 0;
				}
			}

			public override short pos_y
			{
				get
				{
					return 0;
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override ulong vision_guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override int building_id
			{
				get
				{
					return 0;
				}
			}

			public override int building_hp
			{
				get
				{
					return 0;
				}
			}

			public override int update_date
			{
				get
				{
					return 0;
				}
			}

			public override int lastest_passive_index
			{
				get
				{
					return 0;
				}
			}

			public override int owner_change_date
			{
				get
				{
					return 0;
				}
			}

			public override int auto_attack_tick
			{
				get
				{
					return 0;
				}
			}

			public override int auto_attack_interval_tick
			{
				get
				{
					return 0;
				}
			}

			public override int repair_start_date
			{
				get
				{
					return 0;
				}
			}

			public override int repair_end_date
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override BuildingKey GetKey()
			{
				return default(BuildingKey);
			}

			public void DeserializeData(building data, data allDatas)
			{
			}
		}

		public class BuildingCustomFunctionDataImpl : BuildingCustomFunctionData, IDataTypeImpl<BuildingCustomFunctionData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<BuildingCustomFunctionData>, IServerDataType<building_custom_function>, IDataType<BuildingCustomFunctionData>, IDataType, IDataUpdateListener<BuildingCustomFunctionData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<BuildingCustomFunctionData> m_onUpdate;

			private BuildingCustomFunctionKey _key;

			private int m_p_place_id;

			private short m_custom_action_type;

			private int m_cooldown_date;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<BuildingCustomFunctionData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int p_place_id
			{
				get
				{
					return 0;
				}
			}

			public override short custom_action_type
			{
				get
				{
					return 0;
				}
			}

			public override int cooldown_date
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override BuildingCustomFunctionKey GetKey()
			{
				return default(BuildingCustomFunctionKey);
			}

			public void DeserializeData(building_custom_function data, data allDatas)
			{
			}
		}

		public class BuildingHistoryDataImpl : BuildingHistoryData, IDataTypeImpl<BuildingHistoryData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<BuildingHistoryData>, IServerDataType<building_history>, IDataType<BuildingHistoryData>, IDataType, IDataUpdateListener<BuildingHistoryData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<BuildingHistoryData> m_onUpdate;

			private BuildingHistoryKey _key;

			private int m_p_place_id;

			private int m_history_id;

			private int m_history_key;

			private int m_history_value;

			private int m_latest_date;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<BuildingHistoryData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int p_place_id
			{
				get
				{
					return 0;
				}
			}

			public override int history_id
			{
				get
				{
					return 0;
				}
			}

			public override int history_key
			{
				get
				{
					return 0;
				}
			}

			public override int history_value
			{
				get
				{
					return 0;
				}
			}

			public override int latest_date
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override BuildingHistoryKey GetKey()
			{
				return default(BuildingHistoryKey);
			}

			public void DeserializeData(building_history data, data allDatas)
			{
			}
		}

		public class BuildingPassiveDataImpl : BuildingPassiveData, IDataTypeImpl<BuildingPassiveData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<BuildingPassiveData>, IServerDataType<building_passive>, IDataType<BuildingPassiveData>, IDataType, IDataUpdateListener<BuildingPassiveData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<BuildingPassiveData> m_onUpdate;

			private BuildingPassiveKey _key;

			private int m_p_place_id;

			private int m_passive_index;

			private int m_p_passive_list;

			private int m_create_date;

			private int m_update_date;

			private int m_end_date;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<BuildingPassiveData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int p_place_id
			{
				get
				{
					return 0;
				}
			}

			public override int passive_index
			{
				get
				{
					return 0;
				}
			}

			public override int p_passive_list
			{
				get
				{
					return 0;
				}
			}

			public override int create_date
			{
				get
				{
					return 0;
				}
			}

			public override int update_date
			{
				get
				{
					return 0;
				}
			}

			public override int end_date
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override BuildingPassiveKey GetKey()
			{
				return default(BuildingPassiveKey);
			}

			public void DeserializeData(building_passive data, data allDatas)
			{
			}
		}

		public class BuildingPassiveDamageDataImpl : BuildingPassiveDamageData, IDataTypeImpl<BuildingPassiveDamageData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<BuildingPassiveDamageData>, IServerDataType<building_passive_damage>, IDataType<BuildingPassiveDamageData>, IDataType, IDataUpdateListener<BuildingPassiveDamageData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<BuildingPassiveDamageData> m_onUpdate;

			private BuildingPassiveDamageKey _key;

			private int m_p_place_id;

			private int m_passive_index;

			private int m_passive_id;

			private int m_create_date;

			private int m_first_tick_date;

			private int m_last_tick_date;

			private int m_next_tick_date;

			private int m_end_date;

			private short m_damage_type;

			private int m_damage_power;

			private int m_damage_interval;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<BuildingPassiveDamageData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int p_place_id
			{
				get
				{
					return 0;
				}
			}

			public override int passive_index
			{
				get
				{
					return 0;
				}
			}

			public override int passive_id
			{
				get
				{
					return 0;
				}
			}

			public override int create_date
			{
				get
				{
					return 0;
				}
			}

			public override int first_tick_date
			{
				get
				{
					return 0;
				}
			}

			public override int last_tick_date
			{
				get
				{
					return 0;
				}
			}

			public override int next_tick_date
			{
				get
				{
					return 0;
				}
			}

			public override int end_date
			{
				get
				{
					return 0;
				}
			}

			public override short damage_type
			{
				get
				{
					return 0;
				}
			}

			public override int damage_power
			{
				get
				{
					return 0;
				}
			}

			public override int damage_interval
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override BuildingPassiveDamageKey GetKey()
			{
				return default(BuildingPassiveDamageKey);
			}

			public void DeserializeData(building_passive_damage data, data allDatas)
			{
			}
		}

		public class CorpsDataImpl : CorpsData, IDataTypeImpl<CorpsData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<CorpsData>, IServerDataType<corps>, IDataType<CorpsData>, IDataType, IDataUpdateListener<CorpsData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<CorpsData> m_onUpdate;

			private CorpsKey _key;

			private int m_corps_id;

			private ulong m_user_id;

			private ulong m_guild_id;

			private int m_atk;

			private int m_inte;

			private int m_def;

			private int m_agi;

			private int m_mor;

			private short m_pos_x;

			private short m_pos_y;

			private int m_exp;

			private short m_forward_direction;

			private int m_military_power;

			private int m_total_damage;

			private int m_destory_corps_count;

			private int m_lastest_passive_index;

			private int m_siege_weapon_use_count;

			private short m_action_point;

			private int m_lastest_action_date;

			private int m_update_date;

			private int m_create_date;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<CorpsData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int corps_id
			{
				get
				{
					return 0;
				}
			}

			public override ulong user_id
			{
				get
				{
					return 0uL;
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override int atk
			{
				get
				{
					return 0;
				}
			}

			public override int inte
			{
				get
				{
					return 0;
				}
			}

			public override int def
			{
				get
				{
					return 0;
				}
			}

			public override int agi
			{
				get
				{
					return 0;
				}
			}

			public override int mor
			{
				get
				{
					return 0;
				}
			}

			public override short pos_x
			{
				get
				{
					return 0;
				}
			}

			public override short pos_y
			{
				get
				{
					return 0;
				}
			}

			public override int exp
			{
				get
				{
					return 0;
				}
			}

			public override short forward_direction
			{
				get
				{
					return 0;
				}
			}

			public override int military_power
			{
				get
				{
					return 0;
				}
			}

			public override int total_damage
			{
				get
				{
					return 0;
				}
			}

			public override int destory_corps_count
			{
				get
				{
					return 0;
				}
			}

			public override int lastest_passive_index
			{
				get
				{
					return 0;
				}
			}

			public override int siege_weapon_use_count
			{
				get
				{
					return 0;
				}
			}

			public override short action_point
			{
				get
				{
					return 0;
				}
			}

			public override int lastest_action_date
			{
				get
				{
					return 0;
				}
			}

			public override int update_date
			{
				get
				{
					return 0;
				}
			}

			public override int create_date
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override CorpsKey GetKey()
			{
				return default(CorpsKey);
			}

			public void DeserializeData(corps data, data allDatas)
			{
			}
		}

		public class CorpsCustomFunctionDataImpl : CorpsCustomFunctionData, IDataTypeImpl<CorpsCustomFunctionData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<CorpsCustomFunctionData>, IServerDataType<corps_custom_function>, IDataType<CorpsCustomFunctionData>, IDataType, IDataUpdateListener<CorpsCustomFunctionData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<CorpsCustomFunctionData> m_onUpdate;

			private CorpsCustomFunctionKey _key;

			private int m_corps_id;

			private short m_custom_action_type;

			private int m_custom_action_key;

			private int m_available_date;

			private int m_available_duration;

			private int m_cooldown_date;

			private short m_state;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<CorpsCustomFunctionData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int corps_id
			{
				get
				{
					return 0;
				}
			}

			public override short custom_action_type
			{
				get
				{
					return 0;
				}
			}

			public override int custom_action_key
			{
				get
				{
					return 0;
				}
			}

			public override int available_date
			{
				get
				{
					return 0;
				}
			}

			public override int available_duration
			{
				get
				{
					return 0;
				}
			}

			public override int cooldown_date
			{
				get
				{
					return 0;
				}
			}

			public override short state
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override CorpsCustomFunctionKey GetKey()
			{
				return default(CorpsCustomFunctionKey);
			}

			public void DeserializeData(corps_custom_function data, data allDatas)
			{
			}
		}

		public class CorpsFunctionDataImpl : CorpsFunctionData, IDataTypeImpl<CorpsFunctionData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<CorpsFunctionData>, IServerDataType<corps_function>, IDataType<CorpsFunctionData>, IDataType, IDataUpdateListener<CorpsFunctionData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<CorpsFunctionData> m_onUpdate;

			private CorpsFunctionKey _key;

			private int m_corps_id;

			private int m_p_corps_function_id;

			private int m_available_date;

			private int m_available_duration;

			private int m_cooldown_date;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<CorpsFunctionData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int corps_id
			{
				get
				{
					return 0;
				}
			}

			public override int p_corps_function_id
			{
				get
				{
					return 0;
				}
			}

			public override int available_date
			{
				get
				{
					return 0;
				}
			}

			public override int available_duration
			{
				get
				{
					return 0;
				}
			}

			public override int cooldown_date
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override CorpsFunctionKey GetKey()
			{
				return default(CorpsFunctionKey);
			}

			public void DeserializeData(corps_function data, data allDatas)
			{
			}
		}

		public class CorpsHistoryDataImpl : CorpsHistoryData, IDataTypeImpl<CorpsHistoryData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<CorpsHistoryData>, IServerDataType<corps_history>, IDataType<CorpsHistoryData>, IDataType, IDataUpdateListener<CorpsHistoryData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<CorpsHistoryData> m_onUpdate;

			private CorpsHistoryKey _key;

			private int m_corps_id;

			private int m_history_id;

			private int m_history_key;

			private int m_history_value;

			private int m_latest_date;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<CorpsHistoryData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int corps_id
			{
				get
				{
					return 0;
				}
			}

			public override int history_id
			{
				get
				{
					return 0;
				}
			}

			public override int history_key
			{
				get
				{
					return 0;
				}
			}

			public override int history_value
			{
				get
				{
					return 0;
				}
			}

			public override int latest_date
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override CorpsHistoryKey GetKey()
			{
				return default(CorpsHistoryKey);
			}

			public void DeserializeData(corps_history data, data allDatas)
			{
			}
		}

		public class CorpsInformationDataImpl : CorpsInformationData, IDataTypeImpl<CorpsInformationData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<CorpsInformationData>, IServerDataType<corps_information>, IDataType<CorpsInformationData>, IDataType, IDataUpdateListener<CorpsInformationData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<CorpsInformationData> m_onUpdate;

			private CorpsInformationKey _key;

			private int m_corps_id;

			private int m_face_unit_id;

			private int m_face_skinitem_id;

			private string m_nation_name;

			private int m_job_id;

			private int m_jobType_id;

			private int m_base_atk;

			private int m_base_inte;

			private int m_base_def;

			private int m_base_agi;

			private int m_base_mor;

			private int m_additive_atk_percent;

			private int m_additive_inte_percent;

			private int m_additive_def_percent;

			private int m_additive_agi_percent;

			private int m_additive_mor_percent;

			private short m_job_max_level;

			private int m_max_military_power;

			private short m_max_action_point;

			private int m_action_point_recovery_time;

			private int m_action_point_recovery_amount;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<CorpsInformationData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int corps_id
			{
				get
				{
					return 0;
				}
			}

			public override int face_unit_id
			{
				get
				{
					return 0;
				}
			}

			public override int face_skinitem_id
			{
				get
				{
					return 0;
				}
			}

			public override string nation_name
			{
				get
				{
					return null;
				}
			}

			public override int job_id
			{
				get
				{
					return 0;
				}
			}

			public override int jobType_id
			{
				get
				{
					return 0;
				}
			}

			public override int base_atk
			{
				get
				{
					return 0;
				}
			}

			public override int base_inte
			{
				get
				{
					return 0;
				}
			}

			public override int base_def
			{
				get
				{
					return 0;
				}
			}

			public override int base_agi
			{
				get
				{
					return 0;
				}
			}

			public override int base_mor
			{
				get
				{
					return 0;
				}
			}

			public override int additive_atk_percent
			{
				get
				{
					return 0;
				}
			}

			public override int additive_inte_percent
			{
				get
				{
					return 0;
				}
			}

			public override int additive_def_percent
			{
				get
				{
					return 0;
				}
			}

			public override int additive_agi_percent
			{
				get
				{
					return 0;
				}
			}

			public override int additive_mor_percent
			{
				get
				{
					return 0;
				}
			}

			public override short job_max_level
			{
				get
				{
					return 0;
				}
			}

			public override int max_military_power
			{
				get
				{
					return 0;
				}
			}

			public override short max_action_point
			{
				get
				{
					return 0;
				}
			}

			public override int action_point_recovery_time
			{
				get
				{
					return 0;
				}
			}

			public override int action_point_recovery_amount
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override CorpsInformationKey GetKey()
			{
				return default(CorpsInformationKey);
			}

			public void DeserializeData(corps_information data, data allDatas)
			{
			}
		}

		public class CorpsPassiveDataImpl : CorpsPassiveData, IDataTypeImpl<CorpsPassiveData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<CorpsPassiveData>, IServerDataType<corps_passive>, IDataType<CorpsPassiveData>, IDataType, IDataUpdateListener<CorpsPassiveData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<CorpsPassiveData> m_onUpdate;

			private CorpsPassiveKey _key;

			private int m_corps_id;

			private int m_passive_index;

			private int m_p_passive_list;

			private int m_create_date;

			private int m_update_date;

			private int m_end_date;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<CorpsPassiveData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int corps_id
			{
				get
				{
					return 0;
				}
			}

			public override int passive_index
			{
				get
				{
					return 0;
				}
			}

			public override int p_passive_list
			{
				get
				{
					return 0;
				}
			}

			public override int create_date
			{
				get
				{
					return 0;
				}
			}

			public override int update_date
			{
				get
				{
					return 0;
				}
			}

			public override int end_date
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override CorpsPassiveKey GetKey()
			{
				return default(CorpsPassiveKey);
			}

			public void DeserializeData(corps_passive data, data allDatas)
			{
			}
		}

		public class CorpsPassiveDamageDataImpl : CorpsPassiveDamageData, IDataTypeImpl<CorpsPassiveDamageData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<CorpsPassiveDamageData>, IServerDataType<corps_passive_damage>, IDataType<CorpsPassiveDamageData>, IDataType, IDataUpdateListener<CorpsPassiveDamageData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<CorpsPassiveDamageData> m_onUpdate;

			private CorpsPassiveDamageKey _key;

			private int m_corps_id;

			private int m_passive_index;

			private int m_passive_id;

			private int m_create_date;

			private int m_first_tick_date;

			private int m_last_tick_date;

			private int m_next_tick_date;

			private int m_end_date;

			private short m_damage_type;

			private int m_damage_power;

			private int m_damage_interval;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<CorpsPassiveDamageData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int corps_id
			{
				get
				{
					return 0;
				}
			}

			public override int passive_index
			{
				get
				{
					return 0;
				}
			}

			public override int passive_id
			{
				get
				{
					return 0;
				}
			}

			public override int create_date
			{
				get
				{
					return 0;
				}
			}

			public override int first_tick_date
			{
				get
				{
					return 0;
				}
			}

			public override int last_tick_date
			{
				get
				{
					return 0;
				}
			}

			public override int next_tick_date
			{
				get
				{
					return 0;
				}
			}

			public override int end_date
			{
				get
				{
					return 0;
				}
			}

			public override short damage_type
			{
				get
				{
					return 0;
				}
			}

			public override int damage_power
			{
				get
				{
					return 0;
				}
			}

			public override int damage_interval
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override CorpsPassiveDamageKey GetKey()
			{
				return default(CorpsPassiveDamageKey);
			}

			public void DeserializeData(corps_passive_damage data, data allDatas)
			{
			}
		}

		public class CorpsSiegeWeaponDataImpl : CorpsSiegeWeaponData, IDataTypeImpl<CorpsSiegeWeaponData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<CorpsSiegeWeaponData>, IServerDataType<corps_siege_weapon>, IDataType<CorpsSiegeWeaponData>, IDataType, IDataUpdateListener<CorpsSiegeWeaponData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<CorpsSiegeWeaponData> m_onUpdate;

			private CorpsSiegeWeaponKey _key;

			private int m_corps_id;

			private int m_p_siege_weapon;

			private int m_oldCorpsInfo_job_id;

			private int m_oldCorpsInfo_jobType_id;

			private short m_oldCorpsInfo_job_max_level;

			private int m_oldCorpsInfo_max_military_power;

			private int m_oldCorpsInfo_action_point_recovery_amount;

			private int m_oldCorpsInfo_action_point_recovery_time;

			private short m_oldCorpsInfo_max_action_point;

			private int m_oldCorps_exp;

			private int m_oldCorps_military_power;

			private short m_oldCorps_action_point;

			private int m_oldCorps_lastest_action_date;

			private int m_mount_complete_date;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<CorpsSiegeWeaponData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int corps_id
			{
				get
				{
					return 0;
				}
			}

			public override int p_siege_weapon
			{
				get
				{
					return 0;
				}
			}

			public override int oldCorpsInfo_job_id
			{
				get
				{
					return 0;
				}
			}

			public override int oldCorpsInfo_jobType_id
			{
				get
				{
					return 0;
				}
			}

			public override short oldCorpsInfo_job_max_level
			{
				get
				{
					return 0;
				}
			}

			public override int oldCorpsInfo_max_military_power
			{
				get
				{
					return 0;
				}
			}

			public override int oldCorpsInfo_action_point_recovery_amount
			{
				get
				{
					return 0;
				}
			}

			public override int oldCorpsInfo_action_point_recovery_time
			{
				get
				{
					return 0;
				}
			}

			public override short oldCorpsInfo_max_action_point
			{
				get
				{
					return 0;
				}
			}

			public override int oldCorps_exp
			{
				get
				{
					return 0;
				}
			}

			public override int oldCorps_military_power
			{
				get
				{
					return 0;
				}
			}

			public override short oldCorps_action_point
			{
				get
				{
					return 0;
				}
			}

			public override int oldCorps_lastest_action_date
			{
				get
				{
					return 0;
				}
			}

			public override int mount_complete_date
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override CorpsSiegeWeaponKey GetKey()
			{
				return default(CorpsSiegeWeaponKey);
			}

			public void DeserializeData(corps_siege_weapon data, data allDatas)
			{
			}
		}

		public class GuildDataImpl : GuildData, IDataTypeImpl<GuildData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<GuildData>, IServerDataType<guild>, IDataType<GuildData>, IDataType, IDataUpdateListener<GuildData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<GuildData> m_onUpdate;

			private GuildKey _key;

			private ulong m_guild_id;

			private string m_guild_name;

			private int m_emblem_sprite_index;

			private int m_guildwar_rank;

			private short m_position_type;

			private int m_max_user_join_count;

			private int m_color_index;

			private int m_latest_corps_passive_index;

			private int m_ranking_point;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<GuildData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override string guild_name
			{
				get
				{
					return null;
				}
			}

			public override int emblem_sprite_index
			{
				get
				{
					return 0;
				}
			}

			public override int guildwar_rank
			{
				get
				{
					return 0;
				}
			}

			public override short position_type
			{
				get
				{
					return 0;
				}
			}

			public override int max_user_join_count
			{
				get
				{
					return 0;
				}
			}

			public override int color_index
			{
				get
				{
					return 0;
				}
			}

			public override int latest_corps_passive_index
			{
				get
				{
					return 0;
				}
			}

			public override int ranking_point
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override GuildKey GetKey()
			{
				return default(GuildKey);
			}

			public void DeserializeData(guild data, data allDatas)
			{
			}
		}

		public class GuildCorpsPassiveDataImpl : GuildCorpsPassiveData, IDataTypeImpl<GuildCorpsPassiveData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<GuildCorpsPassiveData>, IServerDataType<guild_corps_passive>, IDataType<GuildCorpsPassiveData>, IDataType, IDataUpdateListener<GuildCorpsPassiveData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<GuildCorpsPassiveData> m_onUpdate;

			private GuildCorpsPassiveKey _key;

			private ulong m_guild_id;

			private int m_passive_index;

			private int m_p_passive_list;

			private int m_passive_end_date;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<GuildCorpsPassiveData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override int passive_index
			{
				get
				{
					return 0;
				}
			}

			public override int p_passive_list
			{
				get
				{
					return 0;
				}
			}

			public override int passive_end_date
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override GuildCorpsPassiveKey GetKey()
			{
				return default(GuildCorpsPassiveKey);
			}

			public void DeserializeData(guild_corps_passive data, data allDatas)
			{
			}
		}

		public class GuildCustomFunctionDataImpl : GuildCustomFunctionData, IDataTypeImpl<GuildCustomFunctionData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<GuildCustomFunctionData>, IServerDataType<guild_custom_function>, IDataType<GuildCustomFunctionData>, IDataType, IDataUpdateListener<GuildCustomFunctionData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<GuildCustomFunctionData> m_onUpdate;

			private GuildCustomFunctionKey _key;

			private ulong m_guild_id;

			private short m_custom_guild_action_type;

			private int m_cooldown_date;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<GuildCustomFunctionData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override short custom_guild_action_type
			{
				get
				{
					return 0;
				}
			}

			public override int cooldown_date
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override GuildCustomFunctionKey GetKey()
			{
				return default(GuildCustomFunctionKey);
			}

			public void DeserializeData(guild_custom_function data, data allDatas)
			{
			}
		}

		public class GuildFunctionDataImpl : GuildFunctionData, IDataTypeImpl<GuildFunctionData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<GuildFunctionData>, IServerDataType<guild_function>, IDataType<GuildFunctionData>, IDataType, IDataUpdateListener<GuildFunctionData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<GuildFunctionData> m_onUpdate;

			private GuildFunctionKey _key;

			private ulong m_guild_id;

			private int m_p_guild_function_id;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<GuildFunctionData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override int p_guild_function_id
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override GuildFunctionKey GetKey()
			{
				return default(GuildFunctionKey);
			}

			public void DeserializeData(guild_function data, data allDatas)
			{
			}
		}

		public class GuildHistoryDataImpl : GuildHistoryData, IDataTypeImpl<GuildHistoryData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<GuildHistoryData>, IServerDataType<guild_history>, IDataType<GuildHistoryData>, IDataType, IDataUpdateListener<GuildHistoryData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<GuildHistoryData> m_onUpdate;

			private GuildHistoryKey _key;

			private ulong m_guild_id;

			private int m_history_id;

			private int m_history_value;

			private int m_latest_date;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<GuildHistoryData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override int history_id
			{
				get
				{
					return 0;
				}
			}

			public override int history_value
			{
				get
				{
					return 0;
				}
			}

			public override int latest_date
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override GuildHistoryKey GetKey()
			{
				return default(GuildHistoryKey);
			}

			public void DeserializeData(guild_history data, data allDatas)
			{
			}
		}

		public class GuildMemberGradeDataImpl : GuildMemberGradeData, IDataTypeImpl<GuildMemberGradeData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<GuildMemberGradeData>, IServerDataType<guild_member_grade>, IDataType<GuildMemberGradeData>, IDataType, IDataUpdateListener<GuildMemberGradeData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<GuildMemberGradeData> m_onUpdate;

			private GuildMemberGradeKey _key;

			private ulong m_guild_id;

			private ulong m_user_id;

			private short m_grade;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<GuildMemberGradeData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override ulong user_id
			{
				get
				{
					return 0uL;
				}
			}

			public override short grade
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override GuildMemberGradeKey GetKey()
			{
				return default(GuildMemberGradeKey);
			}

			public void DeserializeData(guild_member_grade data, data allDatas)
			{
			}
		}

		public class TileEffectDataImpl : TileEffectData, IDataTypeImpl<TileEffectData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<TileEffectData>, IServerDataType<tile_effect>, IDataType<TileEffectData>, IDataType, IDataUpdateListener<TileEffectData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<TileEffectData> m_onUpdate;

			private TileEffectKey _key;

			private short m_pos_x;

			private short m_pos_y;

			private int m_p_tile_effect;

			private int m_create_date;

			private int m_update_date;

			private int m_end_date;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<TileEffectData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override short pos_x
			{
				get
				{
					return 0;
				}
			}

			public override short pos_y
			{
				get
				{
					return 0;
				}
			}

			public override int p_tile_effect
			{
				get
				{
					return 0;
				}
			}

			public override int create_date
			{
				get
				{
					return 0;
				}
			}

			public override int update_date
			{
				get
				{
					return 0;
				}
			}

			public override int end_date
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override TileEffectKey GetKey()
			{
				return default(TileEffectKey);
			}

			public void DeserializeData(tile_effect data, data allDatas)
			{
			}
		}

		public class UserDataImpl : UserData, IDataTypeImpl<UserData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<UserData>, IServerDataType<user>, IDataType<UserData>, IDataType, IDataUpdateListener<UserData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<UserData> m_onUpdate;

			private UserKey _key;

			private ulong m_guild_id;

			private ulong m_user_id;

			private int m_face_unit_id;

			private int m_face_skinitem_id;

			private string m_nation_name;

			private int m_p_place_id;

			private int m_contribution;

			private int m_create_date;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<UserData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override ulong user_id
			{
				get
				{
					return 0uL;
				}
			}

			public override int face_unit_id
			{
				get
				{
					return 0;
				}
			}

			public override int face_skinitem_id
			{
				get
				{
					return 0;
				}
			}

			public override string nation_name
			{
				get
				{
					return null;
				}
			}

			public override int p_place_id
			{
				get
				{
					return 0;
				}
			}

			public override int contribution
			{
				get
				{
					return 0;
				}
			}

			public override int create_date
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override UserKey GetKey()
			{
				return default(UserKey);
			}

			public void DeserializeData(user data, data allDatas)
			{
			}
		}

		public class UserCorpsDataImpl : UserCorpsData, IDataTypeImpl<UserCorpsData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<UserCorpsData>, IServerDataType<user_corps>, IDataType<UserCorpsData>, IDataType, IDataUpdateListener<UserCorpsData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<UserCorpsData> m_onUpdate;

			private UserCorpsKey _key;

			private ulong m_guild_id;

			private ulong m_user_id;

			private int m_corps_id;

			private int m_corps_succeeding_exp;

			private int m_face_unit_id;

			private int m_face_skinitem_id;

			private int m_job_id;

			private int m_jobType_id;

			private int m_create_count;

			private int m_corps_creatable_date;

			private int m_create_date;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<UserCorpsData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override ulong user_id
			{
				get
				{
					return 0uL;
				}
			}

			public override int corps_id
			{
				get
				{
					return 0;
				}
			}

			public override int corps_succeeding_exp
			{
				get
				{
					return 0;
				}
			}

			public override int face_unit_id
			{
				get
				{
					return 0;
				}
			}

			public override int face_skinitem_id
			{
				get
				{
					return 0;
				}
			}

			public override int job_id
			{
				get
				{
					return 0;
				}
			}

			public override int jobType_id
			{
				get
				{
					return 0;
				}
			}

			public override int create_count
			{
				get
				{
					return 0;
				}
			}

			public override int corps_creatable_date
			{
				get
				{
					return 0;
				}
			}

			public override int create_date
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override UserCorpsKey GetKey()
			{
				return default(UserCorpsKey);
			}

			public void DeserializeData(user_corps data, data allDatas)
			{
			}
		}

		public class UserHistoryDataImpl : UserHistoryData, IDataTypeImpl<UserHistoryData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<UserHistoryData>, IServerDataType<user_history>, IDataType<UserHistoryData>, IDataType, IDataUpdateListener<UserHistoryData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<UserHistoryData> m_onUpdate;

			private UserHistoryKey _key;

			private ulong m_guild_id;

			private ulong m_user_id;

			private int m_history_id;

			private int m_history_key;

			private int m_history_value;

			private int m_latest_date;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<UserHistoryData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override ulong user_id
			{
				get
				{
					return 0uL;
				}
			}

			public override int history_id
			{
				get
				{
					return 0;
				}
			}

			public override int history_key
			{
				get
				{
					return 0;
				}
			}

			public override int history_value
			{
				get
				{
					return 0;
				}
			}

			public override int latest_date
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override UserHistoryKey GetKey()
			{
				return default(UserHistoryKey);
			}

			public void DeserializeData(user_history data, data allDatas)
			{
			}
		}

		public class UserSiegeWeaponDataImpl : UserSiegeWeaponData, IDataTypeImpl<UserSiegeWeaponData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<UserSiegeWeaponData>, IServerDataType<user_siege_weapon>, IDataType<UserSiegeWeaponData>, IDataType, IDataUpdateListener<UserSiegeWeaponData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<UserSiegeWeaponData> m_onUpdate;

			private UserSiegeWeaponKey _key;

			private ulong m_guild_id;

			private ulong m_user_id;

			private int m_p_siege_weapon;

			private int m_amount;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<UserSiegeWeaponData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override ulong user_id
			{
				get
				{
					return 0uL;
				}
			}

			public override int p_siege_weapon
			{
				get
				{
					return 0;
				}
			}

			public override int amount
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override UserSiegeWeaponKey GetKey()
			{
				return default(UserSiegeWeaponKey);
			}

			public void DeserializeData(user_siege_weapon data, data allDatas)
			{
			}
		}

		public class UserStatsDataImpl : UserStatsData, IDataTypeImpl<UserStatsData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<UserStatsData>, IServerDataType<user_stats>, IDataType<UserStatsData>, IDataType, IDataUpdateListener<UserStatsData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<UserStatsData> m_onUpdate;

			private UserStatsKey _key;

			private ulong m_guild_id;

			private ulong m_user_id;

			private int m_base_atk;

			private int m_base_def;

			private int m_base_inte;

			private int m_base_agi;

			private int m_base_mor;

			private int m_treasure_point;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<UserStatsData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override ulong user_id
			{
				get
				{
					return 0uL;
				}
			}

			public override int base_atk
			{
				get
				{
					return 0;
				}
			}

			public override int base_def
			{
				get
				{
					return 0;
				}
			}

			public override int base_inte
			{
				get
				{
					return 0;
				}
			}

			public override int base_agi
			{
				get
				{
					return 0;
				}
			}

			public override int base_mor
			{
				get
				{
					return 0;
				}
			}

			public override int treasure_point
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override UserStatsKey GetKey()
			{
				return default(UserStatsKey);
			}

			public void DeserializeData(user_stats data, data allDatas)
			{
			}
		}

		public class UserTradingPostDataImpl : UserTradingPostData, IDataTypeImpl<UserTradingPostData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<UserTradingPostData>, IServerDataType<user_trading_post>, IDataType<UserTradingPostData>, IDataType, IDataUpdateListener<UserTradingPostData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<UserTradingPostData> m_onUpdate;

			private UserTradingPostKey _key;

			private ulong m_guild_id;

			private ulong m_user_id;

			private int m_use_count;

			private int m_get_gold;

			private int m_use_gold;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<UserTradingPostData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override ulong user_id
			{
				get
				{
					return 0uL;
				}
			}

			public override int use_count
			{
				get
				{
					return 0;
				}
			}

			public override int get_gold
			{
				get
				{
					return 0;
				}
			}

			public override int use_gold
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override UserTradingPostKey GetKey()
			{
				return default(UserTradingPostKey);
			}

			public void DeserializeData(user_trading_post data, data allDatas)
			{
			}
		}

		public class VisionDataImpl : VisionData, IDataTypeImpl<VisionData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<VisionData>, IServerDataType<vision>, IDataType<VisionData>, IDataType, IDataUpdateListener<VisionData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<VisionData> m_onUpdate;

			private VisionKey _key;

			private ulong m_guild_id;

			private short m_pos_x;

			private short m_pos_y;

			private int m_vision_count;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<VisionData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override short pos_x
			{
				get
				{
					return 0;
				}
			}

			public override short pos_y
			{
				get
				{
					return 0;
				}
			}

			public override int vision_count
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override VisionKey GetKey()
			{
				return default(VisionKey);
			}

			public void DeserializeData(vision data, data allDatas)
			{
			}
		}

		public class VisionBuildingDataImpl : VisionBuildingData, IDataTypeImpl<VisionBuildingData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<VisionBuildingData>, IServerDataType<vision_building>, IDataType<VisionBuildingData>, IDataType, IDataUpdateListener<VisionBuildingData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<VisionBuildingData> m_onUpdate;

			private VisionBuildingKey _key;

			private int m_p_place_id;

			private short m_pos_x;

			private short m_pos_y;

			private ulong m_guild_id;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<VisionBuildingData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int p_place_id
			{
				get
				{
					return 0;
				}
			}

			public override short pos_x
			{
				get
				{
					return 0;
				}
			}

			public override short pos_y
			{
				get
				{
					return 0;
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override VisionBuildingKey GetKey()
			{
				return default(VisionBuildingKey);
			}

			public void DeserializeData(vision_building data, data allDatas)
			{
			}
		}

		public class VisionCorpsDataImpl : VisionCorpsData, IDataTypeImpl<VisionCorpsData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<VisionCorpsData>, IServerDataType<vision_corps>, IDataType<VisionCorpsData>, IDataType, IDataUpdateListener<VisionCorpsData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<VisionCorpsData> m_onUpdate;

			private VisionCorpsKey _key;

			private int m_corps_id;

			private short m_pos_x;

			private short m_pos_y;

			private ulong m_guild_id;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<VisionCorpsData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int corps_id
			{
				get
				{
					return 0;
				}
			}

			public override short pos_x
			{
				get
				{
					return 0;
				}
			}

			public override short pos_y
			{
				get
				{
					return 0;
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public override VisionCorpsKey GetKey()
			{
				return default(VisionCorpsKey);
			}

			public void DeserializeData(vision_corps data, data allDatas)
			{
			}
		}

		public class WorldDataImpl : WorldData, IDataTypeImpl<WorldData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<WorldData>, IServerDataType<world>, IDataType<WorldData>, IDataType, IDataUpdateListener<WorldData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<WorldData> m_onUpdate;

			private short m_clan_db_shard;

			private int m_world_id;

			private int m_instance_index;

			private int m_latest_corps_id;

			private int m_status;

			private int m_season_key;

			private int m_season_detail_key;

			private int m_next_season_detail_key;

			private int m_p_battle_world_id;

			private int m_p_guildwar_town_id;

			private int m_town_shard_index;

			private int m_town_grade;

			private int m_reward_grade;

			private string m_map;

			private long m_play_start;

			private long m_play_end;

			private long m_expire_date;

			private int m_p_victory_place_id;

			private ulong m_victory_guild_id;

			private Action<DirectionCombatSourceData> m_onDirectionCombatSource;

			private Action<DirectionCombatTargetData> m_onDirectionCombatTarget;

			private Action<DirectionChangeHpData> m_onDirectionChangeHp;

			private Action<DirectionCorpsMovedData> m_onDirectionCorpsMoved;

			private Action<DirectionCombatMessageData> m_onDirectionCombatMessage;

			private Action<DirectionPingData> m_onDirectionPing;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<WorldData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override short clan_db_shard
			{
				get
				{
					return 0;
				}
			}

			public override int world_id
			{
				get
				{
					return 0;
				}
			}

			public override int instance_index
			{
				get
				{
					return 0;
				}
			}

			public override int latest_corps_id
			{
				get
				{
					return 0;
				}
			}

			public override int status
			{
				get
				{
					return 0;
				}
			}

			public override int season_key
			{
				get
				{
					return 0;
				}
			}

			public override int season_detail_key
			{
				get
				{
					return 0;
				}
			}

			public override int next_season_detail_key
			{
				get
				{
					return 0;
				}
			}

			public override int p_battle_world_id
			{
				get
				{
					return 0;
				}
			}

			public override int p_guildwar_town_id
			{
				get
				{
					return 0;
				}
			}

			public override int town_shard_index
			{
				get
				{
					return 0;
				}
			}

			public override int town_grade
			{
				get
				{
					return 0;
				}
			}

			public override int reward_grade
			{
				get
				{
					return 0;
				}
			}

			public override string map
			{
				get
				{
					return null;
				}
			}

			public override long play_start
			{
				get
				{
					return 0L;
				}
			}

			public override long play_end
			{
				get
				{
					return 0L;
				}
			}

			public override long expire_date
			{
				get
				{
					return 0L;
				}
			}

			public override int p_victory_place_id
			{
				get
				{
					return 0;
				}
			}

			public override ulong victory_guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override Action<DirectionCombatSourceData> onDirectionCombatSource
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<DirectionCombatTargetData> onDirectionCombatTarget
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<DirectionChangeHpData> onDirectionChangeHp
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<DirectionCorpsMovedData> onDirectionCorpsMoved
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<DirectionCombatMessageData> onDirectionCombatMessage
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<DirectionPingData> onDirectionPing
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public void DeserializeData(world data, data allDatas)
			{
			}
		}

		public class WorldSeqDataImpl : WorldSeqData, IDataTypeImpl<WorldSeqData>, IDataTableOwnerImpl, IClientDataOwnerImpl, IDataRefAble<WorldSeqData>, IServerDataType<world_seq>, IDataType<WorldSeqData>, IDataType, IDataUpdateListener<WorldSeqData>, IDataTableOwner
		{
			private ClientData m_clientData;

			private DataTables m_dataTables;

			private Action<WorldSeqData> m_onUpdate;

			private short m_clan_db_shard;

			private int m_world_id;

			private int m_instance_index;

			private int m_seq;

			private int m_tick;

			public override ClientData clientData
			{
				get
				{
					return null;
				}
			}

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<WorldSeqData> onUpdate
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override short clan_db_shard
			{
				get
				{
					return 0;
				}
			}

			public override int world_id
			{
				get
				{
					return 0;
				}
			}

			public override int instance_index
			{
				get
				{
					return 0;
				}
			}

			public override int seq
			{
				get
				{
					return 0;
				}
			}

			public override int tick
			{
				get
				{
					return 0;
				}
			}

			public void EmitUpdateEvent()
			{
			}

			public void InitClientData()
			{
			}

			public void UpdateClientData()
			{
			}

			public void DestroyClientData()
			{
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public void DeserializeData(world_seq data, data allDatas)
			{
			}
		}

		public class DirectionCombatSourceDataImpl : DirectionCombatSourceData, IDataTableOwnerImpl, IServerDataType<direction_combat_source>, IDataTableOwner
		{
			private DataTables m_dataTables;

			private int m_p_combat_action;

			private int m_source_place_id;

			private int m_source_corps_id;

			private short m_source_pos_x;

			private short m_source_pos_y;

			private ulong m_source_user_id;

			private short m_target_pos_x;

			private short m_target_pos_y;

			private ulong m_target_user_id;

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int p_combat_action
			{
				get
				{
					return 0;
				}
			}

			public override int source_place_id
			{
				get
				{
					return 0;
				}
			}

			public override int source_corps_id
			{
				get
				{
					return 0;
				}
			}

			public override short source_pos_x
			{
				get
				{
					return 0;
				}
			}

			public override short source_pos_y
			{
				get
				{
					return 0;
				}
			}

			public override ulong source_user_id
			{
				get
				{
					return 0uL;
				}
			}

			public override short target_pos_x
			{
				get
				{
					return 0;
				}
			}

			public override short target_pos_y
			{
				get
				{
					return 0;
				}
			}

			public override ulong target_user_id
			{
				get
				{
					return 0uL;
				}
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public void DeserializeData(direction_combat_source data, data allDatas)
			{
			}
		}

		public class DirectionCombatTargetDataImpl : DirectionCombatTargetData, IDataTableOwnerImpl, IServerDataType<direction_combat_target>, IDataTableOwner
		{
			private DataTables m_dataTables;

			private int m_p_combat_action;

			private int m_target_place_id;

			private int m_target_corps_id;

			private short m_target_tile_x;

			private short m_target_tile_y;

			private short m_target_pos_x;

			private short m_target_pos_y;

			private ulong m_target_user_id;

			private int m_source_place_id;

			private int m_source_corps_id;

			private short m_source_pos_x;

			private short m_source_pos_y;

			private ulong m_source_user_id;

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int p_combat_action
			{
				get
				{
					return 0;
				}
			}

			public override int target_place_id
			{
				get
				{
					return 0;
				}
			}

			public override int target_corps_id
			{
				get
				{
					return 0;
				}
			}

			public override short target_tile_x
			{
				get
				{
					return 0;
				}
			}

			public override short target_tile_y
			{
				get
				{
					return 0;
				}
			}

			public override short target_pos_x
			{
				get
				{
					return 0;
				}
			}

			public override short target_pos_y
			{
				get
				{
					return 0;
				}
			}

			public override ulong target_user_id
			{
				get
				{
					return 0uL;
				}
			}

			public override int source_place_id
			{
				get
				{
					return 0;
				}
			}

			public override int source_corps_id
			{
				get
				{
					return 0;
				}
			}

			public override short source_pos_x
			{
				get
				{
					return 0;
				}
			}

			public override short source_pos_y
			{
				get
				{
					return 0;
				}
			}

			public override ulong source_user_id
			{
				get
				{
					return 0uL;
				}
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public void DeserializeData(direction_combat_target data, data allDatas)
			{
			}
		}

		public class DirectionChangeHpDataImpl : DirectionChangeHpData, IDataTableOwnerImpl, IServerDataType<direction_change_hp>, IDataTableOwner
		{
			private DataTables m_dataTables;

			private int m_target_place_id;

			private int m_target_corps_id;

			private short m_target_pos_x;

			private short m_target_pos_y;

			private ulong m_target_user_id;

			private short m_source_pos_x;

			private short m_source_pos_y;

			private ulong m_source_user_id;

			private int m_before_hp;

			private int m_after_hp;

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override int target_place_id
			{
				get
				{
					return 0;
				}
			}

			public override int target_corps_id
			{
				get
				{
					return 0;
				}
			}

			public override short target_pos_x
			{
				get
				{
					return 0;
				}
			}

			public override short target_pos_y
			{
				get
				{
					return 0;
				}
			}

			public override ulong target_user_id
			{
				get
				{
					return 0uL;
				}
			}

			public override short source_pos_x
			{
				get
				{
					return 0;
				}
			}

			public override short source_pos_y
			{
				get
				{
					return 0;
				}
			}

			public override ulong source_user_id
			{
				get
				{
					return 0uL;
				}
			}

			public override int before_hp
			{
				get
				{
					return 0;
				}
			}

			public override int after_hp
			{
				get
				{
					return 0;
				}
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public void DeserializeData(direction_change_hp data, data allDatas)
			{
			}
		}

		public class DirectionCorpsMovedDataImpl : DirectionCorpsMovedData, IDataTableOwnerImpl, IServerDataType<direction_corps_moved>, IDataTableOwner
		{
			private DataTables m_dataTables;

			private ulong m_moved_user_id;

			private int m_moved_corps_id;

			private short m_before_pos_x;

			private short m_before_pos_y;

			private short m_after_pos_x;

			private short m_after_pos_y;

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override ulong moved_user_id
			{
				get
				{
					return 0uL;
				}
			}

			public override int moved_corps_id
			{
				get
				{
					return 0;
				}
			}

			public override short before_pos_x
			{
				get
				{
					return 0;
				}
			}

			public override short before_pos_y
			{
				get
				{
					return 0;
				}
			}

			public override short after_pos_x
			{
				get
				{
					return 0;
				}
			}

			public override short after_pos_y
			{
				get
				{
					return 0;
				}
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public void DeserializeData(direction_corps_moved data, data allDatas)
			{
			}
		}

		public class DirectionCombatMessageDataImpl : DirectionCombatMessageData, IDataTableOwnerImpl, IServerDataType<direction_combat_message>, IDataTableOwner
		{
			private DataTables m_dataTables;

			private ulong m_guild_id;

			private int m_p_combat_message;

			private int m_custom_key;

			private string m_message_params_guild;

			private string m_message_params_other;

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override int p_combat_message
			{
				get
				{
					return 0;
				}
			}

			public override int custom_key
			{
				get
				{
					return 0;
				}
			}

			public override string message_params_guild
			{
				get
				{
					return null;
				}
			}

			public override string message_params_other
			{
				get
				{
					return null;
				}
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public void DeserializeData(direction_combat_message data, data allDatas)
			{
			}
		}

		public class DirectionPingDataImpl : DirectionPingData, IDataTableOwnerImpl, IServerDataType<direction_ping>, IDataTableOwner
		{
			private DataTables m_dataTables;

			private ulong m_guild_id;

			private ulong m_user_id;

			private int m_ping_id;

			private short m_pos_x;

			private short m_pos_y;

			public DataTables dataTables
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override ulong guild_id
			{
				get
				{
					return 0uL;
				}
			}

			public override ulong user_id
			{
				get
				{
					return 0uL;
				}
			}

			public override int ping_id
			{
				get
				{
					return 0;
				}
			}

			public override short pos_x
			{
				get
				{
					return 0;
				}
			}

			public override short pos_y
			{
				get
				{
					return 0;
				}
			}

			public override DataTables GetDataTables()
			{
				return null;
			}

			public void DeserializeData(direction_ping data, data allDatas)
			{
			}
		}

		public class DeserializeContext
		{
			public HashSet<IClientDataOwnerImpl> modifiedDatas;

			public HashSet<IDataType> eventDatas;

			public Action eventActions;

			public static Dictionary<object, Action> dataRefUpdateCallbacks;

			public static Dictionary<object, Dictionary<object, Action>> dictionaryRefUpdateCallbacks;

			public static Dictionary<object, Dictionary<object, Action>> listRefUpdateCallbacks;
		}

		public class DataTableImpl<KeyType, DataType, ClientDataType, FlatBufferDataType, FlatBufferKeyType> : DataTable<KeyType, DataType> where KeyType : struct, IKeyTypeDeserializable<KeyType, FlatBufferDataType, FlatBufferKeyType> where DataType : class, IDataTableOwner, IDataType, IKeyOwner<KeyType>, IDataRefAble<DataType> where ClientDataType : class, IDataTableOwnerImpl, IDataRefAble<DataType>, IClientDataOwnerImpl, IServerDataType<FlatBufferDataType>, DataType, new() where FlatBufferDataType : struct, IFlatbufferObject where FlatBufferKeyType : struct, IFlatbufferObject
		{
			public struct Enumerator : IEnumerator, IDisposable, IEnumerator<KeyValuePair<KeyType, DataType>>
			{
				private Dictionary<KeyType, ClientDataType> m_key2Data;

				private Dictionary<KeyType, ClientDataType>.Enumerator m_originEnumerator;

				private KeyValuePair<KeyType, DataType> m_current;

				object IEnumerator.Current
				{
					get
					{
						return null;
					}
				}

				public KeyValuePair<KeyType, DataType> Current
				{
					get
					{
						return default(KeyValuePair<KeyType, DataType>);
					}
				}

				public Enumerator(DataTableImpl<KeyType, DataType, ClientDataType, FlatBufferDataType, FlatBufferKeyType> dataTable)
				{
					m_key2Data = null;
					m_originEnumerator = default(Dictionary<KeyType, ClientDataType>.Enumerator);
					m_current = default(KeyValuePair<KeyType, DataType>);
				}

				public void Dispose()
				{
				}

				public bool MoveNext()
				{
					return false;
				}

				public void Reset()
				{
				}
			}

			private Dictionary<KeyType, ClientDataType> m_key2ClientData;

			// C# has no syntax for parameterized property 'Item'.
			public override DataType get_Item(string stringKey)
			{
				return null;
			}

			// C# has no syntax for parameterized property 'Item'.
			public override DataType get_Item(KeyType kType)
			{
				return null;
			}

			public override int Count
			{
				get
				{
					return 0;
				}
			}

			public override IEnumerator<KeyValuePair<KeyType, DataType>> GetEnumerator()
			{
				return null;
			}

			public static string GetStringKey(KeyType keyType)
			{
				return null;
			}

			public static KeyType GetKeyWithStringKey(string stringKey)
			{
				return default(KeyType);
			}

			public override DataType GetData(string stringKey)
			{
				return null;
			}

			public override DataType GetData(KeyType kType)
			{
				return null;
			}

			public ClientDataType GetClientData(string stringKey)
			{
				return null;
			}

			public ClientDataType GetClientData(KeyType kType)
			{
				return null;
			}

			public override bool TryGetValue(string stringKey, out DataType value)
			{
				value = null;
				return false;
			}

			public override bool TryGetValue(KeyType key, out DataType value)
			{
				value = null;
				return false;
			}

			public void Clear(DeserializeContext context)
			{
			}

			private static KeyType DeserializeKey(FlatBufferKeyType data)
			{
				return default(KeyType);
			}

			public void DeserializeSnapshot(DataTables dataTables, DeserializeContext context, List<FlatBufferDataType> datas, data allDatas)
			{
			}

			public void DeserializeDelete(DataTables dataTables, DeserializeContext context, FlatBufferKeyType data)
			{
			}

			public void DeserializeDiff(DataTables dataTables, DeserializeContext context, FlatBufferDataType data, data allDatas)
			{
			}
		}

		public class DataTablesImpl : DataTablesOriginImpl
		{
			public class CustomDataParser
			{
				public delegate void JsonParser(DataTablesImpl self, DeserializeContext context, data allDatas);

				public delegate void Clear(DataTablesImpl self, DeserializeContext context);

				public JsonParser snapshotParser;

				public JsonParser oneStepParser;

				public Clear clear;
			}

			private string m_mapName;

			protected static Dictionary<string, CustomDataParser> s_customParsers;

			private WorldDataImpl m_worldData;

			private WorldSeqDataImpl m_worldSeqData;

			private TileDataTableImpl m_tileDataTables;

			private CameraDataImpl m_cameraData;

			private ClientVisionDataImpl m_clientVisionData;

			private TileCandidatesDataImpl m_tileCandidatesData;

			private int m_worldSeq;

			public override WorldData worldData
			{
				get
				{
					return null;
				}
			}

			public override WorldSeqData worldSeqData
			{
				get
				{
					return null;
				}
			}

			public override DataTable<TileKey, TileData> tileDataTables
			{
				get
				{
					return null;
				}
			}

			public override CameraData cameraData
			{
				get
				{
					return null;
				}
			}

			public override ClientVisionData clientVisionData
			{
				get
				{
					return null;
				}
			}

			public override TileCandidatesData tileCandidatesData
			{
				get
				{
					return null;
				}
			}

			static DataTablesImpl()
			{
			}

			public DataTablesImpl(DeserializeContext context, string mapName)
			{
			}

			~DataTablesImpl()
			{
			}

			public override string GetMapName()
			{
				return null;
			}

			public void DeserializeSnapshot(DeserializeContext context, data allDatas)
			{
			}

			public void DeserializeOneStep(DeserializeContext context, data allDatas)
			{
			}

			public override int GetClanDbShard()
			{
				return 0;
			}

			public override int GetWorldId()
			{
				return 0;
			}

			public override int GetSeq()
			{
				return 0;
			}

			public override void Clear(DeserializeContext context)
			{
			}
		}

		public abstract class DataTablesOriginImpl : DataTables
		{
			private DataTableImpl<BuildingKey, BuildingData, BuildingDataImpl, building, key_building> m_buildingDataTables;

			private DataTableImpl<BuildingCustomFunctionKey, BuildingCustomFunctionData, BuildingCustomFunctionDataImpl, building_custom_function, key_building_custom_function> m_buildingCustomFunctionDataTables;

			private DataTableImpl<BuildingHistoryKey, BuildingHistoryData, BuildingHistoryDataImpl, building_history, key_building_history> m_buildingHistoryDataTables;

			private DataTableImpl<BuildingPassiveKey, BuildingPassiveData, BuildingPassiveDataImpl, building_passive, key_building_passive> m_buildingPassiveDataTables;

			private DataTableImpl<BuildingPassiveDamageKey, BuildingPassiveDamageData, BuildingPassiveDamageDataImpl, building_passive_damage, key_building_passive_damage> m_buildingPassiveDamageDataTables;

			private DataTableImpl<CorpsKey, CorpsData, CorpsDataImpl, corps, key_corps> m_corpsDataTables;

			private DataTableImpl<CorpsCustomFunctionKey, CorpsCustomFunctionData, CorpsCustomFunctionDataImpl, corps_custom_function, key_corps_custom_function> m_corpsCustomFunctionDataTables;

			private DataTableImpl<CorpsFunctionKey, CorpsFunctionData, CorpsFunctionDataImpl, corps_function, key_corps_function> m_corpsFunctionDataTables;

			private DataTableImpl<CorpsHistoryKey, CorpsHistoryData, CorpsHistoryDataImpl, corps_history, key_corps_history> m_corpsHistoryDataTables;

			private DataTableImpl<CorpsInformationKey, CorpsInformationData, CorpsInformationDataImpl, corps_information, key_corps_information> m_corpsInformationDataTables;

			private DataTableImpl<CorpsPassiveKey, CorpsPassiveData, CorpsPassiveDataImpl, corps_passive, key_corps_passive> m_corpsPassiveDataTables;

			private DataTableImpl<CorpsPassiveDamageKey, CorpsPassiveDamageData, CorpsPassiveDamageDataImpl, corps_passive_damage, key_corps_passive_damage> m_corpsPassiveDamageDataTables;

			private DataTableImpl<CorpsSiegeWeaponKey, CorpsSiegeWeaponData, CorpsSiegeWeaponDataImpl, corps_siege_weapon, key_corps_siege_weapon> m_corpsSiegeWeaponDataTables;

			private DataTableImpl<GuildKey, GuildData, GuildDataImpl, guild, key_guild> m_guildDataTables;

			private DataTableImpl<GuildCorpsPassiveKey, GuildCorpsPassiveData, GuildCorpsPassiveDataImpl, guild_corps_passive, key_guild_corps_passive> m_guildCorpsPassiveDataTables;

			private DataTableImpl<GuildCustomFunctionKey, GuildCustomFunctionData, GuildCustomFunctionDataImpl, guild_custom_function, key_guild_custom_function> m_guildCustomFunctionDataTables;

			private DataTableImpl<GuildFunctionKey, GuildFunctionData, GuildFunctionDataImpl, guild_function, key_guild_function> m_guildFunctionDataTables;

			private DataTableImpl<GuildHistoryKey, GuildHistoryData, GuildHistoryDataImpl, guild_history, key_guild_history> m_guildHistoryDataTables;

			private DataTableImpl<GuildMemberGradeKey, GuildMemberGradeData, GuildMemberGradeDataImpl, guild_member_grade, key_guild_member_grade> m_guildMemberGradeDataTables;

			private DataTableImpl<TileEffectKey, TileEffectData, TileEffectDataImpl, tile_effect, key_tile_effect> m_tileEffectDataTables;

			private DataTableImpl<UserKey, UserData, UserDataImpl, user, key_user> m_userDataTables;

			private DataTableImpl<UserCorpsKey, UserCorpsData, UserCorpsDataImpl, user_corps, key_user_corps> m_userCorpsDataTables;

			private DataTableImpl<UserHistoryKey, UserHistoryData, UserHistoryDataImpl, user_history, key_user_history> m_userHistoryDataTables;

			private DataTableImpl<UserSiegeWeaponKey, UserSiegeWeaponData, UserSiegeWeaponDataImpl, user_siege_weapon, key_user_siege_weapon> m_userSiegeWeaponDataTables;

			private DataTableImpl<UserStatsKey, UserStatsData, UserStatsDataImpl, user_stats, key_user_stats> m_userStatsDataTables;

			private DataTableImpl<UserTradingPostKey, UserTradingPostData, UserTradingPostDataImpl, user_trading_post, key_user_trading_post> m_userTradingPostDataTables;

			private DataTableImpl<VisionKey, VisionData, VisionDataImpl, vision, key_vision> m_visionDataTables;

			private DataTableImpl<VisionBuildingKey, VisionBuildingData, VisionBuildingDataImpl, vision_building, key_vision_building> m_visionBuildingDataTables;

			private DataTableImpl<VisionCorpsKey, VisionCorpsData, VisionCorpsDataImpl, vision_corps, key_vision_corps> m_visionCorpsDataTables;

			private DirectionParser<DirectionCombatSourceData, DirectionCombatSourceDataImpl, direction_combat_source> m_onDirectionCombatSource;

			private DirectionParser<DirectionCombatTargetData, DirectionCombatTargetDataImpl, direction_combat_target> m_onDirectionCombatTarget;

			private DirectionParser<DirectionChangeHpData, DirectionChangeHpDataImpl, direction_change_hp> m_onDirectionChangeHp;

			private DirectionParser<DirectionCorpsMovedData, DirectionCorpsMovedDataImpl, direction_corps_moved> m_onDirectionCorpsMoved;

			private DirectionParser<DirectionCombatMessageData, DirectionCombatMessageDataImpl, direction_combat_message> m_onDirectionCombatMessage;

			private DirectionParser<DirectionPingData, DirectionPingDataImpl, direction_ping> m_onDirectionPing;

			public override DataTable<BuildingKey, BuildingData> buildingDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<BuildingCustomFunctionKey, BuildingCustomFunctionData> buildingCustomFunctionDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<BuildingHistoryKey, BuildingHistoryData> buildingHistoryDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<BuildingPassiveKey, BuildingPassiveData> buildingPassiveDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<BuildingPassiveDamageKey, BuildingPassiveDamageData> buildingPassiveDamageDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<CorpsKey, CorpsData> corpsDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<CorpsCustomFunctionKey, CorpsCustomFunctionData> corpsCustomFunctionDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<CorpsFunctionKey, CorpsFunctionData> corpsFunctionDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<CorpsHistoryKey, CorpsHistoryData> corpsHistoryDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<CorpsInformationKey, CorpsInformationData> corpsInformationDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<CorpsPassiveKey, CorpsPassiveData> corpsPassiveDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<CorpsPassiveDamageKey, CorpsPassiveDamageData> corpsPassiveDamageDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<CorpsSiegeWeaponKey, CorpsSiegeWeaponData> corpsSiegeWeaponDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<GuildKey, GuildData> guildDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<GuildCorpsPassiveKey, GuildCorpsPassiveData> guildCorpsPassiveDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<GuildCustomFunctionKey, GuildCustomFunctionData> guildCustomFunctionDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<GuildFunctionKey, GuildFunctionData> guildFunctionDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<GuildHistoryKey, GuildHistoryData> guildHistoryDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<GuildMemberGradeKey, GuildMemberGradeData> guildMemberGradeDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<TileEffectKey, TileEffectData> tileEffectDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<UserKey, UserData> userDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<UserCorpsKey, UserCorpsData> userCorpsDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<UserHistoryKey, UserHistoryData> userHistoryDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<UserSiegeWeaponKey, UserSiegeWeaponData> userSiegeWeaponDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<UserStatsKey, UserStatsData> userStatsDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<UserTradingPostKey, UserTradingPostData> userTradingPostDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<VisionKey, VisionData> visionDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<VisionBuildingKey, VisionBuildingData> visionBuildingDataTables
			{
				get
				{
					return null;
				}
			}

			public override DataTable<VisionCorpsKey, VisionCorpsData> visionCorpsDataTables
			{
				get
				{
					return null;
				}
			}

			public override Action<DirectionCombatSourceData> onDirectionCombatSource
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<DirectionCombatTargetData> onDirectionCombatTarget
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<DirectionChangeHpData> onDirectionChangeHp
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<DirectionCorpsMovedData> onDirectionCorpsMoved
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<DirectionCombatMessageData> onDirectionCombatMessage
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			public override Action<DirectionPingData> onDirectionPing
			{
				get
				{
					return null;
				}
				set
				{
				}
			}

			protected void DeserializeSnapshotImpl(DeserializeContext context, data allDatas)
			{
			}

			protected void DeserializeOneStepImpl(DeserializeContext context, data allDatas)
			{
			}

			public virtual void Clear(DeserializeContext context)
			{
			}
		}

		private class ConnectionInfoImpl : ConnectionInfo
		{
			public ObscuredString m_socketServerUuid;

			public ObscuredString m_socketServerIP;

			public ObscuredInt m_socketServerPort;

			public ObscuredString m_logicServerUuid;

			public ObscuredString m_logicServerIP;

			public ObscuredInt m_logicServerPort;

			public ObscuredString m_distributorServerUuid;

			public ObscuredString m_distributorServerIP;

			public ObscuredInt m_distributorServerPort;

			public ObscuredULong m_userId;

			public override string socketServerUuid
			{
				get
				{
					return m_socketServerUuid;
				}
			}

			public override string socketServerIP
			{
				get
				{
					return m_socketServerIP;
				}
			}

			public override int socketServerPort
			{
				get
				{
					return m_socketServerPort;
				}
			}

			public override string logicServerUuid
			{
				get
				{
					return m_logicServerUuid;
				}
			}

			public override string logicServerIP
			{
				get
				{
					return m_logicServerIP;
				}
			}

			public override int logicServerPort
			{
				get
				{
					return m_logicServerPort;
				}
			}

			public override string distributorServerUuid
			{
				get
				{
					return m_distributorServerUuid;
				}
			}

			public override string distributorServerIP
			{
				get
				{
					return m_distributorServerIP;
				}
			}

			public override int distributorServerPort
			{
				get
				{
					return m_userId;
				}
			}

			public override ulong userId
			{
				get
				{
					return m_userId;
				}
			}
		}

		private class ConnectionInfoHash : IEqualityComparer<ConnectionInfoImpl>
		{
			public bool Equals(ConnectionInfoImpl x, ConnectionInfoImpl y)
			{
				return false;
			}

			public int GetHashCode(ConnectionInfoImpl obj)
			{
				return 0;
			}
		}

		private class ScheduleDataImpl : ScheduleData
		{
			private ObscuredInt m_season_detail_key;

			private ObscuredBool m_is_created;

			private DateTime m_season_end;

			private DateTime m_attend_start;

			private DateTime m_attend_end;

			private DateTime m_play_start;

			private DateTime m_play_end;

			public override int season_detail_key
			{
				get
				{
					return 0;
				}
			}

			public override bool is_created
			{
				get
				{
					return false;
				}
			}

			public override DateTime season_end
			{
				get
				{
					return default(DateTime);
				}
			}

			public override DateTime attend_start
			{
				get
				{
					return default(DateTime);
				}
			}

			public override DateTime attend_end
			{
				get
				{
					return default(DateTime);
				}
			}

			public override DateTime play_start
			{
				get
				{
					return default(DateTime);
				}
			}

			public override DateTime play_end
			{
				get
				{
					return default(DateTime);
				}
			}

			protected override void DeserializeImpl(JsonData jsonData)
			{
			}
		}

		[StructLayout((LayoutKind)0, Size = 8)]
		public struct RoomKey
		{
			public int clan_db_shard;

			public int world_id;
		}

		// [RE] SessionImpl rebuilt from decompiled PImpl.SessionImpl
		// (TypeDefIndex 8378; field offsets verified in dump.cs). Socket flow:
		// CreateSocket → OnSocketConnect → UpdateSocketLogin(["login",{userId,authKey}])
		// → OnSocketLogined → EnterSocketServerWorldRoom(["enterToWorld",...]).
		// REST authKey: POST /user/login (rebuild) — original: /chat/getAuthKey.
		private class SessionImpl : Session
		{
			public GameObject m_gameObject;

			public readonly ConnectionInfoImpl m_connectionInfo;

			private Action onSocketConnected__BackingField;

			private Action onSocketDisconnected__BackingField;

			private Action onSocketLogined__BackingField;

			private Action onSocketEnteredToWorld__BackingField;

			private Action onSocketLeavedFromWorld__BackingField;

			private Action<int, int, JsonData> onSocketWorldHistoryUpdated__BackingField;

			private Action<SocketIOEvent> onSocketErrored__BackingField;

			private ObscuredString m_authKey;

			private bool m_webGettingAuthKey;

			private float m_getAuthTimer;

			private float m_authKeyValidationTimer;

			public SocketIOComponent m_socket;

			public ObscuredInt m_userCnt;

			public ObscuredBool m_maintenance;

			private float m_socketConnectTimer;

			public bool m_socketConnecting;

			public bool m_socketConnected;

			private float m_socketLoginTimer;

			private bool m_needToReLoginSocketServer;

			private bool m_socketLoggingIn;

			private bool m_socketLoggedIn;

			private float m_socketWorldRoomTimer;

			private RoomKey m_socketHaveToEnterWorldRoom;

			private RoomKey m_socketHaveToLeaveWorldRoom;

			private RoomKey m_socketEnteringWorldRoom;

			private RoomKey m_socketLeavingWorldRoom;

			private RoomKey m_socketEnteredWorldRoom;

			public override ConnectionInfo connectionInfo
			{
				get
				{
					return m_connectionInfo;
				}
			}

			public override bool socketConnected
			{
				get
				{
					return m_socketConnected && m_socketLoggedIn;
				}
			}

			public override bool socketLogined
			{
				get
				{
					return m_socketLoggedIn;
				}
			}

			public override string authKey
			{
				get
				{
					return m_authKey;
				}
			}

			public override int userCnt
			{
				get
				{
					return m_userCnt;
				}
			}

			public override bool maintenance
			{
				get
				{
					return m_maintenance;
				}
			}

			public event Action onSocketConnected
			{
				add
				{
					onSocketConnected__BackingField = (Action)Delegate.Combine(onSocketConnected__BackingField, value);
				}
				remove
				{
					onSocketConnected__BackingField = (Action)Delegate.Remove(onSocketConnected__BackingField, value);
				}
			}

			public event Action onSocketDisconnected
			{
				add
				{
					onSocketDisconnected__BackingField = (Action)Delegate.Combine(onSocketDisconnected__BackingField, value);
				}
				remove
				{
					onSocketDisconnected__BackingField = (Action)Delegate.Remove(onSocketDisconnected__BackingField, value);
				}
			}

			public event Action onSocketLogined
			{
				add
				{
					onSocketLogined__BackingField = (Action)Delegate.Combine(onSocketLogined__BackingField, value);
				}
				remove
				{
					onSocketLogined__BackingField = (Action)Delegate.Remove(onSocketLogined__BackingField, value);
				}
			}

			public event Action onSocketEnteredToWorld
			{
				add
				{
					onSocketEnteredToWorld__BackingField = (Action)Delegate.Combine(onSocketEnteredToWorld__BackingField, value);
				}
				remove
				{
					onSocketEnteredToWorld__BackingField = (Action)Delegate.Remove(onSocketEnteredToWorld__BackingField, value);
				}
			}

			public event Action onSocketLeavedFromWorld
			{
				add
				{
					onSocketLeavedFromWorld__BackingField = (Action)Delegate.Combine(onSocketLeavedFromWorld__BackingField, value);
				}
				remove
				{
					onSocketLeavedFromWorld__BackingField = (Action)Delegate.Remove(onSocketLeavedFromWorld__BackingField, value);
				}
			}

			public event Action<int, int, JsonData> onSocketWorldHistoryUpdated
			{
				add
				{
					onSocketWorldHistoryUpdated__BackingField = (Action<int, int, JsonData>)Delegate.Combine(onSocketWorldHistoryUpdated__BackingField, value);
				}
				remove
				{
					onSocketWorldHistoryUpdated__BackingField = (Action<int, int, JsonData>)Delegate.Remove(onSocketWorldHistoryUpdated__BackingField, value);
				}
			}

			public event Action<SocketIOEvent> onSocketErrored
			{
				add
				{
					onSocketErrored__BackingField = (Action<SocketIOEvent>)Delegate.Combine(onSocketErrored__BackingField, value);
				}
				remove
				{
					onSocketErrored__BackingField = (Action<SocketIOEvent>)Delegate.Remove(onSocketErrored__BackingField, value);
				}
			}

			public SessionImpl(ConnectionInfoImpl connectionInfo)
			{
				m_connectionInfo = connectionInfo;
				m_authKey = string.Empty;
				m_gameObject = new GameObject("SessionImpl");
				UnityEngine.Object.DontDestroyOnLoad(m_gameObject);
			}

			// [RE] REST payload convention: [user_id, ...args] (decompiled
			// addUserIdObjects used by every Send).
			private object[] addUserIdObjects(params object[] args)
			{
				var all = new object[args.Length + 1];
				all[0] = m_connectionInfo.userId;
				for (int i = 0; i < args.Length; i++)
					all[i + 1] = args[i];
				return all;
			}

			public void Send(string uri, string method, bool showNetworkIndicator, HttpRequest.ResponseCallback callback, params object[] args)
			{
				HttpRequest.instance.Send(uri, method, showNetworkIndicator, callback, addUserIdObjects(args));
			}

			[DebuggerHidden]
			public IEnumerator SendAndWait(string uri, string method, bool showNetworkIndicator, HttpRequest.ResponseCallback callback, params object[] args)
			{
				// [RE] original wraps Send + waits for the response callback
				bool done = false;
				JsonData result = null;
				HttpRequest.instance.Send(uri, method, showNetworkIndicator, delegate(JsonData jd)
				{
					result = jd;
					done = true;
				}, addUserIdObjects(args));
				while (!done)
					yield return null;
				yield return result;
			}

			public void RequestGetWorldData(int clan_db_shard, int world_id, int from_sequence, int to_sequence, List<int> except_sequences, HttpRequest.ResponseCallback callback)
			{
				// [RE] POST /guildWar/getWorldData with the sequence-window args
				HttpRequest.instance.Send("/guildWar/getWorldData", "POST", true, callback,
					new object[] { clan_db_shard, world_id, from_sequence, to_sequence, except_sequences });
			}

			public void Update()
			{
				UpdateWebAuthKey();
				UpdateSocketConnect();
				UpdateSocketLogin();
				UpdateSocketWorldRooms();
			}

			// [RE] UpdateWebAuthKey → REST auth; original hit /chat/getAuthKey
			// (literal `_chat_getAuthKey`), rebuild hits /user/login then keeps
			// the returned authKey. Re-fetch every m_authKeyValidationTimer drift.
			private void UpdateWebAuthKey()
			{
				if (m_webGettingAuthKey || !string.IsNullOrEmpty(m_authKey))
					return;
				m_getAuthTimer += Time.deltaTime;
				if (m_getAuthTimer < 2f)
					return;
				m_getAuthTimer = 0f;
				m_webGettingAuthKey = true;
				HttpRequest.instance.Send("/user/login", "POST", false, delegate(JsonData response)
				{
					m_webGettingAuthKey = false;
					if (response != null && response.Keys.Contains("authKey"))
					{
						m_authKey = response["authKey"].ToString();
						m_needToReLoginSocketServer = true;
					}
			}, new object[] { m_connectionInfo.userId });
			}

			private void OnWebGetAuthKey(JsonData response)
			{
				m_webGettingAuthKey = false;
				if (response != null && response.Keys.Contains("authKey"))
					m_authKey = response["authKey"].ToString();
			}

			private void DestroySocket()
			{
				if (m_socket != null)
				{
					m_socket.Close();
					UnityEngine.Object.Destroy(m_socket.gameObject);
					m_socket = null;
				}
				m_socketConnected = false;
				m_socketLoggedIn = false;
				m_socketConnecting = false;
				m_socketLoggingIn = false;
			}

			private void CreateSocket()
			{
				DestroySocket();
				// [RE] URL template literal: ws://{0}/socket.io/?EIO=4&transport=websocket
				var host = new Uri(ServerSetting.instance.GAME_SERVER_HOST_URL).Authority;
				var go = new GameObject("SocketIO");
				go.transform.SetParent(m_gameObject.transform, false);
				m_socket = go.AddComponent<SocketIOComponent>();
				m_socket.url = string.Format("ws://{0}/socket.io/?EIO=4&transport=websocket", host);
				m_socket.autoConnect = true;
				m_socket.On("connect", OnSocketConnect);
				m_socket.On("disconnect", OnSocketDisconnect);
				m_socket.On("error", OnSocketError);
				m_socket.On("login", OnSocketLogined);
				m_socket.On("enterToWorld", OnSocketEnterToWorld);
				m_socket.On("updateHistory", OnSocketUpdateHistory);
				m_socket.Connect();
				m_socketConnecting = true;
				m_socketConnectTimer = 0f;
			}

			private void OnSocketError(SocketIOEvent e)
			{
				if (onSocketErrored__BackingField != null)
					onSocketErrored__BackingField(e);
			}

			private void ResetSocketConnect()
			{
				m_socketConnecting = false;
				m_socketConnected = false;
				m_socketConnectTimer = 0f;
				m_needToReLoginSocketServer = true;
			}

			private void UpdateSocketConnect()
			{
				if (m_socket == null)
				{
					CreateSocket();
					return;
				}
				if (m_socketConnected)
					return;
				m_socketConnectTimer += Time.deltaTime;
				if (m_socketConnectTimer > 10f)
				{
					CreateSocket();   // [RE] original retried with backoff; 10s cadence
				}
			}

			private void OnSocketConnect(SocketIOEvent e)
			{
				// [RE] decompiled OnSocketConnect: reset world-room state, mark
				// connected, fire onSocketConnected, start login.
				m_socketConnected = true;
				m_socketConnecting = false;
				m_socketConnectTimer = 0f;
				ResetSocketWorldRooms();
				m_needToReLoginSocketServer = true;
				m_socketLoginTimer = 5f;   // [RE] decompiled: first login after 5s grace
				if (onSocketConnected__BackingField != null)
					onSocketConnected__BackingField();
			}

			private void OnSocketDisconnect(SocketIOEvent e)
			{
				m_socketConnected = false;
				m_socketLoggedIn = false;
				m_socketLoggingIn = false;
				ResetSocketWorldRooms();
				if (onSocketDisconnected__BackingField != null)
					onSocketDisconnected__BackingField();
			}

			private void ResetSocketLogin()
			{
				m_socketLoggingIn = false;
				m_socketLoggedIn = false;
				m_socketLoginTimer = 0f;
			}

			private void UpdateSocketLogin()
			{
				// [RE] faithful port of the decompiled UpdateSocketLogin:
				// waits for authKey, retries every 5s while un-logged.
				if (m_socketLoggedIn || m_socketLoggingIn || m_socket == null || !m_socketConnected)
					return;
				if (string.IsNullOrEmpty(m_authKey))
					return;
				m_socketLoginTimer += Time.deltaTime;
				if (m_socketLoginTimer < 5f)
					return;
				m_socketLoginTimer = 0f;
				m_socketLoggingIn = true;
				var payload = new JsonData();
				payload["userId"] = m_connectionInfo.userId;
				payload["authKey"] = m_authKey;
				m_socket.Emit("login", payload, delegate(JsonData ack)
				{
					m_socketLoggingIn = false;
					if (ack != null && !ack.Keys.Contains("error"))
					{
						m_socketLoggedIn = true;
						if (onSocketLogined__BackingField != null)
							onSocketLogined__BackingField();
					}
					else
					{
						m_needToReLoginSocketServer = true;
						OnWebGetAuthKey(null);   // force re-auth
					}
				});
			}

			private void OnSocketLogined(SocketIOEvent e)
			{
				m_socketLoggedIn = true;
				if (OnAnySessionLogined != null)
					OnAnySessionLogined(m_connectionInfo.userId);
				if (onSocketLogined__BackingField != null)
					onSocketLogined__BackingField();
			}

			public void EnterSocketServerWorldRoom(int clan_db_shard, int world_id)
			{
				m_socketHaveToEnterWorldRoom = new RoomKey { clan_db_shard = clan_db_shard, world_id = world_id };
			}

			public void LeaveSocketServerWorldRoom(int clan_db_shard, int world_id)
			{
				m_socketHaveToLeaveWorldRoom = new RoomKey { clan_db_shard = clan_db_shard, world_id = world_id };
			}

			private void ResetSocketWorldRooms()
			{
				m_socketEnteringWorldRoom = default(RoomKey);
				m_socketLeavingWorldRoom = default(RoomKey);
				m_socketEnteredWorldRoom = default(RoomKey);
			}

			private void UpdateSocketWorldRooms()
			{
				// [RE] original: one enter/leave in flight at a time.
				if (m_socketHaveToLeaveWorldRoom.world_id != 0 || m_socketHaveToLeaveWorldRoom.clan_db_shard != 0)
				{
					var leave = m_socketHaveToLeaveWorldRoom;
					m_socketHaveToLeaveWorldRoom = default(RoomKey);
					m_socketLeavingWorldRoom = leave;
					if (m_socket != null && m_socketConnected && m_socketLoggedIn)
						m_socket.Emit("leaveRoom", new JsonData { ["room_id"] = string.Format("world:{0}", leave.world_id) },
							delegate(JsonData ack)
							{
								m_socketLeavingWorldRoom = default(RoomKey);
								if (onSocketLeavedFromWorld__BackingField != null)
									onSocketLeavedFromWorld__BackingField();
							});
					return;
				}
				if ((m_socketHaveToEnterWorldRoom.world_id != 0 || m_socketHaveToEnterWorldRoom.clan_db_shard != 0) && m_socketConnected && m_socketLoggedIn)
				{
					var enter = m_socketHaveToEnterWorldRoom;
					m_socketHaveToEnterWorldRoom = default(RoomKey);
					m_socketEnteringWorldRoom = enter;
					if (m_socket != null)
						m_socket.Emit("enterToWorld", new JsonData { ["world_id"] = enter.world_id, ["guild_id"] = enter.clan_db_shard },
							delegate(JsonData ack)
							{
								m_socketEnteringWorldRoom = default(RoomKey);
								m_socketEnteredWorldRoom = enter;
								if (onSocketEnteredToWorld__BackingField != null)
									onSocketEnteredToWorld__BackingField();
							});
				}
			}

			private void OnSocketEnterToWorld(SocketIOEvent e)
			{
				m_socketEnteredWorldRoom = m_socketEnteringWorldRoom;
				m_socketEnteringWorldRoom = default(RoomKey);
				if (onSocketEnteredToWorld__BackingField != null)
					onSocketEnteredToWorld__BackingField();
			}

			private void OnSocketUpdateHistory(SocketIOEvent e)
			{
				// [RE] world history seq-window sync → data-table listeners
				int from = 0, to = 0;
				if (e.data != null && e.data.Keys.Contains("from_seq"))
					from = (int)e.data["from_seq"];
				if (e.data != null && e.data.Keys.Contains("to_seq"))
					to = (int)e.data["to_seq"];
				if (onSocketWorldHistoryUpdated__BackingField != null)
					onSocketWorldHistoryUpdated__BackingField(from, to, e.data);
			}

			public override int season_detail_key
			{
				get
				{
					return 0;
				}
			}

			public override int clan_db_shard
			{
				get
				{
					return m_socketEnteredWorldRoom.clan_db_shard;
				}
			}

			public override int world_id
			{
				get
				{
					return m_socketEnteredWorldRoom.world_id;
				}
			}

			public override int instance_index
			{
				get
				{
					return 0;
				}
			}

			public override WorldStatus status
			{
				get
				{
					return WorldStatus.NONE;
				}
			}

			public override string map
			{
				get
				{
					return string.Empty;
				}
			}

			public override string socket_server_uuid
			{
				get
				{
					return m_connectionInfo.socketServerUuid;
				}
			}

			public override string logic_server_uuid
			{
				get
				{
					return m_connectionInfo.logicServerUuid;
				}
			}

			public override string distributor_server_uuid
			{
				get
				{
					return m_connectionInfo.distributorServerUuid;
				}
			}

			public override string socket_server_ip
			{
				get
				{
					return m_connectionInfo.socketServerIP;
				}
			}

			public override string logic_server_ip
			{
				get
				{
					return m_connectionInfo.logicServerIP;
				}
			}

			public override string distributor_server_ip
			{
				get
				{
					return m_connectionInfo.distributorServerIP;
				}
			}

			public override int socket_server_port
			{
				get
				{
					return m_connectionInfo.socketServerPort;
				}
			}

			public override int logic_server_port
			{
				get
				{
					return m_connectionInfo.logicServerPort;
				}
			}

			public override int distributor_server_port
			{
				get
				{
					return m_connectionInfo.distributorServerPort;
				}
			}

			protected override void DeserializeImpl(JsonData jsonData)
			{
			}

			public void AddRequireSequence(int sequence, int requestIndex)
			{
			}

			public bool NeedGetWorldData()
			{
				return false;
			}

		}

				// [RE] rebuild-only local session factory. Original flow: NPA account
		// login → per-country server info (LiveJPN/LiveGLB...) → RequestSession.
		// Rebuild: LOCAL ServerSetting + guest device id → SessionImpl, driven
		// by an internal pump MonoBehaviour (original: Manager.Update() loop).
		private class SessionPump : MonoBehaviour
		{
			public SessionImpl session;

			private void Update()
			{
				if (session != null)
					session.Update();
			}
		}

		public static Session CreateLocalSession(ulong userId)
		{
			var info = new ConnectionInfoImpl();
			var hostUrl = ServerSetting.instance.GAME_SERVER_HOST_URL;
			int port = 80;
			try { port = new Uri(hostUrl).Port; } catch { }
			info.m_socketServerUuid = "local";
			info.m_socketServerIP = hostUrl;
			info.m_socketServerPort = port;
			info.m_logicServerUuid = "local";
			info.m_logicServerIP = hostUrl;
			info.m_logicServerPort = port;
			info.m_distributorServerUuid = "local";
			info.m_distributorServerIP = hostUrl;
			info.m_distributorServerPort = port;
			info.m_userId = userId;
			var go = new GameObject("SessionImpl");
			UnityEngine.Object.DontDestroyOnLoad(go);
			var impl = new SessionImpl(info);
			impl.m_gameObject = go;
			var pump = go.AddComponent<SessionPump>();
			pump.session = impl;
			return impl;
		}

		public static Manager CreateManager()
		{
			return null;
		}
	}
}
