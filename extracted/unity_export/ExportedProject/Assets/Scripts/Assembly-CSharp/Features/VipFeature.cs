using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;

namespace Features
{
	internal class VipFeature : Feature
	{
		private int GetValue(int level, List<ObscuredInt> levels, List<ObscuredInt> values)
		{
			return 0;
		}

		private int GetLevelWithValue(int value, List<ObscuredInt> levels, List<ObscuredInt> values)
		{
			return 0;
		}

		public override bool IsEnabled(int id, ulong uid)
		{
			return false;
		}

		public override int GetValue(int id, ulong uid)
		{
			return 0;
		}

		public override bool IsNotified(int id, ulong uid)
		{
			return false;
		}

		public override void SetNotified(int id, ulong uid)
		{
		}

		public override string GetDesc(int id, ulong uid)
		{
			return null;
		}

		public override string GetLockedMessage(int id, ulong uid)
		{
			return null;
		}

		public override int GetLevelWithValue(int id, ulong uid, int value)
		{
			return 0;
		}
	}
}
