using System;
using BattlePlay;

namespace Multiplay
{
	[Serializable]
	public abstract class StepBase
	{
		public static BattlePlayData battlePlayData { get; set; }

		public virtual Step step
		{
			get
			{
				return Step.Match;
			}
		}

		public StepBase(BattlePlayData bpData)
		{
		}

		public virtual bool IsComplete()
		{
			return false;
		}
	}
}
