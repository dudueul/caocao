using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class GuildCustomFunctionData : IDataTableOwner, IDataType<GuildCustomFunctionData>, IKeyOwner<GuildCustomFunctionKey>, IClientDataOwner<GuildCustomFunctionData.ClientData>, IDataRefAble<GuildCustomFunctionData>, IDataType, IDataUpdateListener<GuildCustomFunctionData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<GuildData> guild;
		}

		private HashSet<IDataRefInternal<GuildCustomFunctionData>> m_dataRefs;

		public GuildData guild
		{
			get
			{
				return null;
			}
		}

		public abstract Action<GuildCustomFunctionData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract ulong guild_id { get; }

		public abstract short custom_guild_action_type { get; }

		public abstract int cooldown_date { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<GuildCustomFunctionKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract GuildCustomFunctionKey GetKey();

		public void AddDataRef(IDataRefInternal<GuildCustomFunctionData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<GuildCustomFunctionData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
