namespace GuildWar
{
	public abstract class DirectionCombatMessageData : IDataTableOwner, IDataType
	{
		public abstract ulong guild_id { get; }

		public abstract int p_combat_message { get; }

		public abstract int custom_key { get; }

		public abstract string message_params_guild { get; }

		public abstract string message_params_other { get; }

		public abstract DataTables GetDataTables();
	}
}
