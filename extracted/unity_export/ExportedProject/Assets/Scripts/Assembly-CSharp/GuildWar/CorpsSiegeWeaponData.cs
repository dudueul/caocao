using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class CorpsSiegeWeaponData : IDataTableOwner, IDataType<CorpsSiegeWeaponData>, IKeyOwner<CorpsSiegeWeaponKey>, IClientDataOwner<CorpsSiegeWeaponData.ClientData>, IDataRefAble<CorpsSiegeWeaponData>, IDataType, IDataUpdateListener<CorpsSiegeWeaponData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<CorpsData> corps;

			public GuildWarSiegeWeapon p_siegeWeapon;
		}

		private HashSet<IDataRefInternal<CorpsSiegeWeaponData>> m_dataRefs;

		public CorpsData corps
		{
			get
			{
				return null;
			}
		}

		public GuildWarSiegeWeapon p_siegeWeapon
		{
			get
			{
				return null;
			}
		}

		public int MaxMilitaryPower
		{
			get
			{
				return 0;
			}
		}

		public abstract Action<CorpsSiegeWeaponData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract int corps_id { get; }

		public abstract int p_siege_weapon { get; }

		public abstract int oldCorpsInfo_job_id { get; }

		public abstract int oldCorpsInfo_jobType_id { get; }

		public abstract short oldCorpsInfo_job_max_level { get; }

		public abstract int oldCorpsInfo_max_military_power { get; }

		public abstract int oldCorpsInfo_action_point_recovery_amount { get; }

		public abstract int oldCorpsInfo_action_point_recovery_time { get; }

		public abstract short oldCorpsInfo_max_action_point { get; }

		public abstract int oldCorps_exp { get; }

		public abstract int oldCorps_military_power { get; }

		public abstract short oldCorps_action_point { get; }

		public abstract int oldCorps_lastest_action_date { get; }

		public abstract int mount_complete_date { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<CorpsSiegeWeaponKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract CorpsSiegeWeaponKey GetKey();

		public void AddDataRef(IDataRefInternal<CorpsSiegeWeaponData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<CorpsSiegeWeaponData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
