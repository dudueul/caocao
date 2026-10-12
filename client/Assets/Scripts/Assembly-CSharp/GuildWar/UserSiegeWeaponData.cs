using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class UserSiegeWeaponData : IDataTableOwner, IDataType<UserSiegeWeaponData>, IKeyOwner<UserSiegeWeaponKey>, IClientDataOwner<UserSiegeWeaponData.ClientData>, IDataRefAble<UserSiegeWeaponData>, IDataType, IDataUpdateListener<UserSiegeWeaponData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<UserData> user;

			public GuildWarSiegeWeapon p_siegeWeapon;
		}

		private HashSet<IDataRefInternal<UserSiegeWeaponData>> m_dataRefs;

		public UserData user
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

		public abstract Action<UserSiegeWeaponData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract ulong guild_id { get; }

		public abstract ulong user_id { get; }

		public abstract int p_siege_weapon { get; }

		public abstract int amount { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<UserSiegeWeaponKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract UserSiegeWeaponKey GetKey();

		public void AddDataRef(IDataRefInternal<UserSiegeWeaponData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<UserSiegeWeaponData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
