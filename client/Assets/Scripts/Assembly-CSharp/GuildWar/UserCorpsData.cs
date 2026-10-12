using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class UserCorpsData : IDataTableOwner, IDataType<UserCorpsData>, IKeyOwner<UserCorpsKey>, IClientDataOwner<UserCorpsData.ClientData>, IDataRefAble<UserCorpsData>, IDataType, IDataUpdateListener<UserCorpsData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<UserData> user;

			public DataRef<CorpsData> corps;

			public GuildWarCorpsJob p_job;
		}

		private HashSet<IDataRefInternal<UserCorpsData>> m_dataRefs;

		public UserData user
		{
			get
			{
				return null;
			}
		}

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

		public abstract Action<UserCorpsData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract ulong guild_id { get; }

		public abstract ulong user_id { get; }

		public abstract int corps_id { get; }

		public abstract int corps_succeeding_exp { get; }

		public abstract int face_unit_id { get; }

		public abstract int face_skinitem_id { get; }

		public abstract int job_id { get; }

		public abstract int jobType_id { get; }

		public abstract int create_count { get; }

		public abstract int corps_creatable_date { get; }

		public abstract int create_date { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<UserCorpsKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract UserCorpsKey GetKey();

		public void AddDataRef(IDataRefInternal<UserCorpsData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<UserCorpsData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
