using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class CorpsInformationData : IDataTableOwner, IDataType<CorpsInformationData>, IKeyOwner<CorpsInformationKey>, IClientDataOwner<CorpsInformationData.ClientData>, IDataRefAble<CorpsInformationData>, IDataType, IDataUpdateListener<CorpsInformationData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<CorpsData> corps;

			public GuildWarCorpsJob p_job;
		}

		private HashSet<IDataRefInternal<CorpsInformationData>> m_dataRefs;

		public CorpsData corps
		{
			get
			{
				return null;
			}
		}

		public GuildWarCorpsJob p_job
		{
			get
			{
				return null;
			}
		}

		public abstract Action<CorpsInformationData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract int corps_id { get; }

		public abstract int face_unit_id { get; }

		public abstract int face_skinitem_id { get; }

		public abstract string nation_name { get; }

		public abstract int job_id { get; }

		public abstract int jobType_id { get; }

		public abstract int base_atk { get; }

		public abstract int base_inte { get; }

		public abstract int base_def { get; }

		public abstract int base_agi { get; }

		public abstract int base_mor { get; }

		public abstract int additive_atk_percent { get; }

		public abstract int additive_inte_percent { get; }

		public abstract int additive_def_percent { get; }

		public abstract int additive_agi_percent { get; }

		public abstract int additive_mor_percent { get; }

		public abstract short job_max_level { get; }

		public abstract int max_military_power { get; }

		public abstract short max_action_point { get; }

		public abstract int action_point_recovery_time { get; }

		public abstract int action_point_recovery_amount { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<CorpsInformationKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract CorpsInformationKey GetKey();

		public void AddDataRef(IDataRefInternal<CorpsInformationData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<CorpsInformationData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
