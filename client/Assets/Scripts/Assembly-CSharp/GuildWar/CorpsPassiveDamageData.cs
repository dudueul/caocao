using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class CorpsPassiveDamageData : IDataTableOwner, IDataType<CorpsPassiveDamageData>, IKeyOwner<CorpsPassiveDamageKey>, IClientDataOwner<CorpsPassiveDamageData.ClientData>, IDataRefAble<CorpsPassiveDamageData>, IDataType, IDataUpdateListener<CorpsPassiveDamageData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<CorpsData> corps;

			public GuildWarPassive p_passive;
		}

		private HashSet<IDataRefInternal<CorpsPassiveDamageData>> m_dataRefs;

		public CorpsData corps
		{
			get
			{
				return null;
			}
		}

		public GuildWarPassive p_passive
		{
			get
			{
				return null;
			}
		}

		public abstract Action<CorpsPassiveDamageData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract int corps_id { get; }

		public abstract int passive_index { get; }

		public abstract int passive_id { get; }

		public abstract int create_date { get; }

		public abstract int first_tick_date { get; }

		public abstract int last_tick_date { get; }

		public abstract int next_tick_date { get; }

		public abstract int end_date { get; }

		public abstract short damage_type { get; }

		public abstract int damage_power { get; }

		public abstract int damage_interval { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<CorpsPassiveDamageKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public int GetAccumulatedDamage(long curTime, int maxMilitaryPower, out long nextTickTime)
		{
			nextTickTime = default(long);
			return 0;
		}

		public abstract DataTables GetDataTables();

		public abstract CorpsPassiveDamageKey GetKey();

		public void AddDataRef(IDataRefInternal<CorpsPassiveDamageData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<CorpsPassiveDamageData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
