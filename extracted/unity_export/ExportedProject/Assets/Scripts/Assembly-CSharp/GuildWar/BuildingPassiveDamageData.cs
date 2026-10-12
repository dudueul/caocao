using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class BuildingPassiveDamageData : IDataTableOwner, IDataType<BuildingPassiveDamageData>, IKeyOwner<BuildingPassiveDamageKey>, IClientDataOwner<BuildingPassiveDamageData.ClientData>, IDataRefAble<BuildingPassiveDamageData>, IDataType, IDataUpdateListener<BuildingPassiveDamageData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<BuildingData> building;

			public GuildWarPassive p_passive;
		}

		private HashSet<IDataRefInternal<BuildingPassiveDamageData>> m_dataRefs;

		public BuildingData building
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

		public abstract Action<BuildingPassiveDamageData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract int p_place_id { get; }

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

		protected void OnModified(IDataContainerLinker<BuildingPassiveDamageKey> factory)
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

		public abstract BuildingPassiveDamageKey GetKey();

		public void AddDataRef(IDataRefInternal<BuildingPassiveDamageData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<BuildingPassiveDamageData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
