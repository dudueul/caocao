namespace BattleRecorder
{
	public abstract class Action : HistoryElement, IWritable
	{
		public virtual byte key
		{
			get
			{
				return 0;
			}
		}

		public virtual bool NeedToRecord()
		{
			return false;
		}

		public virtual void Write(OptimizedBinaryWriter w)
		{
		}
	}
}
