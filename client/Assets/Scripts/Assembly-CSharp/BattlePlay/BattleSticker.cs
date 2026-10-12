using System;
using LitJson;

namespace BattlePlay
{
	[Serializable]
	public class BattleSticker : ITransmittable
	{
		public ulong uid;

		public int stickerId;

		public int turnIdx;

		public int remainedSec;

		public bool applied;

		public BattleSticker()
		{
		}

		public BattleSticker(ulong uid, int stickerId, int turnIdx, int remainedSec)
		{
		}

		public BattleSticker(JsonData data)
		{
		}

		public override void Serialize(JsonWriter writer)
		{
		}

		protected virtual void EndWriteObject(JsonWriter writer)
		{
		}

		protected override void DeserializeImpl(JsonData data)
		{
		}

		public void Log(string head)
		{
		}
	}
}
