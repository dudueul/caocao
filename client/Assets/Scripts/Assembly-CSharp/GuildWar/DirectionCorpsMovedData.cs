namespace GuildWar
{
	public abstract class DirectionCorpsMovedData : IDataTableOwner, IDataType
	{
		public abstract ulong moved_user_id { get; }

		public abstract int moved_corps_id { get; }

		public abstract short before_pos_x { get; }

		public abstract short before_pos_y { get; }

		public abstract short after_pos_x { get; }

		public abstract short after_pos_y { get; }

		public abstract DataTables GetDataTables();
	}
}
