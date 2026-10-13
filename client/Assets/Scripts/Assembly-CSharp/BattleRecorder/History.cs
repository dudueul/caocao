using System;
using System.Collections.Generic;

namespace BattleRecorder
{
	public sealed class History : IWritable
	{
		public List<Turn> turns;

		public Turn curTurn
		{
			get
			{
				return null;
			}
		}

		public byte key
		{
			get
			{
				return 0;
			}
		}

		public static History Read(OptimizedBinaryReader r)
		{
			return null;
		}

		public void Write(OptimizedBinaryWriter w)
		{
		}

		public void Reset()
		{
		}

		public void OnFinishTurn()
		{
		}

		public void OnTurnStart()
		{
		}

		public void OnRevertTurn()
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

		public List<Tuple<TimelineMarker, UnitActionComplete>> GetTimelines()
		{
			return null;
		}

		public bool CanRevertHand()
		{
			return false;
		}

		public bool CanRevertTurn()
		{
			return false;
		}
	}
}
