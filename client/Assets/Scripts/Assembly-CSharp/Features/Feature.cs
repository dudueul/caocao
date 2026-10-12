namespace Features
{
	public abstract class Feature
	{
		public abstract bool IsEnabled(int id, ulong uid);

		public abstract int GetValue(int id, ulong uid);

		public abstract bool IsNotified(int id, ulong uid);

		public abstract void SetNotified(int id, ulong uid);

		public abstract string GetDesc(int id, ulong uid);

		public abstract string GetLockedMessage(int id, ulong uid);

		public abstract int GetLevelWithValue(int id, ulong uid, int level);
	}
}
