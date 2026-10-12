using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class GuildFunctionData : IDataTableOwner, IDataType<GuildFunctionData>, IKeyOwner<GuildFunctionKey>, IClientDataOwner<GuildFunctionData.ClientData>, IDataRefAble<GuildFunctionData>, IDataType, IDataUpdateListener<GuildFunctionData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<GuildData> guild;
		}

		private HashSet<IDataRefInternal<GuildFunctionData>> m_dataRefs;

		public GuildData guild
		{
			get
			{
				return null;
			}
		}

		public abstract Action<GuildFunctionData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract ulong guild_id { get; }

		public abstract int p_guild_function_id { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<GuildFunctionKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract GuildFunctionKey GetKey();

		public void AddDataRef(IDataRefInternal<GuildFunctionData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<GuildFunctionData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
