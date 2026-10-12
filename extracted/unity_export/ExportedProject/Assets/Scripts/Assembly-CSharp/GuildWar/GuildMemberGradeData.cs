using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class GuildMemberGradeData : IDataTableOwner, IDataType<GuildMemberGradeData>, IKeyOwner<GuildMemberGradeKey>, IClientDataOwner<GuildMemberGradeData.ClientData>, IDataRefAble<GuildMemberGradeData>, IDataType, IDataUpdateListener<GuildMemberGradeData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<GuildData> guild;

			public DataRef<UserData> user;
		}

		private HashSet<IDataRefInternal<GuildMemberGradeData>> m_dataRefs;

		public DataRef<GuildData> guild
		{
			get
			{
				return null;
			}
		}

		public UserData user
		{
			get
			{
				return null;
			}
		}

		public abstract Action<GuildMemberGradeData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract ulong guild_id { get; }

		public abstract ulong user_id { get; }

		public abstract short grade { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<GuildMemberGradeKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract GuildMemberGradeKey GetKey();

		public void AddDataRef(IDataRefInternal<GuildMemberGradeData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<GuildMemberGradeData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
