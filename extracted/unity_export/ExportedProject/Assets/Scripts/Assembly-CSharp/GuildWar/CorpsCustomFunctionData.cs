using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class CorpsCustomFunctionData : IDataTableOwner, IDataType<CorpsCustomFunctionData>, IKeyOwner<CorpsCustomFunctionKey>, IClientDataOwner<CorpsCustomFunctionData.ClientData>, IDataRefAble<CorpsCustomFunctionData>, IDataType, IDataUpdateListener<CorpsCustomFunctionData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<CorpsData> corps;
		}

		private HashSet<IDataRefInternal<CorpsCustomFunctionData>> m_dataRefs;

		public CorpsData corps
		{
			get
			{
				return null;
			}
		}

		public string atlasName
		{
			get
			{
				return null;
			}
		}

		public string icon
		{
			get
			{
				return null;
			}
		}

		public abstract Action<CorpsCustomFunctionData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract int corps_id { get; }

		public abstract short custom_action_type { get; }

		public abstract int custom_action_key { get; }

		public abstract int available_date { get; }

		public abstract int available_duration { get; }

		public abstract int cooldown_date { get; }

		public abstract short state { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<CorpsCustomFunctionKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract CorpsCustomFunctionKey GetKey();

		public void AddDataRef(IDataRefInternal<CorpsCustomFunctionData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<CorpsCustomFunctionData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
