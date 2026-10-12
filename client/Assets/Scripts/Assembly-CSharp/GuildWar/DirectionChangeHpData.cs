namespace GuildWar
{
	public abstract class DirectionChangeHpData : IDataTableOwner, IDataType
	{
		public abstract int target_place_id { get; }

		public abstract int target_corps_id { get; }

		public abstract short target_pos_x { get; }

		public abstract short target_pos_y { get; }

		public abstract ulong target_user_id { get; }

		public abstract short source_pos_x { get; }

		public abstract short source_pos_y { get; }

		public abstract ulong source_user_id { get; }

		public abstract int before_hp { get; }

		public abstract int after_hp { get; }

		public abstract DataTables GetDataTables();
	}
}
