// [RE] Rebuild-only: client decode of the server's CWLD world snapshots
// (server/milestone 6b). Frame: [magic "CWLD"][worldId][seq][corpsCount]
// [corps rows 28B]×[buildingCount][building rows 12B] — little-endian,
// flatbuffers-struct layout (dsa-02 §1b). The original's snapshot/diff
// protocol (DeserializeSnapshot/Diff) rides the same shape per table.
using System.Collections.Generic;
using FlatBuffers;

namespace GuildWar.Backend
{
	// [RE] rebuild-owned row records (NOT the abstract data-table types —
	// those materialize via the ClientDataType pipeline; rows are the wire).
	public struct CorpsRow
	{
		public int corpsId;
		public int guildId;
		public int posX;
		public int posY;
		public int faceUnitId;
		public int jobType;
		public int militaryPower;
	}

	public struct BuildingRow
	{
		public int placeId;
		public int kind;
		public int guildId;
	}

	public class WorldSnapshot
	{
		public int worldId;

		public int seq;

		public List<CorpsRow> corps = new List<CorpsRow>();

		public List<BuildingRow> buildings = new List<BuildingRow>();

		private const int MAGIC = unchecked((int)0x444C5743); // "CWLD"

		// [RE] binary decode — the mirror of server/worldSnapshot; students
		// verify by hand with the Python round-trip in the milestone log.
		public static WorldSnapshot Decode(byte[] bytes)
		{
			var snap = new WorldSnapshot();
			if (bytes == null || bytes.Length < 20)
				return snap;
			var bb = new ByteBuffer(bytes);
			int magic = bb.GetInt(0);
			if (magic != MAGIC)
				return snap;   // [RE] wrong frame — callers check HasWorld
			snap.worldId = bb.GetInt(4);
			snap.seq = bb.GetInt(8);
			int corpsCount = bb.GetInt(12);
			int bldCount = bb.GetInt(16);
			int off = 20;
			for (int i = 0; i < corpsCount; i++)
			{
				var row = new CorpsRow();
				row.corpsId = bb.GetInt(off);
				row.guildId = bb.GetInt(off + 4);
				row.posX = bb.GetInt(off + 8);
				row.posY = bb.GetInt(off + 12);
				row.faceUnitId = bb.GetInt(off + 16);
				row.jobType = bb.GetInt(off + 20);
				row.militaryPower = bb.GetInt(off + 24);
				snap.corps.Add(row);
				off += 28;
			}
			for (int i = 0; i < bldCount; i++)
			{
				var row = new BuildingRow();
				row.placeId = bb.GetInt(off);
				row.kind = bb.GetInt(off + 4);
				row.guildId = bb.GetInt(off + 8);
				snap.buildings.Add(row);
				off += 12;
			}
			return snap;
		}

		public bool HasWorld
		{
			get
			{
				return worldId != 0;
			}
		}
	}
}
