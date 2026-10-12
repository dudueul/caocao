namespace GuildWar
{
	public abstract class DirectionCombatTargetData : IDataTableOwner, IDataType
	{
		public abstract int p_combat_action { get; }

		public abstract int target_place_id { get; }

		public abstract int target_corps_id { get; }

		public abstract short target_tile_x { get; }

		public abstract short target_tile_y { get; }

		public abstract short target_pos_x { get; }

		public abstract short target_pos_y { get; }

		public abstract ulong target_user_id { get; }

		public abstract int source_place_id { get; }

		public abstract int source_corps_id { get; }

		public abstract short source_pos_x { get; }

		public abstract short source_pos_y { get; }

		public abstract ulong source_user_id { get; }

		public abstract DataTables GetDataTables();
	}
}
