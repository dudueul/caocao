using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class CorpsFunctionData : IDataTableOwner, IDataType<CorpsFunctionData>, IKeyOwner<CorpsFunctionKey>, IClientDataOwner<CorpsFunctionData.ClientData>, IDataRefAble<CorpsFunctionData>, IDataType, IDataUpdateListener<CorpsFunctionData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<CorpsData> corps;

			public GuildWarCombatActiveFunction p_function;
		}

		private HashSet<IDataRefInternal<CorpsFunctionData>> m_dataRefs;

		public CorpsData corps
		{
			get
			{
				return null;
			}
		}

		public GuildWarCombatActiveFunction p_function
		{
			get
			{
				return null;
			}
		}

		public abstract Action<CorpsFunctionData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract int corps_id { get; }

		public abstract int p_corps_function_id { get; }

		public abstract int available_date { get; }

		public abstract int available_duration { get; }

		public abstract int cooldown_date { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<CorpsFunctionKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract CorpsFunctionKey GetKey();

		public void AddDataRef(IDataRefInternal<CorpsFunctionData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<CorpsFunctionData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
