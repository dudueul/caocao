using System;
using BattlePlay;
using LitJson;

namespace Multiplay
{
	[Serializable]
	public class Match : StepBase
	{
		public override Step step
		{
			get
			{
				return Step.Match;
			}
		}

		public Match(BattlePlayData battlePlayData)
			: base(null)
		{
		}

		public override bool IsComplete()
		{
			return false;
		}

		public static void Deserialize(JsonData jsonData)
		{
		}
	}
}
