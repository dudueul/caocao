namespace GuildWar
{
	public abstract class DataTables : DataTablesOrigin
	{
		public abstract WorldData worldData { get; }

		public abstract WorldSeqData worldSeqData { get; }

		public abstract DataTable<TileKey, TileData> tileDataTables { get; }

		public abstract CameraData cameraData { get; }

		public abstract ClientVisionData clientVisionData { get; }

		public abstract TileCandidatesData tileCandidatesData { get; }

		public abstract int GetClanDbShard();

		public abstract int GetWorldId();

		public abstract int GetSeq();

		public abstract string GetMapName();
	}
}
