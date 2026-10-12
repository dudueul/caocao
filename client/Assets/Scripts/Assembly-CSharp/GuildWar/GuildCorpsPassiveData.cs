using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class GuildCorpsPassiveData : IDataTableOwner, IDataType<GuildCorpsPassiveData>, IKeyOwner<GuildCorpsPassiveKey>, IClientDataOwner<GuildCorpsPassiveData.ClientData>, IDataRefAble<GuildCorpsPassiveData>, IDataType, IDataUpdateListener<GuildCorpsPassiveData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<GuildData> ownerGuild;
		}

		private HashSet<IDataRefInternal<GuildCorpsPassiveData>> m_dataRefs;

		public GuildData ownerGuild
		{
			get
			{
				return null;
			}
		}

		public abstract Action<GuildCorpsPassiveData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract ulong guild_id { get; }

		public abstract int passive_index { get; }

		public abstract int p_passive_list { get; }

		public abstract int passive_end_date { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<GuildCorpsPassiveKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract GuildCorpsPassiveKey GetKey();

		public void AddDataRef(IDataRefInternal<GuildCorpsPassiveData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<GuildCorpsPassiveData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
