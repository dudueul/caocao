using System;
using System.Collections.Generic;

namespace BattleRecorder
{
	public sealed class Turn : HistoryElement, IWritable
	{
		public List<Action> actions;

		public byte key
		{
			get
			{
				return 0;
			}
		}

		public override void _Apply()
		{
		}

		public void OnTurnStart()
		{
		}

		public bool OnUnitActionStart()
		{
			return false;
		}

		public void OnUnitActionComplete(UnitData unitData)
		{
		}

		public void Record(Action action)
		{
		}

		public Action GetLastAction()
		{
			return null;
		}

		public void DeleteLastAction()
		{
		}

		public bool CanRevert()
		{
			return false;
		}

		public List<Tuple<TimelineMarker, UnitActionComplete>> GetTimelines()
		{
			return null;
		}

		public static Turn Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
