using System;
using BattlePlay;
using LitJson;

namespace Multiplay
{
	[Serializable]
	public class Ban : StepBase
	{
		public override Step step
		{
			get
			{
				return Step.Match;
			}
		}

		public Ban(BattlePlayData battlePlayData)
			: base(null)
		{
		}

		public override bool IsComplete()
		{
			return false;
		}

		public static void Serialize(JsonWriter writer)
		{
		}

		public static void Deserialize(JsonData jsonData)
		{
		}
	}
}
