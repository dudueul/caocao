namespace Features
{
	internal class TechFeature : Feature
	{
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

		public override int GetLevelWithValue(int id, ulong uid, int level)
		{
			return 0;
		}
	}
}
